package com.net.mall.server.user.service.serviceImpl;

import com.net.mall.common.context.BaseContext;
import com.net.mall.pojo.dto.ShoppingCartDTO;
import com.net.mall.pojo.entity.ProductEntity;
import com.net.mall.pojo.entity.ShoppingCartEntity;
import com.net.mall.pojo.vo.ShoppingCartVO;
import com.net.mall.server.user.mapper.ShoppingCartMapper;
import com.net.mall.server.user.service.ProductService;
import com.net.mall.server.user.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service("userShoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private ProductService productService;

    @Override
    public void add(ShoppingCartDTO dto) {
        ShoppingCartEntity entity = shoppingCartMapper.getByUserIdAndProductId(dto);

        if (entity!=null){
            dto.setNumber(entity.getNumber()+1);
            this.update(dto);
        }

        ShoppingCartEntity cart = new ShoppingCartEntity();
        BeanUtils.copyProperties(dto,cart);

        ProductEntity product=productService.getById(dto.getProductId());
        cart.setProductName(product.getProductName());
        cart.setImageUrl(product.getImageUrl());
        cart.setAmount(product.getPrice());
        //TODO 登录功能后设置创建用户
        //TODO 登陆功能后绑定电脑机位
        cart.setCreateTime(LocalDateTime.now());
        shoppingCartMapper.add(cart);
    }

    @Override
    public void update(ShoppingCartDTO dto) {
        ShoppingCartEntity entity = new ShoppingCartEntity();
        BeanUtils.copyProperties(dto,entity);
        shoppingCartMapper.update(entity);
    }

    @Override
    public void delete(Long id) {
        shoppingCartMapper.delete(id);
    }

    @Override
    public List<ShoppingCartVO> list() {
        //TODO 登录功能完成后，获取当前用户id,根据userId查询购物车
        List<ShoppingCartVO> list = shoppingCartMapper.list();
        return list;
    }

    @Override
    public void clear() {
        Long userId= BaseContext.getCurrentUserId();
        shoppingCartMapper.clear(userId);
    }


}
