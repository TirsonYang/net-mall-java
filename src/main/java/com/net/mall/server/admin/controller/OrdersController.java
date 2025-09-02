package com.net.mall.server.admin.controller;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.OrdersQueryDTO;
import com.net.mall.pojo.vo.OrdersVO;
import com.net.mall.server.admin.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前台端订单管理控制器
 */
@RestController("adminOrdersController")
@RequestMapping("/admin/orders")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


    /**
     * 前台端分页查询已付款订单
     * @param query
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query){
        log.info("前台分页查询已付款的订单：{}",query);
        PageResult page=ordersService.page(query);
        return Result.success(page);
    }

    /**
     * 前台端确认送达
     * @param id
     * @return
     */
    @GetMapping("/finish")
    public Result finish(@RequestParam Long id){
        log.info("前台确认送达：{}",id);
        ordersService.finish(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<OrdersVO>> list(@RequestParam(required = false) OrdersQueryDTO dto){
        log.info("admin查询订单列表:{}",dto);
        List<OrdersVO> list=ordersService.list(dto);
        return Result.success(list);
    }

}
