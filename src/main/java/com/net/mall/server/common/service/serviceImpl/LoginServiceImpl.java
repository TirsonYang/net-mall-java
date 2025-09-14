package com.net.mall.server.common.service.serviceImpl;

import com.net.mall.common.exception.BaseException;
import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.dto.UserPasswordDTO;
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

    @Override
    public void updatePassword(UserPasswordDTO dto) {
        UserEntity entity=loginMapper.selectByUsername(dto.getUsername());
        if (entity==null){
            throw new BaseException("用户不存在");
        }
        if (!entity.getPassword().equals(dto.getOldPassword())){
            throw new BaseException("密码错误");
        }
        entity.setPassword(dto.getNewPassword());
        loginMapper.update(entity);
    }
}
