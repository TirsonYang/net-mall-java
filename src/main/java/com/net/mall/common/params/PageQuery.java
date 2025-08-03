package com.net.mall.common.params;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageQuery implements Serializable {

    private int page;

    private int pageSize;

}
