package com.net.mall.server.admin.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.pojo.dto.OrdersQueryDTO;
import com.net.mall.pojo.vo.OrdersVO;
import com.net.mall.server.admin.mapper.OrdersMapper;
import com.net.mall.server.admin.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service("adminOrdersService")
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Override
    public PageResult page(PageQuery query) {
        PageHelper.startPage(query.getPage(),query.getPageSize());
        Page<OrdersVO> page= ordersMapper.page(query);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void finish(Long id) {
        LocalDateTime now=LocalDateTime.now();
        ordersMapper.finish(id,now);
    }

    @Override
    public List<OrdersVO> list(OrdersQueryDTO dto) {
        return ordersMapper.list(dto);
    }
}
