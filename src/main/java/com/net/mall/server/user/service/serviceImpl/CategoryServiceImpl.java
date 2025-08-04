package com.net.mall.server.user.service.serviceImpl;


import com.net.mall.pojo.vo.CategoryVO;
import com.net.mall.server.user.mapper.CategoryMapper;
import com.net.mall.server.user.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userCategoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> list() {
        return categoryMapper.list();
    }
}
