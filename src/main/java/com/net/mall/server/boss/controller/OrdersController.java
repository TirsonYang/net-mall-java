package com.net.mall.server.boss.controller;


import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.server.boss.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 高级管理订单管理控制器
 */
@RestController("bossOrdersController")
@RequestMapping("/boss/orders")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 分页查询
     * @param query
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query){
        log.info("分页查询：{}",query);
        PageResult page= ordersService.page(query);
        return Result.success(page);
    }

    /**
     * 修改订单状态
     * @param id
     * @param status
     * @return
     */
    @PostMapping("/updateStatus")
    public Result updateStatus(@RequestParam Long id,@RequestParam Integer status){
        ordersService.updateStatus(id,status);
        return Result.success();
    }

}
