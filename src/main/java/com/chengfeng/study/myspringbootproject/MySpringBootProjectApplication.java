package com.chengfeng.study.myspringbootproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Springboot项目启动类
 *
 * @author chengfeng
 * @SpringBootApplication 是一个组合注解 包含@Configuration
 */
@SpringBootApplication(exclude = {
        //暂时不启用 spring security
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
})
//@SpringBootApplication
@MapperScan("com.chengfeng.study.myspringbootproject.mapper")
@EnableAsync //开启异步任务注解
@EnableScheduling //开启定时任务
public class MySpringBootProjectApplication {
    public static void main(String[] args) {
        //这行代码的作用： 基于注解的Ioc容器的初始化操作
        ConfigurableApplicationContext context = SpringApplication.run(MySpringBootProjectApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        System.out.println("访问链接：http://localhost:" + environment.getProperty("server.port") + environment.getProperty("server.servlet.context-path"));

    }
}
