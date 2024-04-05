package com.zaw.redisdemo.ordertest.controller;

import com.zaw.redisdemo.ordertest.entity.Products;
import com.zaw.redisdemo.ordertest.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Products product) {
        productService.addProduct(product);
        return ResponseEntity.ok("新增成功");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProduct(@PathVariable Long id) {
        Products product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}