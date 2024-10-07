package com.chengfeng.study.myspringbootproject.common;

import java.lang.annotation.*;

/**
 * LogAnnotation class
 * 自定义注解
 * @author chengfeng
 * @date 2022/8/14 /0014 17:38
 */
//Type表示该注解可以放在类上, Method表示可以放在方法上
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    //注解参数
    //模块名
    String module() default "";
    //操作名称
    String operator() default "";

}
