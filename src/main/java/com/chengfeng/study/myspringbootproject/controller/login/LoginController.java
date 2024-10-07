package com.chengfeng.study.myspringbootproject.controller.login;

import com.alibaba.fastjson.JSON;
import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.pojo.User;
import com.chengfeng.study.myspringbootproject.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * LoginController class
 *
 * @author chengfeng
 * @date 2020/8/23 /0023 1:43
 */
@RestController
@RequestMapping("/action")
@Log4j2
public class LoginController {

    @Autowired
    private UserService userService;


    /**
     * @method 用户登录
     * @author chengfeng
     * @date 2020/8/23 /0023 18:19
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    private String login(@RequestBody User user, HttpSession session) {
        String name = user.getName();
        String password = user.getPassword();
        log.info("login name :[" + name + "], password : [" + password + "].");
        ResponseResult responseResult = new ResponseResult(true);
        //shiro权限认证
        shiroCheckUser(name, password, responseResult);
//        User getUser = userService.getUser(name, password);

        User user2 = new User();
        user2.setId(1);
        user2.setName("8001");
        user2.setPassword("123456");

        responseResult.setData(user2);
        return JSON.toJSONString(responseResult);
    }

    /**
     * @description 登录时通过shiro认证用户信息
     * @author chengfeng
     * @date 2021/8/8 /0008 21:24
     **/
    private void shiroCheckUser(String name, String password, ResponseResult responseResult) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        try {
            UsernamePasswordToken userToken = new UsernamePasswordToken(name, password);
            subject.login(userToken); //登录
        } catch (UnknownAccountException e) {
            log.warn("用户名验证失败...");
            responseResult.setData(null);
            responseResult.setSuccess(false);
            responseResult.setMessage("用户名验证失败!!!");
        } catch (IncorrectCredentialsException e) {
            log.warn("密码验证失败...");
            responseResult.setData(null);
            responseResult.setSuccess(false);
            responseResult.setMessage("密码验证失败!!!");
        } catch (LockedAccountException e) {
            log.warn("登录失败，该用户已被冻结...");
            responseResult.setData(null);
            responseResult.setSuccess(false);
            responseResult.setMessage("登录失败，该用户已被冻结!!!");
        } catch (AuthenticationException e) {
            log.warn("该用户不存在...");
            responseResult.setData(null);
            responseResult.setSuccess(false);
            responseResult.setMessage("该用户不存在!!!");
        } catch (Exception e) {
            log.warn("登录异常, {}", name, e);
            responseResult.setData(null);
            responseResult.setSuccess(false);
            responseResult.setMessage("登录异常, 请重试!!!");
        }
    }

    /**
     * @method 增加用户
     * @author chengfeng
     * @date 2020/8/29 /0029 14:06
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUser(@RequestBody User user) {
        return JSON.toJSONString(userService.insertUser(user));
    }

}
