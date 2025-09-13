package com.net.mall.server.common.service.serviceImpl;

import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.entity.UserEntity;
import com.net.mall.server.common.mapper.LoginMapper;
import com.net.mall.server.common.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;


    @Override
    public UserEntity login(UserDTO dto) {
        return loginMapper.login(dto);
    }
}
