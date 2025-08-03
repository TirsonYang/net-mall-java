package com.net.mall.server.boss.controller;


import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.CategoryDTO;
import com.net.mall.server.boss.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boss/category")
public class CategoryController {


    private CategoryService categoryService;

    /**
     * 新增商品分类
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CategoryDTO dto){

        if (dto==null){
            return Result.error("参数错误");
        }
        categoryService.add(dto);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody CategoryDTO dto){
        if (dto==null){
            return Result.error("参数错误");
        }
        categoryService.update(dto);
        return Result.success();
    }

}
