package com.chengfeng.study.myspringbootproject.config.springsecurityconfig;

import com.chengfeng.study.myspringbootproject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * RoleService class
 *
 * @author chengfeng
 * @date 2021/7/25 /0025 17:23
 */
@Component
public class RoleService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            //根据用户名获取权限
            System.out.println("从数据库中查询验证用户....");
            Collection<GrantedAuthority> authList = getAuthorities();

            //1.放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀
            //2.放入的是权限时，不能加ROLE_前缀，hasAuthority与放入的权限名称对应即可
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(getRole(username));

            //参数信息: 用户名, 密码, 帐户是否可用, 帐户是否过期(true 未过期，false 已过期), 密码是否过期(true 未过期，false 过期)
            //帐户是否被锁定(true 未锁定，false 已锁定), 可访问的资源权限
            userDetails = new User(username, new BCryptPasswordEncoder().encode("123456"), true,
                    true, true, true, grantedAuthorities);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDetails;
    }

    private Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("vip1"));
        authList.add(new SimpleGrantedAuthority("vip2"));
        return authList;
    }

    private String getRole(String username) {
        if ("root".equals(username)) return "vip1,vip2,vip3";
        if ("guest".equals(username)) return "vip1";
        if ("chengfeng".equals(username)) return "vip2,vip3";
        return "";
    }
}
