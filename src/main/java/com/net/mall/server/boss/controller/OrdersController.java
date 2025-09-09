package com.net.mall.server.boss.controller;


import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.StatusDTO;
import com.net.mall.pojo.vo.OrdersVO;
import com.net.mall.server.boss.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

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
     * @param dto
     * @return
     */
    @PostMapping("/updateStatus")
    public Result updateStatus(@RequestBody StatusDTO dto){
        ordersService.updateStatus(dto.getId(),dto.getStatus());
        return Result.success();
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response,@RequestParam(required = false)String orderNum,
                       @RequestParam(required = false) LocalDateTime startTime,@RequestParam(required = false) LocalDateTime endTime){
        ordersService.export(response,orderNum,startTime,endTime);
    }

    @GetMapping("/list")
    public Result<List<OrdersVO>> list(@RequestParam (required = false) String orderNum,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startTime,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endTime){
        log.info("boss查询订单列表,订单号:{},开始时间:{},结束时间:{}",orderNum,startTime,endTime);
        List<OrdersVO> list=ordersService.list(orderNum,startTime,endTime);
        return Result.success(list);

    }

}
