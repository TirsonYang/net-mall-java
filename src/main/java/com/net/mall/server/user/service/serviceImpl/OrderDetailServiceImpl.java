package com.net.mall.server.user.service.serviceImpl;

import com.net.mall.pojo.dto.OrderDetailDTO;
import com.net.mall.pojo.entity.OrderDetailEntity;
import com.net.mall.pojo.entity.ProductEntity;
import com.net.mall.pojo.vo.OrderDetailVO;
import com.net.mall.pojo.vo.OrdersVO;
import com.net.mall.server.user.mapper.OrderDetailMapper;
import com.net.mall.server.user.service.OrderDetailService;
import com.net.mall.server.user.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("userOrderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ProductService productService;

    @Override
    public List<OrdersVO> list(Long userId) {
        return orderDetailMapper.list(userId);
    }

    @Override
    @Transactional
    public void addList(List<OrderDetailDTO> list) {
//        orderDetailMapper.add(detailEntity);
        ArrayList<OrderDetailEntity> entities = new ArrayList<>();
        ArrayList<ProductEntity> products = new ArrayList<>();
        for (OrderDetailDTO dto : list) {
            // 转换订单详情表记录
            OrderDetailEntity entity = new OrderDetailEntity();
            BeanUtils.copyProperties(dto,entity);
            entities.add(entity);

            // 转换商品表记录

            ProductEntity product = new ProductEntity();
            product.setId(dto.getProductId());
            product.setStock(dto.getQuantity());
            products.add(product);
        }

        orderDetailMapper.addList(entities);
        productService.reduceStock(products);
    }

    @Override
    public List<OrderDetailVO> getByOrderId(Long orderId) {

        return orderDetailMapper.getByOrderId(orderId);
    }

    @Override
    public List<OrderDetailVO> queryByOrderNum(String orderNum) {
        return orderDetailMapper.queryByOrderNum(orderNum);
    }

    @Override
    public List<OrderDetailVO> queryByOrderNumAndComputerId(String orderId, String computerId) {
        return orderDetailMapper.queryByOrderNumAndComputerId(orderId,computerId);
    }
}
