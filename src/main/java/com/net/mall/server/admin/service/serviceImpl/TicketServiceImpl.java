package com.net.mall.server.admin.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.TicketDTO;
import com.net.mall.pojo.entity.ProductEntity;
import com.net.mall.pojo.entity.TicketEntity;
import com.net.mall.pojo.vo.TicketVO;
import com.net.mall.server.admin.mapper.TicketMapper;
import com.net.mall.server.admin.service.ProductService;
import com.net.mall.server.admin.service.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("adminTicketService")
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private ProductService productService;

    @Override
    public void add(TicketDTO dto) {
        TicketEntity entity = new TicketEntity();
        BeanUtils.copyProperties(dto,entity);
        //TODO 修改
        entity.setUserId(dto.getUserId());
        ProductEntity product=productService.getById(dto.getProductId());
        entity.setProductName(product.getProductName());
        if (entity.getExpireTime().isBefore(LocalDateTime.now())){
            entity.setStatus(2);
        }else{
            entity.setStatus(0);
        }
        entity.setCreateTime(LocalDateTime.now());
        List<TicketEntity> list = new ArrayList<>();
        for (int i=0;i< dto.getNumber();i++) {
            list.add(entity);
        }
        ticketMapper.addBatch(list);
    }

    @Override
    public List<TicketVO> listById(Long userId) {
        List<TicketVO> list=ticketMapper.listById(userId);
        for (TicketVO vo : list) {
            if (vo.getExpireTime().isBefore(LocalDateTime.now())){
                updateStatus(vo.getTicketId(), 3);
                vo.setStatus(3);
            }
        }
        return list;
    }

    @Override
    public PageResult pageById(Long userId, PageQuery query) {
        PageHelper.startPage(query);
        Page<TicketVO> page=ticketMapper.pageById(userId,query);
        List<TicketVO> list=page.getResult();
        for (TicketVO vo : list) {
            if (vo.getExpireTime().isBefore(LocalDateTime.now())){
                updateStatus(vo.getTicketId(), 3);
                vo.setStatus(3);
            }
        }
        return new PageResult(page.getTotal(), list);
    }

    private void updateStatus(Long ticketId,Integer status){
        ticketMapper.updateStatus(ticketId,status);
    }


}
