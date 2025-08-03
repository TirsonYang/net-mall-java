package com.net.mall.server.admin.controller;


import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.vo.ProductVO;
import com.net.mall.server.admin.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前台端商品管理控制器
 */
@RestController
@RequestMapping("/admin/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query, @RequestParam Long categoryId){
        log.info("前台分页查询分类为:{}的{}",categoryId,query);
        PageResult page=productService.page(query,categoryId);
        return Result.success(page);
    }

    @GetMapping("/list")
    public Result<List<ProductVO>> list(@RequestParam Long categoryId){
        log.info("前台查询分类为:{}的商品",categoryId);
        List<ProductVO> list=productService.list(categoryId);
        return Result.success(list);
    }

    @PostMapping("/updateStock")
    public Result updateStock(@RequestParam Long id,@RequestParam Integer stock){
        log.info("前台更新商品id为:{}的库存为:{}",id,stock);
        productService.updateStock(id,stock);
        return Result.success();
    }

}
