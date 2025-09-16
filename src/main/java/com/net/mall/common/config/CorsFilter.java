package com.net.mall.common.config;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// 注意：这个类必须被Spring扫描到（放在启动类的同级或子包下）
@Component
public class CorsFilter implements Filter {

    private final List<String> allowedOrigins = Arrays.asList(
            "http://localhost:16444",
            "http://10.198.152.96:16444",
            "http://6ebe67b6.r9.cpolar.cn"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        String origin = req.getHeader("Origin");

        if (origin==null||allowedOrigins.contains(origin)){
            res.setHeader("Access-Control-Allow-Origin", origin);
        }

//        // 1. 允许前端的源（必须是具体地址，不能用*）
//        res.setHeader("Access-Control-Allow-Origin", "http://localhost:16444");
        // 2. 允许的请求方法（必须包含OPTIONS）
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        // 3. 允许的请求头（包含文件上传所需的头）
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With, Accept, Authorization");
        // 4. 允许携带凭证（与前端with-credentials=true对应）
        res.setHeader("Access-Control-Allow-Credentials", "true");
        // 5. 预检请求的有效期（避免频繁发送OPTIONS）
        res.setHeader("Access-Control-Max-Age", "86400"); // 24小时

        // 关键：直接放行OPTIONS请求，不经过业务逻辑
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // 非OPTIONS请求继续执行
        chain.doFilter(request, response);
    }

    // 初始化和销毁方法可以空实现
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}
}