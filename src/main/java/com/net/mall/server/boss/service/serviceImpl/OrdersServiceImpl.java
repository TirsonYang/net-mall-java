package com.net.mall.server.boss.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.utils.ExcelUtil;
import com.net.mall.pojo.entity.OrdersEntity;
import com.net.mall.pojo.vo.OrdersVO;
import com.net.mall.server.boss.mapper.OrdersMapper;
import com.net.mall.server.boss.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

@Service("bossOrdersService")
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public PageResult page(PageQuery query) {
        PageHelper.startPage(query.getPage(),query.getPageSize());
        Page<OrdersVO> page=ordersMapper.page(query);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        ordersMapper.updateStatus(id,status);
    }

    @Override
    public List<OrdersEntity> getListByStatus(Integer status) {
        return ordersMapper.getListByStatus(status);
    }



    @Override
    public void export(HttpServletResponse response, String startTime,String endTime) {
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);
        List<OrdersVO> list = list(start,end);
        log.info("list:{}",list);
        ExcelUtil.export(response,list,start,end);
    }

    @Override
    public List<OrdersVO> list(LocalDateTime startTime,LocalDateTime endTime){
        return ordersMapper.list(startTime,endTime);
    }


}
