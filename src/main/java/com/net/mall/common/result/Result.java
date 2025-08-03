package com.net.mall.common.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Result<T> implements Serializable {

    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.setCode("200");
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.setMsg(msg);
        return result;
    }


}
