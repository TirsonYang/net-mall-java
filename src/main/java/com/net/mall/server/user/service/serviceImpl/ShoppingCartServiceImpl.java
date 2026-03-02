package com.net.mall.server.user.service.serviceImpl;

import com.net.mall.common.context.BaseContext;
import com.net.mall.pojo.dto.ShoppingCartDTO;
import com.net.mall.pojo.entity.ShoppingCartEntity;
import com.net.mall.pojo.vo.ShoppingCartVO;
import com.net.mall.server.user.mapper.ShoppingCartMapper;
import com.net.mall.server.user.service.ProductService;
import com.net.mall.server.user.service.ShoppingCartService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("userShoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public void add(ShoppingCartDTO dto) {
        ShoppingCartEntity entity = shoppingCartMapper.getByUserIdAndProductId(dto);

        if (entity!=null){
            // 如果购物车中已存在该商品，则增加商品数量
            entity.setNumber(entity.getNumber()+1);
            shoppingCartMapper.update(entity);
            return; // 处理完直接返回，避免继续执行添加逻辑
        }

        ShoppingCartEntity cart = new ShoppingCartEntity();
        BeanUtils.copyProperties(dto,cart);

//        ProductEntity product=productService.getById(dto.getProductId());
//        cart.setProductName(product.getProductName());
//        cart.setImageUrl(product.getImageUrl());
//        cart.setPrice(product.getPrice());
        if (cart.getComputerId()==null|| Strings.isEmpty(cart.getComputerId())){
            throw new RuntimeException("请选择电脑机位");
        }
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
    public void delete(Long id, String computerId) {
        ShoppingCartEntity entity = shoppingCartMapper.getById(id);
        if(!entity.getComputerId().equals(computerId)){
            throw new RuntimeException("参数错误");
        }
        shoppingCartMapper.delete(id);
    }

    @Override
    public List<ShoppingCartVO> list(String computerId) {
        if (computerId ==null||Strings.isEmpty(computerId)){
            throw new RuntimeException("请选择电脑机位");
        }
        return shoppingCartMapper.list(computerId);
    }

    @Override
    public void clear(String computerId) {
        shoppingCartMapper.clear(computerId);
    }


}
