package com.zaw.redisdemo.ordertest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaw.redisdemo.ordertest.entity.OrderDetails;
import com.zaw.redisdemo.ordertest.entity.Products;
import com.zaw.redisdemo.ordertest.mapper.OrderDetailsMapper;
import com.zaw.redisdemo.ordertest.mapper.ProductsMapper;
import com.zaw.redisdemo.ordertest.service.OrdersService;
import com.zaw.redisdemo.ordertest.entity.Orders;
import com.zaw.redisdemo.ordertest.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
* @author wza
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2024-03-13 16:48:22
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService {



    @Autowired
    private OrdersMapper orderMapper;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Autowired
    private ProductsMapper productsMapper;

    @Transactional
    public boolean createOrder(List<OrderDetails> orderDetails, Long userId) {
        Orders order = new Orders();
        order.setUserId(userId);
        order.setOrderDate(new Date());
        int inserted = orderMapper.insert(order);
        if (inserted <= 0) {
            return false;
        }

        for (OrderDetails detail : orderDetails) {
            Products product = productsMapper.selectById(detail.getProductId());
            if (product == null || product.getStock() < detail.getQuantity()) {
                throw new RuntimeException("商品库存不足");
            }
            product.setStock(product.getStock() - detail.getQuantity());
            productsMapper.updateById(product);
            detail.setOrderId(order.getId());
            detail.setPriceAtPurchase(product.getPrice());
            orderDetailsMapper.insert(detail);
        }

        return true;
    }

}




