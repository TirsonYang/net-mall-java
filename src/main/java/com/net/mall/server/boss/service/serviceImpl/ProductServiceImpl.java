package com.net.mall.server.boss.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.context.BaseContext;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.ProductDTO;
import com.net.mall.pojo.entity.ProductEntity;
import com.net.mall.pojo.vo.ProductVO;
import com.net.mall.server.boss.mapper.ProductMapper;
import com.net.mall.server.boss.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("bossProductService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void add(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        entity.setCreateUser(BaseContext.getCurrentUserId());
        entity.setUpdateUser(BaseContext.getCurrentUserId());
        //TODO 完成图片上传功能后设置图片访问路径
        entity.setImageUrl("./img");
        productMapper.add(entity);
    }

    @Override
    public void update(ProductDTO dto) {
        ProductEntity entity = new ProductEntity();
        BeanUtils.copyProperties(dto,entity);
        entity.setUpdateTime(LocalDateTime.now());
        entity.setUpdateUser(BaseContext.getCurrentUserId());
        //TODO 完成图片上传功能后设置图片访问路径
        entity.setImageUrl("./img");
        productMapper.update(entity);
    }

    @Override
    public void delete(Long id) {
        productMapper.delete(id);
    }

    @Override
    public PageResult page(PageQuery query,Long categoryId) {
        PageHelper.startPage(query.getPage(),query.getPageSize());
        Page<ProductVO> page=productMapper.page(query,categoryId);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public ProductEntity getById(Long productId) {
        return productMapper.getById(productId);
    }
}
