package com.net.mall.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ShoppingCartEntity implements Serializable {

    /**
     * id
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
     * 商品数量
     */
    private Integer number;

    /**
     * 商品单价
     */
    private BigDecimal amount;

    /**
     * 会员id
     */
    private Long userId;

    /**
     * 电脑id
     */
    private Long computerId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
