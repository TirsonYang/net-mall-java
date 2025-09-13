package com.net.mall.server.common.controller;

import com.net.mall.common.properties.JwtProperty;
import com.net.mall.common.result.Result;
import com.net.mall.common.utils.JwtUtil;
import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.entity.UserEntity;
import com.net.mall.pojo.vo.UserVO;
import com.net.mall.server.common.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtProperty jwtProperty;

    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody UserDTO dto) {
        UserEntity entity=loginService.login(dto);

        if (entity==null){
            return Result.error("用户不存在");
        }
        Map<String,Object> claims=new HashMap<>();
        claims.put("bossId",entity.getId());
        String token= JwtUtil.generateJWT(jwtProperty.getUserSecretKey()
                ,jwtProperty.getUserTtl()
                ,claims);
        UserVO vo=new UserVO();
        vo.setToken(token);
        vo.setId(entity.getId());
        vo.setUsername(entity.getUsername());

        return Result.success(vo);
    }
}
