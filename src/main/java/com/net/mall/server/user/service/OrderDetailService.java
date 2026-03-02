package com.net.mall.server.user.service;

import com.net.mall.pojo.dto.OrderDetailDTO;
import com.net.mall.pojo.entity.OrderDetailEntity;
import com.net.mall.pojo.vo.OrderDetailVO;
import com.net.mall.pojo.vo.OrdersVO;

import java.util.List;

public interface OrderDetailService {
    List<OrdersVO> list(Long userId);

    void addList(List<OrderDetailDTO> list);

    List<OrderDetailVO> getByOrderId(Long orderId);

    List<OrderDetailVO> queryByOrderNum(String orderNum);

    List<OrderDetailVO> queryByOrderNumAndComputerId(String orderId, String computerId);
}
