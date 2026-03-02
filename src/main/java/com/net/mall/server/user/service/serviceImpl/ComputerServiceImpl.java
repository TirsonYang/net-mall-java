package com.net.mall.server.user.service.serviceImpl;

import com.net.mall.server.user.mapper.ComputerMapper;
import com.net.mall.server.user.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    private ComputerMapper computerMapper;
    @Override
    public String queryByNum(String num) {
        return computerMapper.queryByNum(num);
    }
}
