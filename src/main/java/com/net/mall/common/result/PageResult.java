package com.net.mall.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable {

    /**
     * 总记录数
     */
    private long total;

    /**
     * 查询列表
     */
    private List records;


}
