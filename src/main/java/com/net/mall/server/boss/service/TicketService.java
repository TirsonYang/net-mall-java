package com.net.mall.server.boss.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.TicketDTO;
import com.net.mall.pojo.vo.TicketVO;

import java.util.List;

public interface TicketService {
    void add(TicketDTO dto);

    List<TicketVO> listById(Long userId);

    PageResult pageById(Long userId, PageQuery query);

    void checkExpireTicket();
}
