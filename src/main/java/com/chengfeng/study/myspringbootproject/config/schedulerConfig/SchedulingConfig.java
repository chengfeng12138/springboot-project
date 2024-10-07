package com.chengfeng.study.myspringbootproject.config.schedulerConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * SchedulingConfig class
 * 自定义定时任务配置类
 * @author chengfeng
 * @date 2021/9/12 /0012 21:04
 */
@Configuration
public class SchedulingConfig {

    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        // 定时任务执行线程池核心线程数
        taskScheduler.setPoolSize(4);
        // 任务一旦被取消将立即被移除
        taskScheduler.setRemoveOnCancelPolicy(true);
        // 定时任务线程名
        taskScheduler.setThreadNamePrefix("TaskSchedulerThreadPool-");
        return taskScheduler;
    }

}
