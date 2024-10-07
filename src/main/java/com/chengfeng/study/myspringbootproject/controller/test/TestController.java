package com.chengfeng.study.myspringbootproject.controller.test;

import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.utils.ResultUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TestController class
 *
 * @author chengfeng
 * @date 2021/7/3 /0003 18:37
 *
 *  通过 @Scope 设置非静态成员变量为多例模式
 */
@Controller
@Scope("prototype")
public class TestController {
    private int num = 0;

    @RequestMapping("/testScope")
    @ResponseBody
    public ResponseResult testScope () {
        return ResultUtil.success(++num);
    }

    @RequestMapping("/testScope2")
    @ResponseBody
    public ResponseResult testScope2 () {
        return ResultUtil.success(++num);
    }
}
