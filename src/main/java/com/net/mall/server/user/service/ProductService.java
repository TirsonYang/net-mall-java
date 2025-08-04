package com.net.mall.server.user.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.entity.ProductEntity;

public interface ProductService {
    PageResult page(PageQuery query, Long categoryId);

    ProductEntity getById(Long productId);
}
