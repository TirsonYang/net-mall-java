package com.net.mall.server.boss.controller;


import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.vo.OrderDetailVO;
import com.net.mall.server.boss.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 高级管理订单详情控制器
 */

@RestController
@RequestMapping("/boss/orderDetail")
@Slf4j
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;


    /**
     * 根据订单id分页查询
     * @param query
     * @param orderId
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query, @RequestParam Long orderId){
        log.info("分页查询：{}、{}",query,orderId);
        PageResult page=orderDetailService.page(query,orderId);
        return Result.success(page);
    }


    /**
     * 根据订单id列表查询
     * @param orderId
     * @return
     */
    @GetMapping("/list")
    public Result<List<OrderDetailVO>> list(@RequestParam Long orderId){
        log.info("查询：{}",orderId);
        List<OrderDetailVO> list=orderDetailService.list(orderId);
        return Result.success(list);
    }


}
