package com.net.mall.server.boss.service;

import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.dto.UserPasswordDTO;
import com.net.mall.pojo.entity.UserEntity;

public interface UserService {
    UserEntity login(UserDTO dto);

    Integer updatePassword(UserPasswordDTO dto);
}
