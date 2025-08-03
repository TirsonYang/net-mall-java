package com.net.mall.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderNumGenerateUtil {
    // 使用原子计数器避免并发冲突
    private static final AtomicInteger sequence = new AtomicInteger(0);
    private static final Random random = new Random();

    public static String generateOrderId() {
        // 1. 获取当前日期 (格式: yyyyMMdd)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String datePart = dateFormat.format(new Date());

        // 2. 获取纳秒级时间戳 (确保同一毫秒内的唯一性)
        long timestampPart = System.nanoTime();

        // 3. 生成随机数或序列号 (4位随机数)
        int randomPart = random.nextInt(9000) + 1000; // 1000-9999

        // 4. 组合成订单号
        return datePart + timestampPart + randomPart;
    }


}
