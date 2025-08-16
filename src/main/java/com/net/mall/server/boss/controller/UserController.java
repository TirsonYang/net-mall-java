package com.net.mall.server.boss.controller;

import com.net.mall.common.properties.JwtProperty;
import com.net.mall.common.result.Result;
import com.net.mall.common.utils.JwtUtil;
import com.net.mall.pojo.dto.UserDTO;
import com.net.mall.pojo.dto.UserPasswordDTO;
import com.net.mall.pojo.entity.UserEntity;
import com.net.mall.pojo.vo.UserVO;
import com.net.mall.server.boss.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * 超管登录
     * @param dto
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody UserDTO dto, HttpServletRequest request){
        UserEntity entity=userService.login(dto);

        if (entity==null){
            return Result.error("用户不存在");
        }
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

    /**
     * 超管登出
     * @return
     */
    @PostMapping("/logout")
    public Result logout(){
        return Result.success();
    }


    /**
     * 修改密码
     * @param dto
     * @return
     */
    @PostMapping("/updateBossPassword")
    public Result updateBossPassword(@RequestParam UserPasswordDTO dto){
        log.info("修改密码");
        Integer i=userService.updatePassword(dto);
        switch (i){
            case 1:
                return Result.error("用户不存在！");
            case 2:
                return Result.error("密码错误！");
            default:
                return Result.success();
        }
    }

}
