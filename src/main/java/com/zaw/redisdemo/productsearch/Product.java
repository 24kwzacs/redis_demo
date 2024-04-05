package com.zaw.redisdemo.productsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    private String id;
    private String name;
    private String desc;
    private BigDecimal price;
    private int inventory;



}
