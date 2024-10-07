package com.chengfeng.study.myspringbootproject.scheduledtask;

import java.util.concurrent.ScheduledFuture;

/**
 * ScheduledTask class
 * ScheduledFuture的包装类, ScheduledExecutorService定时任务线程池的执行结果
 * @author chengfeng
 * @date 2021/9/19 /0019 16:44
 */
public class ScheduledTask {

    volatile ScheduledFuture<?> future;

    /**
    * 取消定时任务
    * @author chengfeng
    * @date 2021/9/19 /0019 16:45
    */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }

}
