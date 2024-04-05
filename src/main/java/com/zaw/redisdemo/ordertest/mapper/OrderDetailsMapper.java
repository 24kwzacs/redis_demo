package com.zaw.redisdemo.ordertest.mapper;

import com.zaw.redisdemo.ordertest.entity.OrderDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wza
* @description 针对表【order_details】的数据库操作Mapper
* @createDate 2024-03-13 16:49:19
* @Entity com.zaw.redisdemo.entity.ordertest.OrderDetails
*/
@Mapper
public interface OrderDetailsMapper extends BaseMapper<OrderDetails> {

}




