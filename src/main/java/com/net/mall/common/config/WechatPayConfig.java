package com.net.mall.common.config;

import javax.annotation.PostConstruct;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.service.payments.h5.H5Service;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;

//TODO 微信支付
//@Configuration
@Getter
public class WechatPayConfig {

    /**
     * 商户号
     */
    @Value("${wechat.merchantId}")
    public String merchantId;
    /**
     * 商户API私钥路径
     */
    @Value("${wechat.privateKeyPath}")
    public String privateKeyPath;
    /**
     * 商户证书序列号
     */
    @Value("${wechat.merchantSerialNo}")
    public String merchantSerialNo;
    /**
     * 商户APIV3密钥
     */
    @Value("${wechat.apiV3Key}")
    public String apiV3Key;
    /**
     * AppId
     */
    @Value("${wechat.appId}")
    public String appId;

    private Config config;

    @PostConstruct
    public void initConfig() {
        // 使用自动更新平台证书的RSA配置
        // 一个商户号只能初始化一个配置，否则会因为重复的下载任务报错
        config = new RSAAutoCertificateConfig.Builder()
                .merchantId(merchantId)
                .privateKeyFromPath(privateKeyPath)
                .merchantSerialNumber(merchantSerialNo)
                .apiV3Key(apiV3Key)
                .build();
    }

    @Primary
    @Bean()
    public NativePayService nativePayService() {
        return new NativePayService.Builder()
                .config(config)
                .build();
    }

    @Primary
    @Bean
    public NotificationParser notificationParser() {
        return new NotificationParser((NotificationConfig) config);
    }

}
