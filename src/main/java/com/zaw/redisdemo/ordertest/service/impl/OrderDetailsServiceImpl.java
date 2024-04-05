package com.zaw.redisdemo.ordertest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaw.redisdemo.ordertest.entity.OrderDetails;
import com.zaw.redisdemo.ordertest.mapper.OrderDetailsMapper;
import com.zaw.redisdemo.ordertest.service.OrderDetailsService;
import org.springframework.stereotype.Service;

/**
* @author wza
* @description 针对表【order_details】的数据库操作Service实现
* @createDate 2024-03-13 16:49:19
*/
@Service
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails>
    implements OrderDetailsService {

}




