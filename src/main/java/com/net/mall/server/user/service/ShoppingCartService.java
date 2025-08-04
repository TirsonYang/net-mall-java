package com.net.mall.server.user.service;

import com.net.mall.pojo.dto.ShoppingCartDTO;
import com.net.mall.pojo.vo.ShoppingCartVO;

import java.util.List;

public interface ShoppingCartService {
    void add(ShoppingCartDTO dto);

    void update(ShoppingCartDTO dto);

    void delete(Long id);

    List<ShoppingCartVO> list();

    void clear();
}
