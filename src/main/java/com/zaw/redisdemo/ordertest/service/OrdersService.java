package com.zaw.redisdemo.ordertest.service;

import com.zaw.redisdemo.ordertest.entity.OrderDetails;
import com.zaw.redisdemo.ordertest.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author wza
* @description 针对表【orders】的数据库操作Service
* @createDate 2024-03-13 16:48:22
*/
public interface OrdersService extends IService<Orders> {


    boolean createOrder(List<OrderDetails> orderDetails, Long userId);
}
