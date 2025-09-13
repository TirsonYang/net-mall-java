package com.net.mall.common.properties;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class JwtProperty {
    @Value("${net.mall.jwt.boss-secret-key}")
    private String userSecretKey;
    @Value("${net.mall.jwt.boss-token-name}")
    private String userTokenName;
    @Value("${net.mall.jwt.boss-ttl}")
    private Long userTtl;

}
