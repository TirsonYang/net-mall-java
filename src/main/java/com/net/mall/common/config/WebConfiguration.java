package com.net.mall.common.config;

import com.net.mall.server.admin.interceptor.AdminLoginInterceptor;
import com.net.mall.server.boss.interceptor.BossLoginInterceptor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Slf4j
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Value("${pictureFile.path}")
    private String picturePath;

    @Value("${pictureFile.path-mapping}")
    private String picturePath_mapping;

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;
    @Autowired
    private BossLoginInterceptor bossLoginInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
//        log.info("开始注册admin自定义拦截器...");
//        registry.addInterceptor(adminLoginInterceptor)
//                .addPathPatterns("/admin/**")
//                .excludePathPatterns("/admin/user/login");
//
//        log.info("开始注册boss自定义拦截器...");
//        registry.addInterceptor(bossLoginInterceptor)
//                .addPathPatterns("/boss/**")
//                .excludePathPatterns("/boss/user/login");
    }

    // 3. 新增：添加静态资源映射（解决图片访问404）
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        // 临时：强制映射 /myImg/test.png 到一个绝对路径的图片（如桌面的 test.png）
        String testImgPath = "file:C:/Users/ytx/Desktop/test.png";
        log.info("临时强制映射：/myImg/test.png -> {}", testImgPath);

        registry.addResourceHandler("/myImg/test.png")
                .addResourceLocations(testImgPath);
    }



}
