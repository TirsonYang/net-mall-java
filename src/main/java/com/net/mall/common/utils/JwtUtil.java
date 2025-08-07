package com.net.mall.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    public static String generateJWT(String secretKey, long ttl, Map<String,Object> claims){
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        long expMills=System.currentTimeMillis()+ttl;
        Date exp=new Date(expMills);

        JwtBuilder jwtBuilder= Jwts.builder()
                .setExpiration(exp)
                .setClaims(claims)
                .signWith(signatureAlgorithm,secretKey.getBytes(StandardCharsets.UTF_8));

        return jwtBuilder.compact();
    }

    public static Claims parseJWT(String secretKey,String token){
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

}
