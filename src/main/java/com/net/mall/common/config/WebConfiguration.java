package com.net.mall.common.config;

import com.net.mall.server.admin.interceptor.AdminLoginInterceptor;
import com.net.mall.server.boss.interceptor.BossLoginInterceptor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Slf4j
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;
    @Autowired
    private BossLoginInterceptor bossLoginInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册admin自定义拦截器...");
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/user/login");

        log.info("开始注册boss自定义拦截器...");
        registry.addInterceptor(bossLoginInterceptor)
                .addPathPatterns("/boss/**")
                .excludePathPatterns("/boss/user/login");

//        registry.addInterceptor(jwtTokenUserInterceptor)
//                .addPathPatterns("/user/**")
//                .excludePathPatterns("/user/user/login")
//                .excludePathPatterns("/user/shop/status");
    }



}
