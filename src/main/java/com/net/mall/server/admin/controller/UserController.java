package com.net.mall.server.admin.controller;


import com.net.mall.common.properties.JwtProperty;
import com.net.mall.common.result.Result;
import com.net.mall.common.utils.JwtUtil;
import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.entity.UserEntity;
import com.net.mall.pojo.vo.UserVO;
import com.net.mall.server.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * 用户管理
 */
@RestController("adminUserController")
@RequestMapping("/admin/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperty jwtProperty;

    /**
     * 前台登录
     * @param dto
     * @return
     */
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody UserDTO dto){

        UserEntity entity=userService.login(dto);

        if (entity==null){
            return Result.error("用户不存在");
        }

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("adminId", entity.getId());
        String token = JwtUtil.generateJWT(
                jwtProperty.getAdminSecretKey(),
                jwtProperty.getAdminTtl(),
                claims);

        UserVO vo =new UserVO();
        vo.setId(entity.getId());
        vo.setUsername(entity.getUsername());
        vo.setToken(token);

        return Result.success(vo);
    }

}
