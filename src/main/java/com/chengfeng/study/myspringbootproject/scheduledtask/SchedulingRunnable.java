package com.chengfeng.study.myspringbootproject.scheduledtask;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SchedulingRunnable class
 * 定时任务执行线程, 通过反射执行指定bean里的方法
 * @author chengfeng
 * @date 2021/9/19 /0019 16:46
 */
public class SchedulingRunnable implements Runnable {
    private static final Logger log = Logger.getLogger(SchedulingRunnable.class.getName());

    private String beanName;
    private String methodName;
    private String params;

    public SchedulingRunnable(String beanName, String methodName) {
        this(beanName, methodName, null);
    }

    public SchedulingRunnable(String beanName, String methodName, String params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }

    @Override
    public void run() {
        log.log(Level.INFO, "定时任务开始执行 - bean：" + beanName + "，方法：" + methodName + "，参数：" + params);
        long startTime = System.currentTimeMillis();
        Object target = null;
        try {
            target = SpringContextUtils.getBean(beanName);
            Method method = null;
            if (StringUtils.isNotEmpty(params)) {
                method = target.getClass().getDeclaredMethod(methodName, String.class);
            } else {
                method = target.getClass().getDeclaredMethod(methodName);
            }
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotEmpty(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.log(Level.WARNING, "定时任务执行异常 - bean：" + beanName + "，方法：" + methodName + "，参数：" + params);
        }
        long times = System.currentTimeMillis() - startTime;
        log.log(Level.WARNING, "定时任务执行完成 - bean：" + beanName + "，方法：" + methodName + "，参数：" + params + ", 耗时: " + times + "ms.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingRunnable that = (SchedulingRunnable) o;
        if (params == null) {
            return beanName.equals(that.beanName) &&
                    methodName.equals(that.methodName) &&
                    that.params == null;
        }
        return beanName.equals(that.beanName) && methodName.equals(that.methodName) && params.equals(that.params);
    }

    @Override
    public int hashCode() {
        if (params == null) {
            return Objects.hash(beanName, methodName);
        }
        return Objects.hash(beanName, methodName, params);
    }
}
