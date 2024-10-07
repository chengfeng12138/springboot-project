package com.chengfeng.study.myspringbootproject.service.scheduletask;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.mapper.scheduletask.ScheduleJobMapper;
import com.chengfeng.study.myspringbootproject.pojo.SysJobPO;
import com.chengfeng.study.myspringbootproject.scheduledtask.CronTaskRegistrar;
import com.chengfeng.study.myspringbootproject.scheduledtask.SchedulingRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ScheduleTaskService class
 * 自定义定时任务处理service
 * @author chengfeng
 * @date 2021/9/19 /0019 20:55
 */
@Service
public class ScheduleTaskService {

    @Autowired
    private ScheduleJobMapper scheduleJobMapper;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    /**
     * 查询所有定时任务
     *
     * @author chengfeng
     * @date 2021/9/19 /0019 21:16
     */
    public List<SysJobPO> findAllScheduleTask() {
        List<SysJobPO> allScheduleTask = scheduleJobMapper.findAllScheduleTask();
        if (ArrayUtil.isEmpty(allScheduleTask)) {
            allScheduleTask = new ArrayList<>();
        }
        return allScheduleTask;
    }

    /**
     * 根据状态查询定时任务
     *
     * @author chengfeng
     * @date 2021/9/19 /0019 21:16
     */
    public List<SysJobPO> findScheduleTaskListByStatus(Integer jobStatus) {
        List<SysJobPO> statusScheduleTask = scheduleJobMapper.findScheduleTaskListByStatus(jobStatus);
        if (ArrayUtil.isEmpty(statusScheduleTask)) {
            statusScheduleTask = new ArrayList<>();
        }
        return statusScheduleTask;
    }

    /**
     * 增加定时任务
     * @param sysJobPo 定时任务pojo
     * @return 结果
     * @author chengfeng
     * @date 2021/9/19 /0019 23:40
     */
    public ResponseResult addSysScheduleTask(SysJobPO sysJobPo) {
        ResponseResult responseResult = new ResponseResult(false);
        sysJobPo.setCreateTime(DateUtil.now());
        sysJobPo.setUpdateTime(DateUtil.now());
        Integer integer = scheduleJobMapper.addSysScheduleJob(sysJobPo);
        if (ObjectUtil.isEmpty(integer)) {
            responseResult.setMessage("定时任务新增失败!");
        } else {
            if (sysJobPo.getJobStatus() == 1) {
                SchedulingRunnable task = new SchedulingRunnable(sysJobPo.getBeanName(), sysJobPo.getMethodName(), sysJobPo.getMethodParams());
                cronTaskRegistrar.addCronTask(task, sysJobPo.getCronExpression());
            }
            responseResult.setSuccess(true);
        }
        return responseResult;
    }

    /**
     * 更新定时任务
     * @param existedSysJob 定时任务pojo
     * @return 结果
     * @author chengfeng
     * @date 2021/9/19 /0019 23:40
     */
    public ResponseResult editSysScheduleTask(SysJobPO existedSysJob) {
        ResponseResult responseResult = new ResponseResult(false);
        try {
            existedSysJob.setUpdateTime(DateUtil.now());
            Integer integer = scheduleJobMapper.editSysScheduleJob(existedSysJob);
            if (ObjectUtil.isEmpty(integer)) {
                responseResult.setMessage("定时任务新增失败!");
            } else {
                //先移除再添加
                if (existedSysJob.getJobStatus() == 1) {
                    SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
                    cronTaskRegistrar.removeCronTask(task);
                }
                if (existedSysJob.getJobStatus() == 1) {
                    SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
                    cronTaskRegistrar.addCronTask(task, existedSysJob.getCronExpression());
                }
                responseResult.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setMessage("定时任务更新异常..." + existedSysJob.toString());
        }
        return responseResult;
    }

    /**
    * 停用启用定时任务
    * @author chengfeng
    * @date 2021/9/20 /0020 23:57
    */
    public ResponseResult enableOrDisableSysScheduleTask(Integer jobId, Integer jobStatus) {
        ResponseResult responseResult = new ResponseResult(false);
        try {
            SysJobPO existedSysJob = scheduleJobMapper.findScheduleTaskById(jobId);
            if (ObjectUtil.isEmpty(existedSysJob)) {
                responseResult.setMessage("定时任务查询为空!");
                return responseResult;
            }
            scheduleJobMapper.enableOrDisableSysScheduleTask(jobId, jobStatus, DateUtil.now());
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            if (existedSysJob.getJobStatus() == 1) {
                //启用
                cronTaskRegistrar.addCronTask(task, existedSysJob.getCronExpression());
            } else {
                //停用
                cronTaskRegistrar.removeCronTask(task);
            }
            responseResult.setSuccess(true);
        } catch (Exception e) {
            responseResult.setMessage("定时任务停用启用异常!");
            e.printStackTrace();
        }
        return responseResult;
    }

    /**
    * 删除定时任务
    * @param jobId 任务id
    * @author chengfeng
    * @date 2021/9/19 /0019 23:55
    */
    public ResponseResult deleteSysScheduleTask(Integer jobId) {
        ResponseResult responseResult = new ResponseResult(false);
        try {
            SysJobPO existedSysJob = scheduleJobMapper.findScheduleTaskById(jobId);
            if (ObjectUtil.isEmpty(existedSysJob)) {
                return responseResult;
            }
            Integer integer = scheduleJobMapper.deleteSysScheduleJobById(jobId);
            if (ObjectUtil.isEmpty(integer)) {
                responseResult.setMessage("定时任务新增失败!");
            } else {
                if (existedSysJob.getJobStatus() == 1) {
                    SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(),
                            existedSysJob.getMethodParams());
                    cronTaskRegistrar.removeCronTask(task);
                }
                responseResult.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setMessage("定时任务更新异常..." + jobId);
        }
        return responseResult;
    }
}
