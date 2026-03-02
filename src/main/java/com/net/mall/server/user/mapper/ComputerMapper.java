package com.net.mall.server.user.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComputerMapper {
    String queryByNum(String num);
}
