package com.zaw.redisdemo.annotation;

import java.lang.annotation.*;

/**
 * 防重复提交注解
 */
@Target(ElementType.METHOD) // 注解只能用于方法
@Retention(RetentionPolicy.RUNTIME) // 修饰注解的生命周期
@Documented
public @interface RepeatSubmit {

    /**
     * 防重复操作过期时间,默认1s
     */
    long expireTime() default 1;
}
