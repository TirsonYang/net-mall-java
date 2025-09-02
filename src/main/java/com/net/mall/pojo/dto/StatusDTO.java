package com.net.mall.pojo.dto;

import lombok.Data;

@Data
public class StatusDTO {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 要修改的状态
     */
    private Integer status;
}
