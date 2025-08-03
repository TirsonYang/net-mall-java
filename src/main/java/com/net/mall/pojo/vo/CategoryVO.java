package com.net.mall.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryVO implements Serializable {

    private Long id;

    private String categoryName;

    private String description;

}
