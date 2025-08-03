package com.net.mall.server.boss.controller;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.ProductDTO;
import com.net.mall.server.boss.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 高级管理员商品管理控制器
 */
@RestController
@RequestMapping("/boss/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 新增商品
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody ProductDTO dto){
        log.info("新增商品：{}",dto);
        if (dto==null){
            return Result.error("参数错误");
        }
        productService.add(dto);
        return Result.success();
    }

    /**
     * 更新商品
     * @param dto
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody ProductDTO dto){
        log.info("更新商品：{}",dto);
        if (dto==null){
            return Result.error("参数错误");
        }
        productService.update(dto);
        return Result.success();
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id){
        log.info("删除商品：{}",id);
        if (id==null){
            return Result.error("参数错误");
        }
        productService.delete(id);
        return Result.success();
    }

    /**
     * 根据分类id分页查询
     * @param query
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query,@RequestParam Long categoryId){
        log.info("分页查询：{}",query);
        PageResult page=productService.page(query,categoryId);
        return Result.success(page);
    }

}
