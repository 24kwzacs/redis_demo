package com.zaw.redisdemo.productsearch;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zaw.redisdemo.ordertest.entity.Products;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_LIST_KEY = "product:list";

    private static final List<Product> PRODUCT_LIST;

    //模拟从数据库中查出来的数据
    static {
        PRODUCT_LIST = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Product product = new Product();
            product.setId(UUID.randomUUID().toString().replace("-", ""));
            product.setName("商品名称:" + i);
            product.setDesc("商品描述:" + i);
            product.setPrice(new BigDecimal(i));
            product.setInventory(2);
            PRODUCT_LIST.add(product);
        }
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> productListPage(int current, int size) throws InterruptedException {

        //从缓存中拿到分页数据
        List<Product> productList = getProductListByRedis(current, size);

        if (productList == null || productList.size() == 0) {
            log.info("当前缓存中无分页数据，当前页:" + current + ",页大小:" + size);
            //从数据库中拿到分页数据
            productList = getProductListByDataSource(current, size);
        }
        Map<String, Object> resultMap = new HashMap<>();
        //计算当前总页数
        int totalPage = (PRODUCT_LIST.size() + size - 1) / size;
        resultMap.put("total", PRODUCT_LIST.size());
        resultMap.put("data", productList);
        resultMap.put("pages", totalPage);
        return resultMap;
    }

    private List<Product> getProductListByRedis(int current, int size) {
        log.info("从Redis取出商品信息列表，当前页:" + current + ",页大小:" + size);
        // 计算总页数
        int pages = pages(size);
        // 起始位置
        int start = current <= 0 ? 0 : (current > pages ? (pages - 1) * size : (current - 1) * size);
        // 终止位置
        int end = start+size-1;
        List<Product> list = redisTemplate.opsForList().range(PRODUCT_LIST_KEY, start, end);
        List<Product> productList = list;
        return productList;
    }

    /**
     * 获取商品信息集合
     *
     * @return
     */
    private List<Product> getProductListByDataSource(int current, int size) throws InterruptedException {
        //模拟从DB查询需要300ms
        Thread.sleep(300);
        log.info("从数据库取出商品信息列表，当前页:" + current + ",页大小:" + size);
        // 计算总页数
        int pages = pages(size);
        // 起始位置
        int start = current <= 0 ? 0 : (current > pages ? (pages - 1) * size : (current - 1) * size);
        //数据缓存到redis中
        redisTemplate.opsForList().rightPushAll(PRODUCT_LIST_KEY, PRODUCT_LIST);
        //设置当前key过期时间为1个小时
        redisTemplate.expire(PRODUCT_LIST_KEY,1000*60*60, TimeUnit.MILLISECONDS);
        return PRODUCT_LIST.stream().skip(start).limit(size).collect(Collectors.toList());
    }

    /**
     *  获取总页数
     * @param size
     * @return
     */
    private Integer pages(int size){
        int pages = PRODUCT_LIST.size() % size == 0 ? PRODUCT_LIST.size() / size : PRODUCT_LIST.size() / size + 1;
        return pages;
    }



}

