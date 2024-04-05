package com.zaw.redisdemo;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zaw.redisdemo.ordertest.entity.Orders;
import com.zaw.redisdemo.ordertest.entity.Products;
import com.zaw.redisdemo.ordertest.service.OrdersService;
import com.zaw.redisdemo.ordertest.service.ProductsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class RedisDemoApplicationTests {

    @Test
    void contextLoads() {


        //        一个数组，将重复的数字输出到控制台

        int[] a = {1,2,4,4,5,6};

        Set<Integer> setArr = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            if (setArr.contains(a[i])){
                System.out.println(a[i]);
            }else {
                setArr.add(a[i]);
            }
        }


    }

    @Autowired
    private ProductsService productsService;




    @Transactional
    @Test
    void testA() {
//        在这种情况下，testB() 方法内的事务不会生效，因为事务的传播机制在这里并不适用。
//        Spring 的事务传播行为只在事务方法被外部调用时生效，而在同一个类内部的方法之间的调用不会触发事务的代理。换句话说
//        虽然 testB() 方法上标注了 @Transactional 注解，但由于事务方法是在同一个类中的 testA() 方法内部调用的，Spring 并不会对其进行事务代理。
//        要解决这个问题，您需要将 testB() 方法移动到另一个类中，并确保在调用 testB() 方法时通过 Spring 代理
        productsService.testB();
    }

//    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    void testB() {
//        boolean update = productsService.update(new LambdaUpdateWrapper<Products>()
//                .set(Products::getStock, 400).eq(Products::getId, 1));
//        int a = 10 /0;
//
//    }
}
