package com.net.mall.server.user.mapper;

import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.dto.OrdersCancelDTO;
import com.net.mall.pojo.entity.OrdersEntity;
import com.net.mall.pojo.vo.OrdersVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Component("userOrdersMapper")
public interface OrdersMapper {

    List<OrdersVO> list(String orderNum,LocalDateTime startTime, LocalDateTime endTime,Long userId);

    Page<OrdersVO> page(PageQuery pageQuery, Long userId);

    void add(OrdersEntity entity);

    void cancel(OrdersCancelDTO dto, LocalDateTime cancelTime);
}
