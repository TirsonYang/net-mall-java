package com.net.mall.server.boss.mapper;

import com.net.mall.pojo.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    void add(CategoryEntity entity);

    void update(CategoryEntity entity);
}
