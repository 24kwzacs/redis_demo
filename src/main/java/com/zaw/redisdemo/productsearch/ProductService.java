package com.zaw.redisdemo.productsearch;

import java.util.Map;

public interface ProductService {

    Map<String,Object> productListPage(int current, int size) throws InterruptedException;

}
