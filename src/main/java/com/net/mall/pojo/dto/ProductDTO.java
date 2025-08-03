package com.net.mall.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductDTO implements Serializable {
    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品分类id
     */
    private Long categoryId;

    /**
     * 商品状态
     */
    private Integer status;

    /**
     * 商品图片路径
     */
    private String imageUrl;

    /**
     * 商品库存
     */
    private Integer stock;

}
