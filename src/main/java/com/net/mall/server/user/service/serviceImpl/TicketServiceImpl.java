package com.net.mall.server.user.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.context.BaseContext;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.entity.TicketEntity;
import com.net.mall.pojo.vo.TicketVO;
import com.net.mall.server.user.mapper.TicketMapper;
import com.net.mall.server.user.service.OrdersService;
import com.net.mall.server.user.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("userTicketService")
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private OrdersService ordersService;
    @Override
    public PageResult pageById(PageQuery query) {
        PageHelper.startPage(query);
        Long userId = BaseContext.getCurrentUserId();
        Page<TicketVO> page = ticketMapper.pageById(query,userId);
        List<TicketVO> result = page.getResult();
        List<TicketVO> resList = new ArrayList<>();
        for (TicketVO vo : result) {
            if (vo.getExpireTime().isBefore(LocalDateTime.now())){
                ticketMapper.updateStatus(vo.getTicketId(),3);
                continue;
            }
            resList.add(vo);
        }
        return new PageResult(resList.size(), resList);
    }

    @Override
    public List<TicketVO> listById() {
        Long userId = BaseContext.getCurrentUserId();

        List<TicketVO> list = ticketMapper.listById(userId);
        List<TicketVO> resList = new ArrayList<>();
        for (TicketVO vo : list) {
            if (vo.getExpireTime().isBefore(LocalDateTime.now())){
                ticketMapper.updateStatus(vo.getTicketId(),3);
                continue;
            }
            resList.add(vo);
        }

        return resList;
    }

    @Override
    public Integer useTicket(Long ticketId,String phone) {
        TicketEntity ticket=ticketMapper.getById(ticketId);
        if (ticketMapper ==null){
            return 1;
        }
        if (ticket.getStatus()!=1){
            return 2;
        }
        if (ticket.getExpireTime().isBefore(LocalDateTime.now())){
            ticketMapper.updateStatus(ticketId,3);
            return 3;
        }
        ticketMapper.updateStatus(ticketId,2);
        ordersService.orderByTicket(ticket,phone);
        return 0;
    }
}
