package com.zaw.redisdemo.ordertest.controller;

import com.zaw.redisdemo.ordertest.entity.OrderDetails;
import com.zaw.redisdemo.ordertest.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody List<OrderDetails> orderDetails) {
        boolean success = ordersService.createOrder(orderDetails, 1L);
        if (success) {
            return ResponseEntity.ok("订单创建成功");
        } else {
            return ResponseEntity.badRequest().body("订单创建失败");
        }
    }
}
