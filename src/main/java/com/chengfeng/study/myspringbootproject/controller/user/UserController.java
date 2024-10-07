package com.chengfeng.study.myspringbootproject.controller.user;

import com.alibaba.fastjson.JSON;
import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.pojo.User;
import com.chengfeng.study.myspringbootproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController class
 *
 * @author chengfeng
 * @date 2020/8/1 /0001 23:56
 */
@RestController
@RequestMapping("/action")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    public String getUser(@PathVariable int id){
        User sel = userService.getUserById(id);
        System.out.println(JSON.toJSONString(sel));
        return JSON.toJSONString(sel);
    }

    /**
    * 查询所有用户信息
    * @author chengfeng
    * @date 2021/8/21 /0021 22:15
    */
    @PostMapping("/findAllUser")
    public String findAllUser() {
        List<User> allUser = null;
        ResponseResult responseResult = new ResponseResult();
        try {
            allUser = userService.findAllUser();
            responseResult.setSuccess(true);
            responseResult.setData(allUser);
        } catch (Exception e) {
            e.printStackTrace();
            responseResult.setSuccess(false);
            responseResult.setMessage("用户信息查询失败!");
        }
        return JSON.toJSONString(responseResult);
    }

    /**
    * 保存用户信息
    * @author chengfeng
    * @date 2021/9/11 /0011 0:51
    */
    @RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
    public String saveUserInfo(@RequestBody User user) {
        System.out.println("添加用户:  " + user.toString());
        ResponseResult responseResult = userService.insertUser(user);
        return JSON.toJSONString(responseResult);
    }

    /**
    * 删除用户信息
    * @author chengfeng
    * @date 2021/9/11 /0011 0:52
    */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@RequestBody User user) {
        ResponseResult responseResult = new ResponseResult(true);
        int userId = userService.deleteUser(user.getId());
        responseResult.setData(userId);
        return JSON.toJSONString(responseResult);
    }
}
