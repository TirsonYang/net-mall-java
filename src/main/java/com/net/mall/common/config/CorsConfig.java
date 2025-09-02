package com.net.mall.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")                      // 所有接口
                        .allowedOrigins("http://localhost:16444") // 你的前端地址
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 必须包含 OPTIONS
                        .allowedHeaders("*")                    // 允许所有请求头
                        .allowCredentials(false)                 // 如需要 cookie / token，设为 true
                        .maxAge(360000);                          // 预检请求缓存时间
            }
        };
    }
}
