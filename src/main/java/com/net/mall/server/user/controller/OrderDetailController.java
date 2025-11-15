package com.net.mall.server.user.controller;

import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.OrderDetailDTO;
import com.net.mall.server.user.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/orderDetail")
@Slf4j
public class OrderDetailController {

    @Autowired
    private OrderDetailService detailService;

    @PostMapping("/add")
    public Result addDetail(@RequestBody List<OrderDetailDTO> list){
        log.info("用户提交订单后生成订单详情：{}",list);
        detailService.addList(list);
        return Result.success();
    }

}
