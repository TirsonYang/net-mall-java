package com.net.mall.server.user.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.utils.OrderNumGenerateUtil;
import com.net.mall.pojo.dto.OrderDetailDTO;
import com.net.mall.pojo.dto.OrdersCancelDTO;
import com.net.mall.pojo.dto.OrdersDTO;
import com.net.mall.pojo.entity.OrderDetailEntity;
import com.net.mall.pojo.entity.OrdersEntity;
import com.net.mall.pojo.vo.OrdersVO;
import com.net.mall.pojo.vo.ShoppingCartVO;
import com.net.mall.server.user.mapper.OrdersMapper;
import com.net.mall.server.user.service.OrderDetailService;
import com.net.mall.server.user.service.OrdersService;
import com.net.mall.server.user.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service("userOrdersService")
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public List<OrdersVO> list() {
        //TODO 登录功能完成后，获取当前用户id,根据userId列表查询订单
        Long userId=1L;
        return ordersMapper.list(userId);
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
    public void add(OrdersDTO dto) {
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
        ordersMapper.add(entity);
        //2、从购物车中获取商品信息后加入订单详情
        List<ShoppingCartVO> cartList = shoppingCartService.list();
        for (ShoppingCartVO vo : cartList) {
            OrderDetailEntity detailEntity = new OrderDetailEntity();
            detailEntity.setProductId(vo.getProductId());
            detailEntity.setImageUrl(vo.getImageUrl());
            detailEntity.setProductName(vo.getProductName());
            detailEntity.setOrderId(entity.getId());
            detailEntity.setQuantity(vo.getNumber());
            detailEntity.setAmount(vo.getAmount());
            orderDetailService.add(detailEntity);
        }
    }

    @Override
    public void cancel(OrdersCancelDTO dto) {
        LocalDateTime now= LocalDateTime.now();
        ordersMapper.cancel(dto,now);
    }
}
