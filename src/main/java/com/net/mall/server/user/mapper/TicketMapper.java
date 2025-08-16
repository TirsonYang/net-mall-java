package com.net.mall.server.user.mapper;

import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.entity.TicketEntity;
import com.net.mall.pojo.vo.TicketVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("userTicketMapper")
public interface TicketMapper {

    Page<TicketVO> pageById(PageQuery query, Long userId);

    List<TicketVO> listById(Long userId);

    TicketEntity getById(Long ticketId);

    void updateStatus(Long ticketId, Integer status);
}
