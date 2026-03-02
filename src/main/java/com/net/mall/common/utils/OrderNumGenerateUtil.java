package com.net.mall.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class OrderNumGenerateUtil {
    // 使用原子长整型计数器，支持更大的数值范围
    private static final AtomicLong sequence = new AtomicLong(100000L);
    private static final Random random = new Random();

    /**
     * 生成6位全局唯一的订单码
     * @return 6位数字字符串，在系统整个生命周期内唯一
     */
//    public static String generateOrderId() {
//        // 获取当前序号并递增
//        long currentSeq = sequence.getAndIncrement();
//
//        // 确保不会超过999999
//        if (currentSeq > 999999L) {
//            synchronized (OrderNumGenerateUtil.class) {
//                // 双重检查，防止多个线程同时超过限制
//                if (sequence.get() > 999999L) {
//                    // 如果真的达到了上限，可以从100000重新开始
//                    // 但在实际应用中，6位数字(100000-999999)能提供90万個唯一值，通常足够使用
//                    sequence.set(100000L);
//                }
//                currentSeq = sequence.getAndIncrement();
//            }
//        }
//
//        // 返回6位数字字符串
//        return String.format("%06d", currentSeq);
//    }

    /**
     * 获取当前时间戳作为备用方案
     * @return 基于时间戳的6位数字
     */
    public static String generateOrderId() {
        // 使用当前时间戳的后6位
        long timestamp = System.currentTimeMillis();
        // 取时间戳后6位，确保是6位数
        long sixDigit = timestamp % 1000000L;
        // 如果不足6位，加上基础值
        if (sixDigit < 100000L) {
            sixDigit += 100000L;
        }
        return String.valueOf(sixDigit);
    }
}
