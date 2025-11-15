package com.net.mall.server.user.service.serviceImpl;

import com.net.mall.pojo.dto.OrderDetailDTO;
import com.net.mall.pojo.entity.OrderDetailEntity;
import com.net.mall.pojo.vo.OrdersVO;
import com.net.mall.server.user.mapper.OrderDetailMapper;
import com.net.mall.server.user.service.OrderDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userOrderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrdersVO> list(Long userId) {
        return orderDetailMapper.list(userId);
    }

    @Override
    public void addList(List<OrderDetailDTO> list) {
//        orderDetailMapper.add(detailEntity);
        ArrayList<OrderDetailEntity> entities = new ArrayList<>();
        for (OrderDetailDTO dto : list) {
            OrderDetailEntity entity = new OrderDetailEntity();
            BeanUtils.copyProperties(dto,entity);
            entities.add(entity);
        }
        orderDetailMapper.addList(entities);
    }
}
