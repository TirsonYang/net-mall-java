package com.net.mall.common.properties;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.service.payments.h5.H5Service;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;


//@Configuration
@Data
public class WechatProperty {
    /**
     * 商户号
     */
    @Value("${wechat.pay.merchantId}")
    public String merchantId;
    /**
     * 商户API私钥路径
     */
    @Value("${wechat.pay.privateKeyPath}")
    public String privateKeyPath;
    /**
     * 商户APIV3密钥
     */
    @Value("${wechat.pay.apikey}")
    public String apiV3Key;
    /**
     * AppId
     */
    @Value("${wechat.pay.appId}")
    public String appId;

    @Bean
    public NativePayService createService(){

        RSAAutoCertificateConfig config = new RSAAutoCertificateConfig.Builder()
                .merchantId(merchantId)
                .apiV3Key(apiV3Key)
                .privateKeyFromPath(privateKeyPath)
                .build();

        return new NativePayService.Builder().config(config).build();
    }

}
