package com.net.mall.server.admin.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.vo.CategoryVO;
import com.net.mall.server.admin.mapper.CategoryMapper;
import com.net.mall.server.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminCategoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult page(PageQuery query) {
        PageHelper.startPage(query.getPage(),query.getPageSize());
        Page<CategoryVO> page = categoryMapper.page(query);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<CategoryVO> list() {
        return categoryMapper.list();
    }
}
