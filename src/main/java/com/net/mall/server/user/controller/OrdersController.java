package com.net.mall.server.user.controller;


import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.OrdersCancelDTO;
import com.net.mall.pojo.dto.OrdersDTO;
import com.net.mall.pojo.vo.OrdersVO;
import com.net.mall.server.user.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController("userOrdersController")
@RequestMapping("/user/orders")
@Slf4j
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/list")
    public Result<List<OrdersVO>> list(@RequestParam (required = false) String orderNum,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startTime,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endTime,
                                       @RequestParam Long userId){
        log.info("boss查询订单列表,订单号:{},开始时间:{},结束时间:{}",orderNum,startTime,endTime);
        List<OrdersVO> list=ordersService.list(orderNum,startTime,endTime,userId);
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery pageQuery){
        log.info("会员订单分页查询：{}",pageQuery);
        PageResult page=ordersService.page(pageQuery);
        return Result.success(page);
    }

    @GetMapping("/detail")
    public Result<List<OrdersVO>> detail(@RequestParam Long id){
        log.info("会员订单详情：{}",id);
        List<OrdersVO> list=ordersService.detail(id);
        return Result.success(list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody OrdersDTO dto){
        log.info("会员订单新增：{}",dto);
        ordersService.add(dto);
        return Result.success();
    }

    @PostMapping("/cancel")
    public Result cancel(@RequestBody OrdersCancelDTO dto){
        log.info("会员订单取消：{}",dto);
        ordersService.cancel(dto);
        return Result.success();
    }

    //TODO 会员订单支付


}
