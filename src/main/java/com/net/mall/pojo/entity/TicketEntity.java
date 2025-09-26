package com.net.mall.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketEntity {

    /**
     * 优惠券ID
     */
    private Long ticketId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 优惠券状态(1未使用,2已使用,3已过期)
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 使用时间
     */
    private LocalDateTime useTime;

    /**
     * 失效时间
     */
    private LocalDateTime expireTime;

    /**
     * 订单编号
     */
    private String orderNum;
}
