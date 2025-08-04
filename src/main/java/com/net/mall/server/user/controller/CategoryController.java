package com.net.mall.server.user.controller;


import com.net.mall.common.result.Result;
import com.net.mall.pojo.vo.CategoryVO;
import com.net.mall.server.user.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 会员端分类控制器
 */
@RestController("userCategoryController")
@RequestMapping("/user/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 会员列表查询分类
     * @return
     */
    @GetMapping("/list")
    public Result<List<CategoryVO>> list(){
        log.info("会员列表查询分类");
        List<CategoryVO> list=categoryService.list();
        return Result.success(list);
    }



}
