package com.zaw.redisdemo.ordertest.service;

import com.zaw.redisdemo.ordertest.entity.Products;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zaw.redisdemo.productsearch.Product;

/**
* @author wza
* @description 针对表【products】的数据库操作Service
* @createDate 2024-03-13 16:49:22
*/
public interface ProductsService extends IService<Products> {


    void addProduct(Products product);

    Products getProductById(Long id);
    public void testB();

}
