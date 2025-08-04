package com.net.mall.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ShoppingCartDTO implements Serializable {

    private Long id;

    private Long userId;

    private Long computerId;

    private BigDecimal amount;


}
