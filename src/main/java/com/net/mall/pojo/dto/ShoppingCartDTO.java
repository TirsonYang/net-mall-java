package com.net.mall.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ShoppingCartDTO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片
     */
    private String imageUrl;

    /**
     * 商品数量
     */
    private Integer number;

    /**
     * 商品单价
     */
    private BigDecimal price;

}
