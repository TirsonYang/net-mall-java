package com.net.mall.server.user.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.DetailQueryDTO;
import com.net.mall.pojo.dto.OrdersCancelDTO;
import com.net.mall.pojo.dto.OrdersDTO;
import com.net.mall.pojo.entity.TicketEntity;
import com.net.mall.pojo.vo.OrderDetailVO;
import com.net.mall.pojo.vo.OrdersVO;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public interface OrdersService {
    List<OrdersVO> list(String orderNum, LocalDateTime startTime, LocalDateTime endTime, Long userId);

    PageResult page(PageQuery pageQuery);

    List<OrdersVO> detail(Long id);

    Long add(OrdersDTO dto);

    void cancel(OrdersCancelDTO dto);

    String orderByTicket(TicketEntity ticket,String phone,String remark,Long userId);

    String alipayCreate(Long orderId);

    void handleAlipayNotify(HttpServletRequest request);

    List<OrderDetailVO> getByOrderNum(DetailQueryDTO dto);



    // TODO 微信支付
//    Map wechatCreate(Long orderId);
}
