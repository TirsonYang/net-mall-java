package com.net.mall.server.common.mapper;


import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    UserEntity login(UserDTO dto);

    UserEntity selectByUsername(String username);

    void update(UserEntity entity);
}
