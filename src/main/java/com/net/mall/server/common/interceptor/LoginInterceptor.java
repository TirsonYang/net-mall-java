package com.net.mall.server.common.interceptor;

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
public class LoginInterceptor implements HandlerInterceptor{
    @Autowired
    private JwtProperty jwtProperty;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info("LoginInterceptor preHandle");
        // 打印日志 观察是否成功拦截
        log.info(request.toString());

        if ("OPTIONS".equals(request.getMethod())){
            return true;
        }
        if (!(handler instanceof HandlerInterceptor)){
            return true;
        }

        try {
            log.info("-------注册boss拦截器--------");
            String token=request.getHeader(jwtProperty.getUserTokenName());
            log.info("jwt校验：{}",token);
            Claims claims= JwtUtil.parseJWT(jwtProperty.getUserSecretKey(),token);
            Long userId=Long.valueOf(claims.get("userId").toString());
            log.info("userId:{}",userId);
            BaseContext.setCurrentUserId(userId);
            return true;
        } catch (NumberFormatException e) {
            log.info("jwt校验失败");
            response.setStatus(401);
            return false;
        }

    }
}
