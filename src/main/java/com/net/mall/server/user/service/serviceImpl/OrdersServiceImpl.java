package com.net.mall.server.user.service.serviceImpl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.utils.OrderNumGenerateUtil;
import com.net.mall.common.utils.SortUtil;
import com.net.mall.pojo.dto.OrderDetailDTO;
import com.net.mall.pojo.dto.OrdersCancelDTO;
import com.net.mall.pojo.dto.OrdersDTO;
import com.net.mall.pojo.entity.OrdersEntity;
import com.net.mall.pojo.entity.ProductEntity;
import com.net.mall.pojo.entity.TicketEntity;
import com.net.mall.pojo.vo.OrderDetailVO;
import com.net.mall.pojo.vo.OrderMessageVO;
import com.net.mall.pojo.vo.OrdersVO;
import com.net.mall.pojo.vo.ShoppingCartVO;
import com.net.mall.server.user.mapper.OrdersMapper;
import com.net.mall.server.user.service.OrderDetailService;
import com.net.mall.server.user.service.OrdersService;
import com.net.mall.server.user.service.ProductService;
import com.net.mall.server.user.service.ShoppingCartService;
import com.net.mall.server.websocket.WebSocketServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("userOrdersService")
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private WebSocketServer webSocketServer;

    @Autowired
    private AlipayClient client;

    @Override
    public List<OrdersVO> list(String orderNum, LocalDateTime startTime, LocalDateTime endTime, Long userId) {
        List<OrdersVO> list = ordersMapper.list(orderNum, startTime, endTime, userId);
        SortUtil.sortOrdersList(list);
        return list;
    }

    @Override
    public PageResult page(PageQuery pageQuery) {
        //TODO 登录功能完成后，获取当前用户id,根据userId分页查询订单
        Long userId=1L;
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getPageSize());
        Page<OrdersVO> page=ordersMapper.page(pageQuery,userId);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<OrdersVO> detail(Long id) {
        return orderDetailService.list(id);
    }

    @Override
    public Long add(OrdersDTO dto) {
        //1、生成订单号、新增订单
        OrdersEntity entity = new OrdersEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setOrderNum(OrderNumGenerateUtil.generateOrderId());
        //TODO 登录功能完成后，获取当前用户id和电脑机位id
        Long userId=1L;
        Long computerId=100L;
        entity.setUserId(userId);
        entity.setComputerId(computerId);
        entity.setOrderTime(LocalDateTime.now());
        entity.setStatus(1);
        ordersMapper.add(entity);


        //TODO websocket发送信息 提示来单 修改到支付后

        OrderMessageVO messageVO=new OrderMessageVO();
        messageVO.setContent("您有新的订单，请及时配送");
        messageVO.setType("order");
        messageVO.setOrderNum(entity.getOrderNum());
        messageVO.setTimestamp(System.currentTimeMillis());

        webSocketServer.send(messageVO);


        // 清空购物车
        shoppingCartService.clear();



        //2、从购物车中获取商品信息后加入订单详情
//        List<ShoppingCartVO> cartList = shoppingCartService.list(userId);
//        List<OrderDetailDTO> detailList=new ArrayList<>();
//        for (ShoppingCartVO vo : cartList) {
//            OrderDetailDTO detailDTO = new OrderDetailDTO();
//            detailDTO.setProductId(vo.getProductId());
//            detailDTO.setImageUrl(vo.getImageUrl());
//            detailDTO.setProductName(vo.getProductName());
//            detailDTO.setOrderId(entity.getId());
//            detailDTO.setQuantity(vo.getNumber());
//            detailDTO.setAmount(vo.getPrice());
//            detailList.add(detailDTO);
//        }
//        orderDetailService.addList(detailList);
        return entity.getId();
    }

    @Override
    public void cancel(OrdersCancelDTO dto) {
        LocalDateTime now= LocalDateTime.now();
        ordersMapper.cancel(dto,now);
    }

    @Override
    public String orderByTicket(TicketEntity ticket, String phone, String remark, Long userId) {
        ProductEntity product = productService.getById(ticket.getProductId());
        //配置实体类，插入订单表
        OrdersEntity entity = new OrdersEntity();
        entity.setOrderNum(OrderNumGenerateUtil.generateOrderId());
        entity.setStatus(2);
        entity.setComputerId(1L);
        entity.setUserId(userId);
        entity.setOrderTime(LocalDateTime.now());
        entity.setCheckoutTime(LocalDateTime.now());
        entity.setTotal(product.getPrice());
        entity.setPreference(product.getPrice());
        entity.setAmount(new BigDecimal("0"));
        entity.setPhone(phone);
        entity.setRemark(remark+"--使用优惠券");
        entity.setPayMethod(3);
        ordersMapper.add(entity);


        //websocket发送信息 提示来单
        OrderMessageVO messageVO =new OrderMessageVO();
        messageVO.setContent("您有新的订单，请及时配送");
        messageVO.setType("order");
        messageVO.setOrderNum(entity.getOrderNum());
        messageVO.setTimestamp(System.currentTimeMillis());

        webSocketServer.send(messageVO);

        //配置实体类，插入订单明细表
        OrderDetailDTO detailDTO = new OrderDetailDTO();
        detailDTO.setProductId(product.getId());
        detailDTO.setProductName(product.getProductName());
        detailDTO.setImageUrl(product.getImageUrl());
        detailDTO.setOrderId(entity.getId());
        detailDTO.setQuantity(1);
        detailDTO.setAmount(new BigDecimal("0"));
        List<OrderDetailDTO> list=new ArrayList<>();
        list.add(detailDTO);
        orderDetailService.addList(list);
        return entity.getOrderNum();
    }

    @Transactional
    @Override
    public String alipayCreate(Long orderId) {


        List<OrderDetailVO> list = orderDetailService.getByOrderId(orderId);
        OrdersEntity entity = ordersMapper.getById(orderId);

        // 构造请求参数以调用接口
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();

        // 设置商户订单号
        model.setOutTradeNo(String.valueOf(orderId));

        // 设置订单总金额
        model.setTotalAmount(String.valueOf(entity.getAmount()));

        // 设置订单标题
        model.setSubject("测试订单");

        // 设置产品码
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        // 设置PC扫码支付的方式
//        model.setQrPayMode("1");

        // 设置商户自定义二维码宽度
//        model.setQrcodeWidth(100L);

        // 设置订单包含的商品列表信息
        List<GoodsDetail> goodsDetail = new ArrayList<GoodsDetail>();
        for(OrderDetailVO vo : list){
            GoodsDetail goodsDetail0 = new GoodsDetail();
            goodsDetail0.setGoodsName(vo.getProductName());
//            goodsDetail0.setAlipayGoodsId(String.valueOf(vo.getProductId()));
            goodsDetail0.setQuantity(Long.valueOf(vo.getQuantity()));
            goodsDetail0.setPrice(String.valueOf(vo.getAmount()));
            goodsDetail0.setGoodsId(String.valueOf(vo.getProductId()));
            goodsDetail0.setGoodsCategory(vo.getProductName());
//            goodsDetail0.setCategoriesTree("124868003|126232002|126252004");
//            goodsDetail0.setShowUrl("http://www.alipay.com/xxx.jpg");
            goodsDetail.add(goodsDetail0);
        }
        model.setGoodsDetail(goodsDetail);

        request.setBizModel(model);
        // 第三方代调用模式下请设置app_auth_token
        // request.putOtherTextParam("app_auth_token", "<-- 请填写应用授权令牌 -->");

        AlipayTradePagePayResponse response = null;
        try {
            response = client.pageExecute(request, "POST");
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        // 如果需要返回GET请求，请使用
        // AlipayTradePagePayResponse response = alipayClient.pageExecute(request, "GET");
        String pageRedirectionData = response.getBody();
        System.out.println(pageRedirectionData);

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
            // System.out.println(diagnosisUrl);
        }
        return response.getBody();
    }


}
