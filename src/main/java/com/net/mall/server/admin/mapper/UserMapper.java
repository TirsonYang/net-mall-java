package com.net.mall.server.admin.mapper;

import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    UserEntity login(UserDTO dto);
}
