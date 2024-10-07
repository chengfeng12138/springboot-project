package com.chengfeng.study.myspringbootproject.utils;

import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.common.ResultEnum;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ResultUtil class
 *
 * @author chengfeng
 * @date 2020/7/26 /0026 22:07
 */
public class ResultUtil {
    private static final Logger log = Logger.getLogger(ResultUtil.class.getName());
    /**成功且带数据**/
    public static ResponseResult success(Object object){
        ResultUtil.printLog();
        ResponseResult result = new ResponseResult(true);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(object);
        return result;
    }
    /**成功且带数据**/
    public static ResponseResult success(Object object, String type){
        ResultUtil.printLog();
        ResponseResult result = new ResponseResult(true);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(object);
        result.setType(type);
        return result;
    }
    /**成功但不带数据**/
    public static ResponseResult success(){
        ResultUtil.printLog();
        return success(null);
    }
    /**失败**/
    public static ResponseResult error(Integer code,String msg){
        ResultUtil.printLog();
        ResponseResult result = new ResponseResult(false);
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static void printLog(){
        log.log(Level.INFO, "request end.the end time is [ " + DateUtils.getNowTime_EN() + "], the uuid is [" + UUIDUtils.getUUID() + "]");
    }

}
