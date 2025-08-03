package com.net.mall.server.admin.mapper;

import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.vo.OrdersVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {


    Page<OrdersVO> page(PageQuery query);

    void finish(Long id);
}
