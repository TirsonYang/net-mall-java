package com.net.mall.server.admin.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    PageResult page(PageQuery query);

    List<CategoryVO> list();
}
