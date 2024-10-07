package com.chengfeng.study.myspringbootproject.config.shiroconfig;

import cn.hutool.core.util.StrUtil;
import com.chengfeng.study.myspringbootproject.pojo.User;
import com.chengfeng.study.myspringbootproject.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * UserRealm class
 * shiro 自定义 realm
 *
 * @author chengfeng
 * @date 2021/8/8 /0008 19:07
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权方法...");
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        //获取认证时的用户对象

        User loginUser = (User) principals.getPrimaryPrincipal();
        System.out.println("loginUser.toString() = " + loginUser.toString());

        User currentUser = (User) subject.getPrincipal();
        if (StrUtil.isNotEmpty(currentUser.getPerms())) {
            //授予当前用户的权限
            authInfo.addStringPermission(currentUser.getPerms());
        }
        return authInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行认证方法...");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String username = userToken.getUsername();
        //根据用户名查询用户
        User user = userService.getUserByName(username);
        if (user == null) {
            //用户名验证失败 抛出 UnknownAccountException 异常
            return null;
        }
        //设置session
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser", user);
        //由shiro做密码验证, 验证失败抛出 IncorrectCredentialsException 异常
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");

    }
}
