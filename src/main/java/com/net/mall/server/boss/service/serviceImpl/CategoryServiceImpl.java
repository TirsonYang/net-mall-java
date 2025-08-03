package com.net.mall.server.boss.service.serviceImpl;

import com.net.mall.pojo.dto.CategoryDTO;
import com.net.mall.pojo.entity.CategoryEntity;
import com.net.mall.server.boss.mapper.CategoryMapper;
import com.net.mall.server.boss.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
