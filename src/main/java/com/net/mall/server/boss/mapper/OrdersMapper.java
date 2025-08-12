package com.net.mall.server.boss.mapper;

import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.entity.OrdersEntity;
import com.net.mall.pojo.vo.OrdersVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Component("bossOrdersMapper")
public interface OrdersMapper {

    Page<OrdersVO> page(PageQuery query);

    void updateStatus(Long id, Integer status);

    List<OrdersEntity> getListByStatus(Integer status);

    List<OrdersVO> list(LocalDateTime startTime,LocalDateTime endTime);
}
