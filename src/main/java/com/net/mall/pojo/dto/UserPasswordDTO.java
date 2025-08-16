package com.net.mall.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPasswordDTO implements Serializable {

    private String username;

    private String oldPassword;

    private String newPassword;

}
