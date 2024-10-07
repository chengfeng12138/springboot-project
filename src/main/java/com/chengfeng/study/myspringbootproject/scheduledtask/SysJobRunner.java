package com.chengfeng.study.myspringbootproject.scheduledtask;

import com.chengfeng.study.myspringbootproject.pojo.SysJobPO;
import com.chengfeng.study.myspringbootproject.service.scheduletask.ScheduleTaskService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SysJobRunner class
 * 服务启动后执行定时任务
 * @author chengfeng
 * @date 2021/9/19 /0019 17:30
 */
@Component
public class SysJobRunner implements CommandLineRunner {
    private static final Logger log = Logger.getLogger(SysJobRunner.class.getName());

    @Autowired
    private ScheduleTaskService scheduleTaskService;
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public void run(String... args) {
        // 初始加载数据库里状态为正常的定时任务
        //查询数据库里启用的定时任务
        new Thread(() -> {
            List<SysJobPO> jobList = scheduleTaskService.findScheduleTaskListByStatus(1);
            if (CollectionUtils.isNotEmpty(jobList)) {
                for (SysJobPO job : jobList) {
                    SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
                    cronTaskRegistrar.addCronTask(task, job.getCronExpression());
                }
            }
            log.log(Level.INFO, "定时任务加载完毕...任务数: " + jobList.size());
        }, "enable-schedule-task-thread").start();
    }
}
