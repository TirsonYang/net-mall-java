package com.net.mall.server.boss.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.vo.OrderDetailVO;
import com.net.mall.server.boss.mapper.OrderDetailMapper;
import com.net.mall.server.boss.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public PageResult page(PageQuery query, Long orderId) {
        PageHelper.startPage(query.getPage(),query.getPageSize());
        Page<OrderDetailVO> page=orderDetailMapper.page(query,orderId);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public List<OrderDetailVO> list(Long orderId) {
        return orderDetailMapper.list(orderId);
    }
}
