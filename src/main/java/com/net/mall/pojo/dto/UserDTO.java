package com.net.mall.pojo.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
