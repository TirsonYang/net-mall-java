package com.net.mall.server.admin.interceptor;

import com.net.mall.common.context.BaseContext;
import com.net.mall.common.properties.JwtProperty;
import com.net.mall.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperty jwtProperty;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("LoginInterceptor preHandle");
        if ("OPTIONS".equals(request.getMethod())){
            return true;
        }
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        try {
            log.info("注册拦截器—————");
            String token = request.getHeader(jwtProperty.getAdminTokenName());
            log.info("jwt校验:{}",token);
            Claims claims = JwtUtil.parseJWT(jwtProperty.getAdminSecretKey(), token);
            Long adminId=Long.valueOf(claims.get("adminId").toString());
            log.info("adminId:{}",adminId);
            BaseContext.setCurrentUserId(adminId);
            return true;
        }
        catch (Exception e) {
            log.info("jwt校验失败");
            response.setStatus(401);     //返回异常状态码
            return false;
        }
    }

}
