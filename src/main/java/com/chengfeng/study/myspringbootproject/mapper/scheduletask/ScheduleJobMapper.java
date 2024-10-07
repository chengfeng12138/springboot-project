package com.chengfeng.study.myspringbootproject.mapper.scheduletask;

import com.chengfeng.study.myspringbootproject.pojo.SysJobPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ScheduleJobMapper class
 *
 * @author chengfeng
 * @date 2021/9/19 /0019 20:56
 */
@Repository
public interface ScheduleJobMapper {

    /**
     * 查询所有定时任务
     * @return 任务列表
     * @author chengfeng
     * @date 2021/9/19 /0019 20:59
     */
    List<SysJobPO> findAllScheduleTask();

    /**
     * 查询指定状态的定时任务列表
     * @param jobStatus 任务状态
     * @return 任务列表
     * @author chengfeng
     * @date 2021/9/19 /0019 20:59
     */
    List<SysJobPO> findScheduleTaskListByStatus(Integer jobStatus);

    /**
    * 根据id查询定时任务
    * @param jobId 定时任务id
    * @return 定时任务对象
    * @author chengfeng
    * @date 2021/9/19 /0019 23:58
    */
    SysJobPO findScheduleTaskById(Integer jobId);

    /**
    * 增加定时任务
    * @param sysJobPo 定时任务实体类
    * @return 主键id
    * @author chengfeng
    * @date 2021/9/19 /0019 20:59
    */
    Integer addSysScheduleJob(SysJobPO sysJobPo);

    /**
     * 修改定时任务
     * @param sysJobPo 定时任务实体类
     * @return 更新行数
     * @author chengfeng
     * @date 2021/9/19 /0019 20:59
     */
    Integer editSysScheduleJob(SysJobPO sysJobPo);

    /**
    * 更新任务状态
    * @param jobId 任务id
    * @param jobStatus 任务状态
    * @param updateTime 更新时间
    * @return 影响行数
    * @author chengfeng
    * @date 2021/9/20 /0020 23:56
    */
    Integer enableOrDisableSysScheduleTask(Integer jobId, Integer jobStatus, String updateTime);

    /**
     * 删除定时任务
     * @param jobId 定时任务id
     * @return 更新行数
     * @author chengfeng
     * @date 2021/9/19 /0019 20:59
     */
    Integer deleteSysScheduleJobById(Integer jobId);
}
