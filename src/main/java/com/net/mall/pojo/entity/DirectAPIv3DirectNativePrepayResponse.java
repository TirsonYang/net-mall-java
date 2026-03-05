package com.net.mall.pojo.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class DirectAPIv3DirectNativePrepayResponse {

    @SerializedName("code_url")
    public String codeUrl;
}
