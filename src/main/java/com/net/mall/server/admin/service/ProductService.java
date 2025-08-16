package com.net.mall.server.admin.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.entity.ProductEntity;
import com.net.mall.pojo.vo.ProductVO;

import java.util.List;

public interface ProductService {
    PageResult page(PageQuery query, Long categoryId);

    List<ProductVO> list(Long categoryId);

    void updateStock(Long id, Integer stock);

    ProductEntity getById(Long productId);
}
