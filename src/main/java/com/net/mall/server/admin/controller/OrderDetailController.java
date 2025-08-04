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


/**
 * 前台端订单详情控制器
 */
@RestController("adminOrderDetailController")
@RequestMapping("/admin/orderDetail")
@Slf4j
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 前台端分页订单详情
     * @param query
     * @param orderId
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query, @RequestParam Long orderId){
        log.info("前台分页查询订单详情：{}{}",orderId,query);
        PageResult page=orderDetailService.page(query,orderId);
        return Result.success(page);
    }

    /**
     * 前台端列表查询订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/list")
    public Result<List<OrderDetailVO>> list(@RequestParam Long orderId){
        log.info("前台查询订单详情：{}",orderId);
        List<OrderDetailVO> list=orderDetailService.list(orderId);
        return Result.success(list);
    }

}
