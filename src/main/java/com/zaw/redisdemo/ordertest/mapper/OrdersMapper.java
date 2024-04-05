package com.zaw.redisdemo.ordertest.mapper;

import com.zaw.redisdemo.ordertest.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wza
* @description 针对表【orders】的数据库操作Mapper
* @createDate 2024-03-13 16:48:22
* @Entity com.zaw.redisdemo.entity.ordertest.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}




