package com.net.mall.server.admin.service.serviceImpl;

import com.net.mall.common.exception.BaseException;
import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.entity.UserEntity;
import com.net.mall.server.admin.mapper.UserMapper;
import com.net.mall.server.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;


@Service("adminUserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity login(UserDTO dto) {

        UserEntity userEntity = userMapper.login(dto);

        if (userEntity==null){
            throw new BaseException("用户不存在");
        }

//        String password = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes());

        if (!Objects.equals(userEntity.getPassword(), dto.getPassword())){
            throw new BaseException("密码错误");
        }

        return userEntity;
    }
}
