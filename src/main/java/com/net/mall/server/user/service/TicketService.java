package com.net.mall.server.user.service;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.UseTicketDTO;
import com.net.mall.pojo.vo.TicketVO;

import java.util.List;

public interface TicketService {
    PageResult pageById(PageQuery query);

    List<TicketVO> listById(Long userId);

    Integer useTicket(UseTicketDTO dto);
}
