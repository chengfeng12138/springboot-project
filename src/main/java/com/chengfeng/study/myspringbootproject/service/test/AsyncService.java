package com.chengfeng.study.myspringbootproject.service.test;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * AsyncService class
 *
 * @author chengfeng
 * @date 2021/8/14 /0014 22:20
 */
@Service
public class AsyncService {

    @Async //表示该方法为异步
    public String hello() {
        try {
            Thread.sleep(3000);
            System.out.println("异步执行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test...";
    }
}
