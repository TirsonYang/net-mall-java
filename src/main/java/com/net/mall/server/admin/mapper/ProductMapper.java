package com.net.mall.server.admin.mapper;


import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.entity.ProductEntity;
import com.net.mall.pojo.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("adminProductMapper")
public interface ProductMapper {


    Page<ProductVO> page(PageQuery query, Long categoryId);

    List<ProductVO> list(Long categoryId);

    void updateStock(Long id, Integer stock);

    ProductEntity getById(Long productId);
}
