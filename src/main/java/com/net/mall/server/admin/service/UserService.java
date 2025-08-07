package com.net.mall.server.admin.service;

import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.entity.UserEntity;

public interface UserService {
    UserEntity login(UserDTO dto);
}
