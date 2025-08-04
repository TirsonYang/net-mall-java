package com.net.mall.server.user.mapper;

import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.entity.ProductEntity;
import com.net.mall.pojo.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("userProductMapper")
public interface ProductMapper {

    Page<ProductVO> page(PageQuery query, Long categoryId);

    ProductEntity getById(Long productId);
}
