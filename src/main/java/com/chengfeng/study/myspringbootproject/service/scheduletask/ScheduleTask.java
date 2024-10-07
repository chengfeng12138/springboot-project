package com.chengfeng.study.myspringbootproject.service.scheduletask;

import cn.hutool.core.date.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * ScheduleTask class
 * 定时任务类
 *
 * @author chengfeng
 * @date 2021/6/20 /0020 17:47
 */
// @Service
public class ScheduleTask {

    @Scheduled(cron = "0/5 * * * * ?")  //添加定时任务
    //@Scheduled(fixedRate = 5000)  //间隔5秒执行一次
    private void configureTasks() {
        System.out.println("这里5秒执行一次..." + DateUtil.now());
    }
}
