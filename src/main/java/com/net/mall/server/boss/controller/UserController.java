package com.net.mall.server.boss.controller;

import com.net.mall.common.properties.JwtProperty;
import com.net.mall.common.result.Result;
import com.net.mall.common.utils.JwtUtil;
import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.entity.UserEntity;
import com.net.mall.pojo.vo.UserVO;
import com.net.mall.server.boss.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController("bossUserController")
@RequestMapping("/boss/user")
public class UserController implements Serializable{

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperty jwtProperty;

    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody UserDTO dto, HttpServletRequest request){
        UserEntity entity=userService.login(dto);

        if (entity==null){
            return Result.error("用户不存在");
        }

//        HttpSession session = request.getSession();
//
//        session.setAttribute("boss","BOSS");

        Map<String,Object> claims=new HashMap<>();
        claims.put("bossId",entity.getId());
        String token= JwtUtil.generateJWT(jwtProperty.getBossSecretKey()
                ,jwtProperty.getBossTtl()
                ,claims);
        UserVO vo=new UserVO();
        vo.setToken(token);
        vo.setId(entity.getId());
        vo.setUsername(entity.getUsername());

        return Result.success(vo);

    }


    @PostMapping("/logout")
    public Result logout(){
        return Result.success();
    }

}
