package com.net.mall.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyUtil {
    /**
     * 将元为单位的金额转换为分（微信支付要求）
     * @param yuan BigDecimal类型的金额（元）
     * @return 分（整数）
     */
    public static int yuanToFen(BigDecimal yuan) {
        if (yuan == null) {
            return 0;
        }
        // 乘以100，并四舍五入到整数（分）
        return yuan.multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.HALF_UP)
                .intValue(); // 注意：若金额可能超过int范围，请改用long
    }

    /**
     * 将分为单位的金额转换为元（用于解析微信返回结果）
     * @param fen 分（整数）
     * @return BigDecimal类型的金额（元）
     */
    public static BigDecimal fenToYuan(int fen) {
        return new BigDecimal(fen).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }
}
