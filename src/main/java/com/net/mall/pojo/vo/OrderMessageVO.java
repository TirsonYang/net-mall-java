package com.net.mall.pojo.vo;

import lombok.Data;

@Data
public class OrderMessageVO {

    private String type;

    private String content;

    private String orderNum;

    private Long timestamp;

}
