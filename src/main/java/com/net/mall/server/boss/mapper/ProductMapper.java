package com.net.mall.server.boss.mapper;

import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.entity.ProductEntity;
import com.net.mall.pojo.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("bossProductMapper")
public interface ProductMapper {
    void add(ProductEntity entity);

    void update(ProductEntity entity);

    void delete(Long id);

    Page<ProductVO> page(PageQuery query,Long categoryId);

    ProductEntity getById(Long productId);

    List<ProductVO> getList(Long categoryId);
}
