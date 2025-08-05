package com.net.mall.server.admin.mapper;

import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.vo.OrdersVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Mapper
@Component("adminOrdersMapper")
public interface OrdersMapper {


    Page<OrdersVO> page(PageQuery query);

    void finish(Long id, LocalDateTime time);
}
