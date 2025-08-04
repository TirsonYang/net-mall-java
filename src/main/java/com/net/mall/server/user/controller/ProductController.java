package com.net.mall.server.user.controller;


import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.server.user.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 会员端商品控制器
 */
@RestController("userProductController")
@RequestMapping("/user/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 会员分页查询商品
     * @param query
     * @param categoryId
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody PageQuery query,@RequestParam Long categoryId){
        log.info("用户分页查询分类为:{}的{}",categoryId,query);
        PageResult page=productService.page(query,categoryId);
        return Result.success(page);
    }

}
