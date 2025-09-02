package com.net.mall.server.admin.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.OrdersQueryDTO;
import com.net.mall.pojo.vo.OrdersVO;

import java.util.List;

public interface OrdersService {
    PageResult page(PageQuery query);

    void finish(Long id);

    List<OrdersVO> list(OrdersQueryDTO dto);
}
