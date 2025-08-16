package com.net.mall.server.user.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.OrdersCancelDTO;
import com.net.mall.pojo.dto.OrdersDTO;
import com.net.mall.pojo.entity.TicketEntity;
import com.net.mall.pojo.vo.OrdersVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrdersService {
    List<OrdersVO> list();

    PageResult page(PageQuery pageQuery);

    List<OrdersVO> detail(Long id);

    void add(OrdersDTO dto);

    void cancel(OrdersCancelDTO dto);

    void orderByTicket(TicketEntity ticket,String phone);
}
