package com.net.mall.pojo.dto;

import lombok.Data;

@Data
public class UpdateStockDTO {
    /**
     * 商品id
     */
    private Long id;

    /**
     * 库存
     */
    private Integer stock;

}
