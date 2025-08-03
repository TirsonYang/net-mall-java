package com.net.mall.server.admin.controller;


import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.vo.OrderDetailVO;
import com.net.mall.server.admin.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/orderDetail")
@Slf4j
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query, @RequestParam Long orderId){
        log.info("前台分页查询订单详情：{}{}",orderId,query);
        PageResult page=orderDetailService.page(query,orderId);
        return Result.success(page);
    }

    @GetMapping("/list")
    public Result<List<OrderDetailVO>> list(@RequestParam Long orderId){
        log.info("前台查询订单详情：{}",orderId);
        List<OrderDetailVO> list=orderDetailService.list(orderId);
        return Result.success(list);
    }

}
