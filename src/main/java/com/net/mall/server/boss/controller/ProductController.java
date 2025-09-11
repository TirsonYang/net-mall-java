package com.net.mall.server.boss.controller;

import com.net.mall.common.params.PageQuery;
import com.net.mall.common.result.PageResult;
import com.net.mall.common.result.Result;
import com.net.mall.common.utils.UUIDUtil;
import com.net.mall.pojo.dto.ProductDTO;
import com.net.mall.pojo.vo.CateProVO;
import com.net.mall.pojo.vo.ProductVO;
import com.net.mall.server.boss.service.CategoryService;
import com.net.mall.server.boss.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * 高级管理员商品管理控制器
 */
@RestController("bossProductController")
@RequestMapping("/boss/product")
@Slf4j
public class ProductController {

    @Value("${pictureFile.path}")
    private String picturePath;

    @Value("${pictureFile.path-mapping}")
    private String picturePath_mapping;

    @Value("${pictureFile.url}")
    private String pictureUrl;

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

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

    @GetMapping("/getCate")
    public Result<List<CateProVO>> getCate(){
        log.info("商品页查询分类");
        List<CateProVO> list = categoryService.getCate();
        return Result.success(list);
    }

    @GetMapping("/list")
    public Result<List<ProductVO>> list(@RequestParam(required = false) Long categoryId){
        log.info("商品查询列表：{}",categoryId);
        List<ProductVO> list = productService.list(categoryId);
        return Result.success(list);
    }

    @PostMapping("/upload/img")
    public Result<String> upload(MultipartFile file){
        log.info("上传图片：{}",file);
        String fileName= file.getOriginalFilename();
        String suffixName= null;
        if (fileName != null) {
            suffixName = fileName.substring(fileName.lastIndexOf("."));
        }
        fileName = UUIDUtil.getUUID() + suffixName;
        File dest = new File(picturePath+fileName);
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e){
            e.printStackTrace();
        }
        String finalFileName = pictureUrl+fileName;
        log.info("上传成功，保存路径：{}，访问URL：{}", dest.getAbsolutePath(), finalFileName);
        return Result.success(finalFileName);
    }

}
