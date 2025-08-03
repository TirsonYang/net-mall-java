package com.net.mall.server.admin.mapper;


import com.github.pagehelper.Page;
import com.net.mall.common.params.PageQuery;
import com.net.mall.pojo.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    Page<CategoryVO> page(PageQuery query);

    List<CategoryVO> list();
}
