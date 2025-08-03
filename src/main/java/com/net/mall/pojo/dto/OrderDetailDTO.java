package com.net.mall.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDetailDTO implements Serializable {

    /**
     * 订单详情id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片
     */
    private String imageUrl;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品总金额(quantity*price)
     */
    private BigDecimal amount;


}
