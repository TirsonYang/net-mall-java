package com.net.mall.server.task;

import com.net.mall.pojo.dto.OrdersDTO;
import com.net.mall.pojo.entity.OrdersEntity;
import com.net.mall.server.boss.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class AutoReceiveTask {

    @Autowired
    private OrdersService ordersService;

    //定时任务:每天凌晨3点将订单状态为<已送达>改为<已完成>
    @Scheduled(cron = "0 0 3 * * ? ")
    public void executeTask(){
        log.info("{}:任务:将订单状态为<已送达>改为<已完成>", LocalDateTime.now());
        List<OrdersEntity> list=ordersService.getListByStatus(3);
        for (OrdersEntity entity : list) {
            ordersService.updateStatus(entity.getId(),4);
        }
    }
}
