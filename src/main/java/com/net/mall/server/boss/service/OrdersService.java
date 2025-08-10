package com.net.mall.server.boss.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.entity.OrdersEntity;
import com.net.mall.pojo.vo.OrdersVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OrdersService {
    PageResult page(PageQuery query);

    void updateStatus(Long id, Integer status);

    List<OrdersEntity> getListByStatus(Integer status);

    void export(HttpServletResponse response);

    List<OrdersVO> list();
}
