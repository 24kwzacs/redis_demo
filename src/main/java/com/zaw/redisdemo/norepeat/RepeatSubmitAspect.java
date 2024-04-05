package com.zaw.redisdemo.norepeat;

import com.zaw.redisdemo.annotation.RepeatSubmit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 重复提交aop
 */

@Component
@Aspect
public class RepeatSubmitAspect {

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.zaw.redisdemo.annotation.RepeatSubmit)")
    public void repeatSubmit() {}

    @Around("repeatSubmit()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取防重复提交注解
        RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
        // 获取token当做key，小编这里是新后端项目获取不到哈，先写死
//         String token = request.getHeader("Authorization");
        String tokenKey = "hhhhhhh,nihao";
//        if (StringUtils.isEmpty(token)) {
//            throw new RuntimeException("token不存在，请登录！");
//        }
        String url = request.getRequestURI();
        /**
         *  通过前缀 + url + token 来生成redis上的 key
         *  可以在加上用户id，小编这里没办法获取，大家可以在项目中加上
         */
        String redisKey = "repeat_submit_key:"
                .concat(url)
                .concat("_")
                .concat(tokenKey);


        if (!redisTemplate.hasKey(redisKey)) {
            redisTemplate.opsForValue().set(redisKey, redisKey, annotation.expireTime(), TimeUnit.SECONDS);
            try {
                //正常执行方法并返回
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                redisTemplate.delete(redisKey);
                throw new Throwable(throwable);
            }
        } else {
            // 抛出异常
            throw new Throwable("请勿重复提交");
        }
    }
}

