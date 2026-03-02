//package com.net.mall.common.config;
//
//
//import com.wechat.pay.java.core.Config;
//import com.wechat.pay.java.core.RSAAutoCertificateConfig;
//import com.wechat.pay.java.service.payments.nativepay.NativePayService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class WeChatConfig {
//
//    @Value("${wechat.merchantId}")
//    private String merchantId;
//
//    @Value("${wechat.privateKeyPath}")
//    private String privateKeyPath;
//
//    @Value("${wechat.merchantSerialNumber}")
//    private String merchantSerialNumber;
//
//    @Value("${wechat.apiV3key}")
//    private String apiV3key;
//
//    @Bean
//    public NativePayService nativePayService() {
//        Config config =
//                new RSAAutoCertificateConfig.Builder()
//                        .merchantId(merchantId)
//                        .privateKeyFromPath(privateKeyPath)
//                        .merchantSerialNumber(merchantSerialNumber)
//                        .apiV3Key(apiV3key)
//                        .build();
//         return new NativePayService.Builder().config(config).build();
//    }
//}
