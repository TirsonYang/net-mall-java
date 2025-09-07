package com.net.mall.server.boss.controller;


import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.dto.CategoryDTO;
import com.net.mall.pojo.vo.CategoryVO;
import com.net.mall.server.boss.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 高级管理商品分类控制器
 */
@RestController("bossCategoryController")
@RequestMapping("/boss/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增商品分类
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CategoryDTO dto){
        log.info("新增商品分类：{}",dto);
        if (dto==null){
            return Result.error("参数错误");
        }
        categoryService.add(dto);
        return Result.success();
    }

    /**
     * 修改商品分类
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody CategoryDTO dto){
        log.info("修改商品分类：{}",dto);
        if (dto==null){
            return Result.error("参数错误");
        }
        categoryService.update(dto);
        return Result.success();
    }

    /**
     * 删除商品分类
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id){
        log.info("删除商品分类：{}",id);
        if (id==null){
            return Result.error("参数错误");
        }
        categoryService.delete(id);
        return Result.success();
    }


    /**
     * 分页查询商品分类
     */
    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query){
        log.info("分页查询：{}",query);
        PageResult page=categoryService.page(query);
        return Result.success(page);
    }

    /**
     * 列表查询商品分类
     * @return
     */
    @GetMapping("/list")
    public Result<List<CategoryVO>> list(){
        log.info("boss列表查询分类");
        List<CategoryVO> list=categoryService.list();
        return Result.success(list);
    }

}
