package com.net.mall.server.user.service;

import com.net.mall.pojo.entity.OrderDetailEntity;
import com.net.mall.pojo.vo.OrdersVO;

import java.util.List;

public interface OrderDetailService {
    List<OrdersVO> list(Long userId);

    void add(OrderDetailEntity detailEntity);
}
