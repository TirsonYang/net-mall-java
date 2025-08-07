package com.net.mall.server.boss.service.serviceImpl;

import com.net.mall.common.exception.BaseException;
import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.entity.UserEntity;
import com.net.mall.server.boss.mapper.UserMapper;
import com.net.mall.server.boss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("bossUserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity login(UserDTO dto) {

        UserEntity entity=userMapper.login(dto);

        if (entity==null){
            throw new BaseException("用户不存在");
        }

        if (!Objects.equals(entity.getPassword(), dto.getPassword())){
            throw new BaseException("密码错误");
        }

        return entity;

    }
}
