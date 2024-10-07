package com.chengfeng.study.myspringbootproject.common;

import com.chengfeng.study.myspringbootproject.utils.ResultUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GlobalExceptionHandler class
 * 公共异常处理
 * @author chengfeng
 * @date 2020/8/15 /0015 16:18
 *  ControllerAdvice注解 当前项目中所有类的统一异常处理类
 *  ExceptionHandler注解 用来定义函数针对异常类型以及异常如何出咯
 * @author chengfeng
 */
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult exceptionHandler(Exception e){
        log.error("全局异常处理: {}", e.getMessage(), e);
        return ResultUtil.error(500, "服务器错误, 请联系管理员!");
    }
}
