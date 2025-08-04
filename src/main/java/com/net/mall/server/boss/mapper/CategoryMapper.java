package com.net.mall.server.boss.mapper;

import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.entity.CategoryEntity;
import com.net.mall.pojo.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("bossCategoryMapper")
public interface CategoryMapper {
    void add(CategoryEntity entity);

    void update(CategoryEntity entity);

    void delete(Long id);

    Page<CategoryVO> page(PageQuery query);

    List<CategoryVO> list();
}
