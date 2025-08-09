package com.net.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NetMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(NetMallApplication.class, args);
    }
}
