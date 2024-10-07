package com.chengfeng.study.myspringbootproject;

import com.chengfeng.study.myspringbootproject.pojo.Dog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MyTest class
 * 反射创建对象
 * @author chengfeng
 * @date 2020/9/4 /0004 23:39
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyTest {

    @Autowired
    Dog dog;

    @Test
    public void test() {
        System.out.println("dog = " + dog);

    }
}