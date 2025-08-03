package com.net.mall.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrdersEntity implements Serializable {

    /**
     * 订单id
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 订单状态(1待付款、2已付款、3已完成、4已取消)
     */
    private Integer status;

    /**
     * 下单用户id
     */
    private Long userId;

    /**
     * 电脑机位id
     */
    private Long computerId;

    /**
     * 下单时间
     */
    private LocalDateTime orderTime;

    /**
     * 支付时间
     */
    private LocalDateTime checkoutTime;

    /**
     * 支付方式(1微信、2支付宝)
     */
    private Integer payMethod;

    /**
     * 实际总价
     */
    private BigDecimal total;

    /**
     * 优惠金额
     */
    private BigDecimal preference;

    /**
     * 订单总金额
     */
    private BigDecimal amount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 下单手机号
     */
    private String phone;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 送达时间
     */
    private LocalDateTime deliveryTime;


}
