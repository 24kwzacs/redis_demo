package com.zaw.redisdemo.ordertest.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaw.redisdemo.ordertest.entity.Products;
import com.zaw.redisdemo.ordertest.service.ProductsService;
import com.zaw.redisdemo.ordertest.mapper.ProductsMapper;
import com.zaw.redisdemo.productsearch.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @author wza
* @description 针对表【products】的数据库操作Service实现
* @createDate 2024-03-13 16:49:22
*/
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products>
    implements ProductsService{


    @Autowired
    private ProductsMapper productMapper;

    public void addProduct(Products product) {
        productMapper.insert(product);
    }

    public Products getProductById(Long id) {
        return productMapper.selectById(id);
    }


    @Transactional(propagation = Propagation.MANDATORY)
    public void testB() {
        boolean update = this.update(new LambdaUpdateWrapper<Products>()
                .set(Products::getStock, 500).eq(Products::getId, 1));
        int a = 10 /0;

    }
}




