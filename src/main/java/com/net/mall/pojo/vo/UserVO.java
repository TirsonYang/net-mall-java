package com.net.mall.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * jwt令牌
     */
    private String token;

}
