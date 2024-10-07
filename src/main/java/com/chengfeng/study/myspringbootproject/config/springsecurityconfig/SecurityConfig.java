package com.chengfeng.study.myspringbootproject.config.springsecurityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SecurityConfig class
 * security安全配置
 *
 * @author chengfeng
 * @date 2021/7/25 /0025 15:24
 */
@EnableWebSecurity  //启用security
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RoleService roleService;

    /**
     * 授权
     * 两种授权方式
     * 1.hasRole() --> 角色授权：授权代码需要加ROLE_前缀，controller上使用时不要加前缀
     * 2.hasAuthority() --> 权限授权：设置和使用时，名称保持一至即可
     *
     * @author chengfeng
     * @date 2021/7/25 /0025 17:19
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问, 功能页只有对应权限人才能访问
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasAuthority("vip1")
                .antMatchers("/level2/**").hasAuthority("vip2")
                .antMatchers("/level3/**").hasAuthority("vip3");
        //没有权限默认跳转登录页
        http.formLogin()
                .loginPage("/toLogin")  //自定义登录页
                .loginProcessingUrl("/login")  //登录请求
                .usernameParameter("username")  //用户名参数
                .passwordParameter("password");  //密码参数
        //关闭csrf功能
        http.csrf().disable();
        //注销功能, /logout请求, 成功后 /login?success
        http.logout().logoutSuccessUrl("/");
        //记住我功能
        http.rememberMe().rememberMeParameter("rememberMe");
    }

    /**
     * 权限认证
     *
     * @author chengfeng
     * @date 2021/7/25 /0025 17:19
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        //使用数据库进行验证时(使用BCrypt编码将用户密码加密处理后存储在数据库中)
        auth.userDetailsService(roleService).passwordEncoder(bCryptPasswordEncoder);

        //设置不同登录用户的权限(数据存于内存中 --> inMemoryAuthentication(), 也可来自于数据库中 --> jdbcAuthentication())

        //auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        //        .withUser("chengfeng").password(bCryptPasswordEncoder.encode("123456")).roles("vip2", "vip3")
        //        .and()
        //        .withUser("root").password(bCryptPasswordEncoder.encode("594230")).roles("vip1", "vip2", "vip3")
        //        .and()
        //        .withUser("guest").password(bCryptPasswordEncoder.encode("123456")).roles("vip1");
    }
}
