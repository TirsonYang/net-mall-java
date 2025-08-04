package com.net.mall.server.user.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;

public interface ProductService {
    PageResult page(PageQuery query, Long categoryId);
}
