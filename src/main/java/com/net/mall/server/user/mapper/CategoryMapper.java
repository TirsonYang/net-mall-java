package com.net.mall.server.user.mapper;

import com.net.mall.pojo.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("userCategoryMapper")
public interface CategoryMapper {


    List<CategoryVO> list();

}
