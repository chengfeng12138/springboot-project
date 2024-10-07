package com.chengfeng.study.myspringbootproject.config.druidconfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * DruidConfig class
 *
 * @author chengfeng
 * @date 2021/7/25 /0025 1:30
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * @description Druid后台监控  http://localhost:8080/druid
     * @author chengfeng
     * @date 2021/7/25 /0025 1:40
     **/
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //配置后台账号密码
        HashMap<String, String> initParameters = new HashMap<>(2);
        //登录名和密码key固定
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");

        //允许访问(为空则所有人可访问)
        initParameters.put("allow", "");
        //禁止访问
        //initParameters.put("chengfeng", "192.168.0.5");

        //设置初始化参数
        bean.setInitParameters(initParameters);
        return bean;
    }
}
