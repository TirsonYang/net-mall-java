package com.net.mall.server.common.service;

import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.dto.UserPasswordDTO;
import com.net.mall.pojo.entity.UserEntity;
import com.net.mall.pojo.vo.UserVO;

public interface LoginService {
    UserEntity login(UserDTO dto);

    void updatePassword(UserPasswordDTO dto);
}
