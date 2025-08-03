package com.net.mall.server.admin.controller;


import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.pojo.vo.CategoryVO;
import com.net.mall.server.admin.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 前台端分类管理控制器
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query){
        log.info("前台分页查询分类：{}",query);
        PageResult page=categoryService.page(query);
        return Result.success(page);
    }


    @GetMapping("/list")
    public Result<List<CategoryVO>> list(){
        log.info("前台列表查询分类");
        List<CategoryVO> list=categoryService.list();
        return Result.success(list);
    }

}
