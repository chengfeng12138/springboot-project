package com.chengfeng.study.myspringbootproject.mapper.user;

import com.chengfeng.study.myspringbootproject.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper class
 *
 * @author chengfeng`
 * @date 2020/8/1 /0001 23:58
 */
@Repository
public interface UserMapper {
    /**
     * 根据id查询用户
     * @param id 用户id
     * @param id 用户id
     * @author chengfeng
     * @date 2020/8/23 /0023 2:03
    */
    User getUserById(int id);

    /**
     * 根据用户名和密码查询用户
     * @param name 用户名
     * @param password 密码
     * @return User
     * @author chengfeng
     * @date 2020/8/23 /0023 2:04
    */
    User getUser(String name, String password);

    /**
     * 插入用户数据
     * @param user 用户数据
     * @return id 用户id
     * @author chengfeng
     * @date 2020/8/23 /0023 17:15
    */
    Integer insertUser(User user);

    /**
     * 根据用户名查询用户
     * @param name 用户数据
     * @return User 用户信息
     * @author chengfeng
     * @date 2020/8/29 /0029 17:05
    */
    User getUserByName(String name);

    /**
    * 更新用户信息
    * @param user 用户信息
    * @return int 删除结果
    * @author chengfeng
    * @date 2021/7/25 /0025 2:22
    **/
    int updateUser(User user);

    /**
     * 删除用户信息
     * @param id 用户id
     * @return int 删除结果
     * @author chengfeng
     * @date 2021/7/25 /0025 2:22
     **/
    int deleteUser(int id);

    /**
    * 查询所有用户信息
    * @return 所有用户信息
    * @author chengfeng
    * @date 2021/8/21 /0021 22:11
    */
    List<User> findAllUser();
}
