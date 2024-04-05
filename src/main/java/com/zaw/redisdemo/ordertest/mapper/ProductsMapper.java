package com.zaw.redisdemo.ordertest.mapper;

import com.zaw.redisdemo.ordertest.entity.Products;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wza
* @description 针对表【products】的数据库操作Mapper
* @createDate 2024-03-13 16:49:22
* @Entity com.zaw.redisdemo.entity.ordertest.Products
*/
@Mapper
public interface ProductsMapper extends BaseMapper<Products> {

}




