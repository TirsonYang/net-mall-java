package com.net.mall.server.user.controller;


import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.InformationDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user/information")
public class InformationController {

    @PostMapping("/submitBirthday")
    public Result submitBirthday(@RequestBody InformationDTO dto){
        if (dto==null){
            return Result.error("参数错误");
        }



        return Result.success();
    }
}
