package com.net.mall.server.boss.interceptor;

import com.net.mall.common.context.BaseContext;
import com.net.mall.common.properties.JwtProperty;
import com.net.mall.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@Slf4j
public class BossLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperty jwtProperty;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info("LoginInterceptor preHandle");
        if ("OPTIONS".equals(request.getMethod())){
            return true;
        }
        if (!(handler instanceof HandlerInterceptor)){
            return true;
        }

        try {
            log.info("-------注册boss拦截器--------");
            String token=request.getHeader(jwtProperty.getBossTokenName());
            log.info("jwt校验：{}",token);
            Claims claims= JwtUtil.parseJWT(jwtProperty.getBossSecretKey(),token);
            Long bossId=Long.valueOf(claims.get("bossId").toString());
            log.info("bossId:{}",bossId);
            BaseContext.setCurrentUserId(bossId);
            return true;
        } catch (NumberFormatException e) {
            log.info("jwt校验失败");
            response.setStatus(401);
            return false;
        }

    }

}
