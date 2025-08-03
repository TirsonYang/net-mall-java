package com.net.mall.server.boss.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.vo.OrderDetailVO;

import java.util.List;

public interface OrderDetailService {
    PageResult page(PageQuery query, Long orderId);

    List<OrderDetailVO> list(Long orderId);
}
