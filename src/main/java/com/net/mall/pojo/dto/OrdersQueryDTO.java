package com.net.mall.pojo.dto;

import java.time.LocalDateTime;

public class OrdersQueryDTO {
    /**
     * 会员id
     */
    private Long userId;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

}
