package com.net.mall.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserEntity implements Serializable {

    /**
     *用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 类型(1超管、2管理)
     */
    private Integer type;

}
