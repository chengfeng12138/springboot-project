package com.chengfeng.study.myspringbootproject.config.shiroconfig;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig class
 * Subject：主体，一般指用户。
 * SecurityManager：安全管理器，管理所有Subject
 * Realms：用于进行权限信息的验证
 *
 * @author chengfeng
 * @date 2021/8/8 /0008 19:04
 */
@Configuration
public class ShiroConfig {

    //创建 ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(
            @Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
         *  anon: 无需认证即可访问
         *  authc: 必须认证了才能访问
         *  user: 必须拥有记住我功能才能用
         *  perms: 拥有对某个资源的权限才能访问
         *  role: 拥有某个角色权限才能访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<>();

        //拦截
        filterMap.put("/level1/1", "authc");
        filterMap.put("/level2/2", "authc");

        //授权 必须有addRole的权限才能访问该请求
        filterMap.put("/user/add", "perms[addRole]");
        filterMap.put("/user/update", "perms[updateRole]");
        //角色权限 manager
        filterMap.put("/user/*", "roles[manager]");

        //开放登录接口
        filterMap.put("/action/login", "anon");
        filterMap.put("/action/addUser", "anon");
        //开放扫码登录接口
        filterMap.put("/getLoginQr", "anon");
        filterMap.put("/bindUserIdAndToken", "anon");
        filterMap.put("/phoneLogin", "anon");
        filterMap.put("/", "anon");
        filterMap.put("/index", "anon");
        filterMap.put("/logout", "anon");

        filterMap.put("/actuator", "anon");

        filterMap.put("swagger/**,/swagger-ui.html,swagger-ui.html,/webjars/**,/swagger-ui.html/*,/swagger-resources,/swagger-resources/**", "anon");

        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterMap.put("/**", "authc");
        filterMap.put("/**", "anon");  //开放所有请求 不再拦截
        factoryBean.setFilterChainDefinitionMap(filterMap);
        //设置登录页
        factoryBean.setLoginUrl("/toLogin");
        //设置未授权页面
        factoryBean.setUnauthorizedUrl("/noAuth");
        System.out.println("Shiro拦截器工厂类注入成功...");
        return factoryBean;
    }

    //创建 SecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(mySessionManager());
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public DefaultWebSessionManager mySessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //防止登录URL中带JSESSIONID
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultWebSessionManager;
    }

    //通过自定义类创建 realm 对象
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

}
