package com.net.mall.server.boss.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.TicketDTO;
import com.net.mall.pojo.entity.ProductEntity;
import com.net.mall.pojo.entity.TicketEntity;
import com.net.mall.pojo.vo.TicketVO;
import com.net.mall.server.boss.mapper.TicketMapper;
import com.net.mall.server.boss.service.ProductService;
import com.net.mall.server.boss.service.TicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("bossTicketService")
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private ProductService productService;

    @Override
    public void add(TicketDTO dto) {
        TicketEntity entity = new TicketEntity();
        BeanUtils.copyProperties(dto,entity);
        ProductEntity product=productService.getById(dto.getProductId());
        entity.setProductName(product.getProductName());
        entity.setCreateTime(LocalDateTime.now());
        if (entity.getExpireTime().isBefore(LocalDateTime.now())){
            entity.setStatus(2);
        }else {
            entity.setStatus(0);
        }
        ticketMapper.add(entity);
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
        List<TicketVO> list = page.getResult();
        for (TicketVO vo : list) {
            if (vo.getExpireTime().isBefore(LocalDateTime.now())){
                updateStatus(vo.getTicketId(), 3);
                vo.setStatus(3);
            }
        }
        return new PageResult(page.getTotal(), list);
    }

    @Override
    public void checkExpireTicket() {
        List<TicketEntity> list=ticketMapper.listByStatus(1);
        for (TicketEntity entity : list) {
            if (entity.getExpireTime().isBefore(LocalDateTime.now())){
                updateStatus(entity.getTicketId(),3);
            }
        }
    }

    private void updateStatus(Long ticketId,Integer status){
        ticketMapper.updateStatus(ticketId,status);
    }
}
