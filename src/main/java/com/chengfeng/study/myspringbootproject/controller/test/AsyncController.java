package com.chengfeng.study.myspringbootproject.controller.test;

import cn.hutool.core.thread.ExecutorBuilder;
import com.chengfeng.study.myspringbootproject.service.test.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * AsyncController class
 *
 * @author chengfeng
 * @date 2021/8/14 /0014 22:16
 */
@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    ExecutorService executor = ExecutorBuilder.create()
            .setCorePoolSize(5)
            .setMaxPoolSize(10)
            .setWorkQueue(new LinkedBlockingQueue<>(100))
            .build();

    @RequestMapping("/asyncTest")
    @ResponseBody
    public String asyncTest() {
        asyncService.hello();
        return "OK";
    }
}
