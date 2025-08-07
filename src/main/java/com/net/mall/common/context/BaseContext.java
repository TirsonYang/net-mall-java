package com.net.mall.common.context;

public class BaseContext {

    public static ThreadLocal<Long> threadLocal=new ThreadLocal<>();

    public static void setCurrentUserId(Long userId){
        threadLocal.set(userId);
    }

    public static Long getCurrentUserId(){
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
