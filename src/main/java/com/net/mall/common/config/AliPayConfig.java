package com.net.mall.common.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliPayConfig {

    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHxrVivDwb3woa/uyl3/Jwou2SThXKnFdhWTgyzIRM2Ptf8dpCpEgB3t/6As0Y78ENqb0AalecwZlzD9ZY146+VdVxHrITCm+j2tw3gCQ8dqwCFvSE1/vR4y8OrTtSNiNmzi3detktkExZhjmCAnTayhef68oVXnTQ33/UWJvUHaOInL5N0i9iOfy2flNOG3EgSumQexAraEh+g4ziVd0BqMz2bp5gyAr1lGUe6+TkHFtzbiS9lfZtFSdh+EmO5vDmZygWjZZItQbIrHEzK2ONrsqoybPdw6mCTW28EpkN/mnLRf26p849FZ2WoH4AOU3MWhoCmle6orrTckmpk3JZAgMBAAECggEAbRAltWjkk7LGaEbripZoJSgK02HbyUNqQS0sokl74ErPKROZsbxKhw4jNa04WFaF6Va2FfZV5/nvK+jF0dlctPE3oUT536ebY7AzAnXxJYxhwM/1nBvuEHt/2jc2lmQcbcEiQG0ktJ/bZHIYEaGtge2hqnj5e87C4OEWylhe27O/kLC8AD9t1eGRRdENAzbfh75u2RXLMuX3IFjIupaQoqlR9JQ2leBB1kEvF0XFHeZD5fieYpt/m5eu1uLUmLwUU6xB4IF5FsSKjtB4acDkhgRBty/+N+Kl00+8lIB9XTRJgNOuIkBvGWrKKKoBoItxEJmzxl/WEGbBfYfPowLGAQKBgQDbrniZlxBZwmXFvVXxfX7YT9C4Viln8rojcp73KjIBWUJYC3dnIWLfKSO9GayuuKpLjX+aEsDAiadOBFwvJeTWL8I5x/0KT6BMAHKJZNG+Q5kB2chGlzluWy4Zvy3tV7rcnkUG8y8wQHkmkJHcmMsUErgaILT+Z6tXRaKNROKhmQKBgQCeOSHq7XfTLUBMPLAZFVmYtZDigQIrwD+mFUsdY4c8cGTW1q05rb21Dr3JK0GhpvFrMuUmS4eInIufb3zRR0pGZ0I62c26vR4oQ8Ym1qUWicB4X5PVZEwGkyof1OUq3CmZb0ooBsxsZSpAQoRwUxLuMtLJoslXUcQej9y23DNOwQKBgQCIqXc5BTMUw8yQQQLwIkQAyVZs9pz3n5aQasPduxA0qpSNNUTokWpyraMgBpLYduTp73LMJJJotLmx3SCD7Cz52egYDgOqoHJ2FWkrc97PYDoVXqgq0fTWZD6/oaEun38NtB/4/NwERneXuCQleaAMMsD48BPomBGNTzKac5oRMQKBgDNMUrLYSwEsqqwNAQsg4Ouh8ld9fDazHe+695+PpUjzJ75/26nXqqoJHX8bhyvh9GPNHH5N/qNMHfiTLOStOGebEt4Bv7HLnvtu/c+Zx+yGvOBKHKQS4JNBI2cunx5GQzZ73o8bpSt/oTM0XbwNMxYHZE/7d0vYNr2x5t1ih0ABAoGAYkuwUS3JlRX3QWMiGXP/PKLXvQS3FZDxpGE6UHwtTq1zaoCf+zpkLBLOxN0bzqC2Xm4likGzeIfgF9Op8sj9tTOuGrSCr+PaWMkS4PnqEhBT6n/J5ALshiGOiBU8jatV1acysEn60EeEZk9gUtx+yLY1kbgoIcmYYS5hGBn2e+g=";
    private static  final String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArSeeZ7RwV29w5L7uiRrUQA2SjHZYgurZfDUfD4xgN7DwB1yua++M7x4IXkOC8ikRu6BguMAhGnFdqOcmo4wQnb+Fj6b0NtQffVtR9tyMCv2kyT2RTZtyOEhmI1KY0UN8/9z6b70KIdkJHVfJ1JTpjWwjZJ8M4F1je5Ietjhr11XyfCXC01yDuKUJb9QClLIJkXOllwnB6gIcb+ZVVormfjCH3eL8NrZvqRcQRXWmE7CzFucA27/ZkZAki6gIQANmtwQrJU2GeQ514/1LgCMPzUHAI2i0JmDSGa+dtaaqb5GCl/BtNeuX30oWt/x0D4CYARR/2JoVXeLOO4autdX3AQIDAQAB";


    @Bean
    public AlipayClient alipayClient() throws AlipayApiException {
        AlipayConfig config = new AlipayConfig();
        config.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        config.setAppId("9021000161681326");
        config.setPrivateKey(PRIVATE_KEY);
        config.setFormat("json");
        config.setCharset("UTF-8");
        config.setAlipayPublicKey(ALIPAY_PUBLIC_KEY);
        config.setSignType("RSA2");
        return new DefaultAlipayClient(config);
    }
}
