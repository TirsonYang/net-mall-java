package com.net.mall.server.boss.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.CategoryDTO;

public interface CategoryService {
    void add(CategoryDTO dto);

    void update(CategoryDTO dto);

    void delete(Long id);

    PageResult page(PageQuery query);
}
