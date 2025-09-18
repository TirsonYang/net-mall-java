package com.net.mall.server.boss.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.ProductDTO;
import com.net.mall.pojo.entity.ProductEntity;
import com.net.mall.pojo.vo.ProductVO;

import java.util.List;

public interface ProductService {
    void add(ProductDTO dto);

    void update(ProductDTO dto);

    void delete(Long id);

    PageResult page(PageQuery query,Long categoryId);

    ProductEntity getById(Long productId);

    List<ProductVO> list(Long categoryId);

    void deleteByCategoryId(Long categoryId);

    ProductVO findById(Long id);
}
