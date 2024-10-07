package com.chengfeng.study.myspringbootproject;

import cn.hutool.core.date.DateUtil;
import com.chengfeng.study.myspringbootproject.common.RedisUtils;
import com.chengfeng.study.myspringbootproject.mapper.scheduletask.ScheduleJobMapper;
import com.chengfeng.study.myspringbootproject.pojo.SysJobPO;
import com.chengfeng.study.myspringbootproject.service.mail.MailSendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

/**
 * Test2 class
 *
 * @author chengfeng
 * @date 2021/7/25 /0025 0:30
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Test2 {

    @Autowired
    DataSource dataSource;

    @Autowired
    MailSendService mailSendService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    ScheduleJobMapper scheduleJobMapper;

    @Test
    public void test() {

        String subject = "这是带有样式的一封邮件~";
        String text = "<h1 style=\"text-align: center;\">哈哈哈哈哈哈!</h1>";
        String from = "1210714514@qq.com";
        String to = "1210714514@qq.com";
        String fileName = "8001.png";
        String fileUrl = "C:\\Users\\15623\\Desktop\\8001.png";

        mailSendService.sendMail(subject, text, from, to, true, fileName, fileUrl);
    }

    @Test
    public void test1() {
        SysJobPO sysJobPo = new SysJobPO(null , "demoTask", "taskWithParams", "666",
                "0/2 * * * * ?", 1, "带参数的定时任务", DateUtil.now(), DateUtil.now());
        Integer integer = scheduleJobMapper.addSysScheduleJob(sysJobPo);
        System.out.println("integer = " + integer);
    }
}
