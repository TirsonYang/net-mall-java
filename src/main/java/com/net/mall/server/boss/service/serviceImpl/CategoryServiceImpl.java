package com.net.mall.server.boss.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.CategoryDTO;
import com.net.mall.pojo.entity.CategoryEntity;
import com.net.mall.pojo.vo.CategoryVO;
import com.net.mall.server.boss.mapper.CategoryMapper;
import com.net.mall.server.boss.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        //TODO 登录功能后设置创建用户和修改用户
        entity.setCreateUser(1L);
        entity.setUpdateUser(1L);
        categoryMapper.add(entity);
    }

    @Override
    public void update(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setUpdateTime(LocalDateTime.now());
        //TODO 登录功能后设置修改用户
        entity.setUpdateUser(1L);
        categoryMapper.update(entity);
    }

    @Override
    public void delete(Long id) {
        categoryMapper.delete(id);
    }

    @Override
    public PageResult page(PageQuery query) {
        PageHelper.startPage(query.getPage(),query.getPageSize());
        Page<CategoryVO> page=categoryMapper.page(query);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<CategoryVO> list() {
        return categoryMapper.list();
    }
}
