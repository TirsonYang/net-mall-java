package com.net.mall.server.user.mapper;


import com.net.mall.pojo.entity.OrderDetailEntity;
import com.net.mall.pojo.vo.OrdersVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("userOrderDetailMapper")
public interface OrderDetailMapper {
    List<OrdersVO> list(Long userId);

    void add(OrderDetailEntity detailEntity);
}
