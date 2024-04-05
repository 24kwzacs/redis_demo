package com.zaw.redisdemo.productsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/page")
    public Map<String,Object> page(@RequestParam("current") int current, @RequestParam("size") int size){
        Map<String, Object> stringObjectMap;
        try {
            stringObjectMap = productService.productListPage(current, size);
        } catch (InterruptedException e) {
            stringObjectMap = new HashMap<>();
        }
        return stringObjectMap;
    }

}
