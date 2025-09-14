package com.net.mall.server.admin.mapper;


import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.dto.TicketDTO;
import com.net.mall.pojo.entity.TicketEntity;
import com.net.mall.pojo.vo.TicketVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("adminTicketMapper")
public interface TicketMapper {


    void add(TicketEntity entity);

    List<TicketVO> listById(Long userId);

    Page<TicketVO> pageById(Long userId, PageQuery query);

    void updateStatus(Long ticketId, Integer status);
}
