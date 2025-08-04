package com.net.mall.server.boss.mapper;

import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.vo.OrdersVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("bossOrdersMapper")
public interface OrdersMapper {

    Page<OrdersVO> page(PageQuery query);

    void updateStatus(Long id, Integer status);
}
