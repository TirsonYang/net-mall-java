package com.net.mall.server.boss.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;

public interface OrdersService {
    PageResult page(PageQuery query);

    void updateStatus(Long id, Integer status);
}
