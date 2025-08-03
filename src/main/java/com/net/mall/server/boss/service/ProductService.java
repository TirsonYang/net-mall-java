package com.net.mall.server.boss.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.ProductDTO;

public interface ProductService {
    void add(ProductDTO dto);

    void update(ProductDTO dto);

    void delete(Long id);

    PageResult page(PageQuery query,Long categoryId);
}
