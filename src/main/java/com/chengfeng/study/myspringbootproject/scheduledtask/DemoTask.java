package com.chengfeng.study.myspringbootproject.scheduledtask;

import org.springframework.stereotype.Component;

/**
 * DemoTask class
 * 定时任务示例类
 * @author chengfeng
 * @date 2021/9/19 /0019 17:23
 */
@Component("demoTask")
public class DemoTask {
    public void taskWithParams(String params) {
        System.out.println("执行有参示例任务：" + params);
    }

    public void taskNoParams() {
        System.out.println("执行无参示例任务");
    }
}
