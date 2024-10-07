package com.chengfeng.study.myspringbootproject.controller.test;

import com.chengfeng.study.myspringbootproject.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SwaggerController class
 *
 * @author chengfeng
 * @date 2021/8/14 /0014 21:38
 */
@Api(tags = "swagger测试-controller") // swagger 对该Controller的说明
@RestController
public class SwaggerController {

    @ApiOperation("get方法测试")  //swagger 对请求方法的说明
    @GetMapping("/getTest")
    public String getTest(@ApiParam("用户名") String username) {
        return "hello" + username;
    }

    @ApiOperation("post方法测试")
    @PostMapping("/postTest2")
    public String postTest(@ApiParam("用户信息") User user) {
        return "hello" + user.getName();
    }
}
