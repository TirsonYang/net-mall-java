package com.net.mall.server.boss.service.serviceImpl;

import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.dto.UserPasswordDTO;
import com.net.mall.pojo.entity.UserEntity;
import com.net.mall.server.boss.mapper.UserMapper;
import com.net.mall.server.boss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bossUserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity login(UserDTO dto) {
        return userMapper.login(dto);

    }

    @Override
    public Integer updatePassword(UserPasswordDTO dto) {
        UserEntity entity = userMapper.findByUsername(dto.getUsername());
        if (entity==null){
            return 1;
        }
        if (!entity.getPassword().equals(dto.getOldPassword())){
            return 2;
        }
        UserDTO user= new UserDTO();
        user.setPassword(dto.getNewPassword());
        user.setUsername(dto.getUsername());
        userMapper.update(user);
        return 3;
    }
}
