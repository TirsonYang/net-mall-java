package com.net.mall.common.utils;

import java.util.UUID;

public class UUIDUtil {
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        return uuid.toString();
    }
}
