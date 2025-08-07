package com.net.mall.common.properties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtProperty {

    @Value("${net.mall.jwt.admin-secret-key}")
    private String adminSecretKey;
    @Value("${net.mall.jwt.admin-token-name}")
    private String adminTokenName;
    @Value("${net.mall.jwt.admin-ttl}")
    private Long adminTtl;


    @Value("${net.mall.jwt.boss-secret-key}")
    private String bossSecretKey;
    @Value("${net.mall.jwt.boss-token-name}")
    private String bossTokenName;
    @Value("${net.mall.jwt.boss-ttl}")
    private Long bossTtl;

}
