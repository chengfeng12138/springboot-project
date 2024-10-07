package com.chengfeng.study.myspringbootproject.controller.scheduletask;

import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.pojo.SysJobPO;
import com.chengfeng.study.myspringbootproject.service.scheduletask.ScheduleTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ScheduleTaskController class
 * 自定义定时任务controller
 * @author chengfeng
 * @date 2021/9/19 /0019 23:38
 */
@RestController
@RequestMapping("/action")
public class ScheduleTaskController {

    @Autowired
    private ScheduleTaskService scheduleTaskService;

    /**
    * 查询所有定时任务
    * @author chengfeng
    * @date 2021/9/20 /0020 22:09
    */
    @RequestMapping("/findAllScheduleTask")
    @ResponseBody
    public ResponseResult findAllScheduleTask() {
        List<SysJobPO> allScheduleTask = scheduleTaskService.findAllScheduleTask();
        ResponseResult responseResult = new ResponseResult(true);
        responseResult.setData(allScheduleTask);
        return responseResult;
    }

    /**
    * 保存定时任务
    * @author chengfeng
    * @date 2021/9/20 /0020 22:09
    */
    @RequestMapping("/addSysScheduleTask")
    @ResponseBody
    public ResponseResult addSysScheduleTask(@RequestBody SysJobPO sysJobPo) {
        return scheduleTaskService.addSysScheduleTask(sysJobPo);
    }

    /**
     * 更新定时任务
     * @author chengfeng
     * @date 2021/9/20 /0020 22:13
     */
    @RequestMapping("/editSysScheduleTask")
    @ResponseBody
    public ResponseResult editSysScheduleTask(@RequestBody SysJobPO sysJobPo) {
        return scheduleTaskService.editSysScheduleTask(sysJobPo);
    }

    /**
     * 停用或启用定时任务
     * @author chengfeng
     * @date 2021/9/20 /0020 22:13
     */
    @RequestMapping("/enableOrDisableSysScheduleTask")
    @ResponseBody
    public ResponseResult enableOrDisableSysScheduleTask(@RequestBody Integer jobId, @RequestBody Integer jobStatus) {
        return scheduleTaskService.enableOrDisableSysScheduleTask(jobId, jobStatus);
    }

    /**
    * 删除定时任务
    * @author chengfeng
    * @date 2021/9/20 /0020 22:13
    */
    @RequestMapping("/deleteSysScheduleTask")
    @ResponseBody
    public ResponseResult deleteSysScheduleTask(@RequestParam("jobId") Integer jobId) {
        return scheduleTaskService.deleteSysScheduleTask(jobId);
    }
}
