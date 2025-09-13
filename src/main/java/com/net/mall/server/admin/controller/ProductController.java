package com.net.mall.server.admin.controller;


import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.UpdateStockDTO;
import com.net.mall.pojo.vo.CateProVO;
import com.net.mall.pojo.vo.ProductVO;
import com.net.mall.server.admin.service.CategoryService;
import com.net.mall.server.admin.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前台端商品管理控制器
 */
@RestController("adminProductController")
@RequestMapping("/admin/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


    /**
     * 前台端分页查询商品
     * @param query
     * @param categoryId
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query, @RequestParam Long categoryId){
        log.info("前台分页查询分类为:{}的{}",categoryId,query);
        PageResult page=productService.page(query,categoryId);
        return Result.success(page);
    }

    /**
     * 前台端列表查询商品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    public Result<List<ProductVO>> list(@RequestParam(required = false) Long categoryId){
        log.info("前台查询分类为:{}的商品",categoryId);
        List<ProductVO> list=productService.list(categoryId);
        log.info("前台查询分类为:{}的商品结果为:{}",categoryId,list);
        return Result.success(list);
    }

    /**
     * 前台端更新商品库存
     * @param dto
     * @return
     */
    @PostMapping("/updateStock")
    public Result updateStock(@RequestBody UpdateStockDTO dto){
        log.info("前台更新商品id为:{}的库存为:{}",dto.getId(),dto.getStock());
        productService.updateStock(dto.getId(),dto.getStock());
        return Result.success();
    }

    @GetMapping("/getCate")
    public Result<List<CateProVO>> getCate(){
        log.info("前台查询所有分类以展示商品");
        List<CateProVO> vo=categoryService.getCate();
        return Result.success(vo);
    }

}
