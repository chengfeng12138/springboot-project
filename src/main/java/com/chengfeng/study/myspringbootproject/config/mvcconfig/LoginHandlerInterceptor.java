package com.chengfeng.study.myspringbootproject.config.mvcconfig;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * LoginHandlerInterceptor class
 * 登录拦截器
 * @author chengfeng
 * @date 2021/7/17 /0017 19:49
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static final Logger log = Logger.getLogger(LoginHandlerInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功,应该有用户的session
        Object loginUser = request.getSession().getAttribute("loginUserSession");
        System.out.println("loginUser = " + loginUser);
        return true;
        //if (loginUser == null) {
        //    //未登录
        //    log.log(Level.INFO, "没有权限, 请求被拦截...");
        //    response.setHeader("myMessage", "没有权限,请先登录!");
        //    //重定向到首页
        //    response.sendRedirect(request.getContextPath() + "/");
        //    return false;
        //}
        //return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
