package com.chengfeng.study.myspringbootproject.controller.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ShiroController class
 *
 * @author chengfeng
 * @date 2021/8/14 /0014 1:03
 */
@Controller
public class ShiroController {
    /**
     * 到登录页
     *
     * @author chengfeng
     * @date 2021/8/14 /0014 1:05
     **/
    @RequestMapping("/toLogin")
    private String toLogin() {
        return "login";
    }

    @RequestMapping("/user/add")
    @ResponseBody
    private String add() {
        return "<h1>add</h1>";
    }

    @RequestMapping("/user/update")
    @ResponseBody
    private String update() {
        return "<h1>update</h1>";
    }

    /**
     * 未授权
     *
     * @author chengfeng
     * @date 2021/8/14 /0014 1:05
     **/
    @RequestMapping("/noAuth")
    @ResponseBody
    private String unAuthorization() {
        return "<h1 style=\"color: red; text-align: center\">当前用户未授权, 不允许访问!</h1>";
    }

    /**
     * 到首页
     *
     * @author chengfeng
     * @date 2021/8/14 /0014 1:05
     **/
    @RequestMapping("/toHome")
    private String toHome() {
        return "home";
    }

    /**
    * 退出登录
    * @author chengfeng
    * @date 2021/8/14 /0014 1:38
    **/
    @RequestMapping("/logout")
    @ResponseBody
    private String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "退出登录成功.";
    }
}
