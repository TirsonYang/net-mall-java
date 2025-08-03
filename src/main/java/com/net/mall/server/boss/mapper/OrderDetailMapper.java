package com.net.mall.server.boss.mapper;

import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.vo.OrderDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    Page<OrderDetailVO> page(PageQuery query, Long orderId);

    List<OrderDetailVO> list(Long orderId);
}
