package com.net.mall.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProductVO implements Serializable {

    private Long id;

    private String productName;

    private String description;

    private BigDecimal price;

    private String imageUrl;

    private Integer stock;


}
