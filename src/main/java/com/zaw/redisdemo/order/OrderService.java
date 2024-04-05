package com.zaw.redisdemo.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class OrderService
{
    public static final String ORDER_KEY = "order:";

    @Resource
    private RedisTemplate redisTemplate;

    public void addOrder()
    {
        int keyId = ThreadLocalRandom.current().nextInt(1000)+1;
        String orderNo = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(ORDER_KEY+keyId,"京东订单"+ orderNo);
        log.info("=====>编号"+keyId+"的订单流水生成:{}",orderNo);
    }

    public String getOrderById(Integer id)
    {
        return (String)redisTemplate.opsForValue().get(ORDER_KEY + id);
    }
}