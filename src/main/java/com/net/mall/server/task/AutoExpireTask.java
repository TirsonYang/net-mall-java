package com.net.mall.server.task;


import com.net.mall.server.boss.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class AutoExpireTask {

    @Autowired
    private TicketService ticketService;

    // 定时任务：每隔6个小时统一修改过期优惠券
    @Scheduled(initialDelay = 0, fixedRate = 3600000)
    public void executeTask() {
        log.info("{}:任务:检查过期优惠券并修改状态：<未使用1>-><已过期3>", LocalDateTime.now());
        ticketService.checkExpireTicket();
    }


}
