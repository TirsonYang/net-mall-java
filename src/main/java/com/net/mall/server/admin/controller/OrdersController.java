package com.net.mall.server.admin.controller;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.server.admin.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/orders")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query){
        log.info("前台分页查询已付款的订单：{}",query);
        PageResult page=ordersService.page(query);
        return Result.success(page);
    }

    @GetMapping("/finish")
    public Result finish(@RequestParam Long id){
        log.info("前台确认送达：{}",id);
        ordersService.finish(id);
        return Result.success();
    }

}
