package com.net.mall.server.admin.controller;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.server.admin.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    //TODO 待完善： 前端确认送达后，每隔一定时间自动将订单状态从已送达改为已完成，springTask
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

}
