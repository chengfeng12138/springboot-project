package com.chengfeng.study.myspringbootproject.common;

import com.chengfeng.study.myspringbootproject.utils.RequestUtil;
import com.chengfeng.study.myspringbootproject.utils.UUIDUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GlobalFilter class
 * 公共的请求拦截器
 *
 * @author chengfeng
 * @date 2020/8/16 /0016 21:48
 */
@Component
public class GlobalFilter implements Filter {
    private static final Logger log = Logger.getLogger(GlobalFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("加载拦截器...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.log(Level.INFO, "开始启动拦截器...");

        //跨域
        HttpServletResponse res = (HttpServletResponse) response;
        // 设置允许Cookie
        res.addHeader("Access-Control-Allow-Credentials", "true");
        // 允许http://www.xxx.com域（自行设置，这里只做示例）发起跨域请求
        res.addHeader("Access-Control-Allow-Origin", "*");
        // 设置允许跨域请求的方法
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        // 允许跨域请求包含content-type
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN");
        if ("OPTIONS".equals(((HttpServletRequest) request).getMethod())) {
            response.getWriter().println("ok");
            return;
        }
        long startTime = System.currentTimeMillis();
        String uuid = UUIDUtils.getUUID();
        log.log(Level.INFO, "request start. the unique id: 【" + uuid + "】, request ip: 【"
                + RequestUtil.getIpAddress((HttpServletRequest) request) + "】, requestAddress is " + ((HttpServletRequest) request).getServletPath());
        //处理请求
        chain.doFilter(request, response);
        log.log(Level.INFO, "request end. the unique id: 【" + uuid + "】, spend time : " + (System.currentTimeMillis() - startTime) + "ms");
    }

    @Override
    public void destroy() {
        System.out.println("拦截器销毁...");
    }
}
