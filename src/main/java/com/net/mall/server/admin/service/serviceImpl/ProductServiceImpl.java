package com.net.mall.server.admin.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.vo.ProductVO;
import com.net.mall.server.admin.mapper.ProductMapper;
import com.net.mall.server.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageResult page(PageQuery query, Long categoryId) {
        PageHelper.startPage(query.getPage(),query.getPageSize());
        Page<ProductVO> page= productMapper.page(query,categoryId);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<ProductVO> list(Long categoryId) {
        return productMapper.list(categoryId);
    }

    @Override
    public void updateStock(Long id, Integer stock) {
        productMapper.updateStock(id,stock);
    }
}
