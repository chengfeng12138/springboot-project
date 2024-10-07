package com.chengfeng.study.myspringbootproject.service.user;

import cn.hutool.core.date.DateUtil;
import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.mapper.user.UserMapper;
import com.chengfeng.study.myspringbootproject.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * UserService class
 *
 * @author chengfeng
 * @date 2020/8/2 /0002 0:04
 */
@Service
public class UserService {
    private static final Logger log = Logger.getLogger(UserService.class.getName());

    @Autowired
    UserMapper userMapper;

    /**
    * @description 根据id获取用户
    * @author chengfeng
    * @date 2021/8/8 /0008 22:06
    **/
    public User getUserById(int id){
        return userMapper.getUserById(id);
    }

    /**
     * @description 根据用户名和密码获取用户
     * @author chengfeng
     * @date 2021/8/8 /0008 22:06
     **/
    public User getUser(String name, String password){
        return userMapper.getUser(name, password);
    }

    /**
     * @description 根据用户名获取用户
     * @author chengfeng
     * @date 2021/8/8 /0008 22:06
     **/
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    /**
     * 查询所有用户信息
     * @return 所有用户信息
     * @author chengfeng
     * @date 2021/8/21 /0021 22:11
     */
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    /**
     * @method 新增用户
     * @author chengfeng
     * @date 2020/8/29 /0029 17:12
    */
    public ResponseResult insertUser(User user) {
        ResponseResult responseResult;
        String name = user.getName();
        if (name == null){
            responseResult = new ResponseResult(501, "用户信息缺失,添加用户失败!", null, false, "user");
            return responseResult;
        }
        Integer userId = null;
        if (user.getId() == null) {
            //新增用户
            User userByName = userMapper.getUserByName(name);
            if (userByName != null){
                //用户名重复
                responseResult = new ResponseResult(502, "用户名重复!", null, false, "user");
                return responseResult;
            }
            user.setCreateTime(DateUtil.now());
            userId = userMapper.insertUser(user);
        } else {
            //更新用户
            userId = user.getId();
            userMapper.updateUser(user);
        }
        if (userId == null){
            responseResult = new ResponseResult(500, "保存用户失败!", null, false, "user");
            return responseResult;
        }

        responseResult = new ResponseResult(true);
        user.setId(userId);
        responseResult.setData(user);
        return responseResult;
    }

    /**
    * 删除用户信息
    * @param id 用户id
    * @author chengfeng
    * @date 2021/9/11 /0011 0:50
    */
    public int deleteUser(int id) {
        log.log(Level.INFO, "current delete customer id: " + id);
        return userMapper.deleteUser(id);
    }
}
