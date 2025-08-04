package com.net.mall.server.user.mapper;

import com.net.mall.pojo.dto.ShoppingCartDTO;
import com.net.mall.pojo.entity.ShoppingCartEntity;
import com.net.mall.pojo.vo.ShoppingCartVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("userShoppingCartMapper")
public interface ShoppingCartMapper {

    ShoppingCartEntity getByUserIdAndProductId(ShoppingCartDTO dto);

    void update(ShoppingCartEntity entity);

    void add(ShoppingCartEntity cart);

    void delete(Long id);

    List<ShoppingCartVO> list();

    void clear(Long userId);
}
