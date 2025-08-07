package com.net.mall.server.user.controller;


import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.ShoppingCartDTO;
import com.net.mall.pojo.vo.ShoppingCartVO;
import com.net.mall.server.user.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 会员端购物车控制器
 */
@RestController("userShoppingCartController")
@RequestMapping("/user/shoppingCart")
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCartDTO dto){
        log.info("查询购物车：{}",dto);
        if (dto==null){
            return Result.error("参数错误");
        }
        shoppingCartService.add(dto);
        return Result.success();
    }


    @PostMapping("/update")
    public Result update(@RequestBody ShoppingCartDTO dto){
        log.info("更新购物车：{}",dto);
        if (dto==null){
            return Result.error("参数错误");
        }
        shoppingCartService.update(dto);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id){
        log.info("删除购物车项：{}",id);
        shoppingCartService.delete(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<ShoppingCartVO>> list(){
        log.info("会员购物车列表");
        List<ShoppingCartVO> list = shoppingCartService.list();
        return Result.success(list);
    }

    @DeleteMapping("/clear")
    public Result clear(){
        log.info("清空购物车");
        shoppingCartService.clear();
        return Result.success();
    }

}
