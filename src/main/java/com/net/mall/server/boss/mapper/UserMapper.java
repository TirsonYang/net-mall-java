package com.net.mall.server.boss.mapper;


import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("bossUserMapper")
public interface UserMapper {

    UserEntity login(UserDTO dto);
}
