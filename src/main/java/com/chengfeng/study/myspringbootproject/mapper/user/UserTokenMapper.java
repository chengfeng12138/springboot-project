package com.chengfeng.study.myspringbootproject.mapper.user;

import com.chengfeng.study.myspringbootproject.pojo.UserToken;
import org.springframework.stereotype.Repository;

/**
 * UserTokenMapper class
 *
 * @author chengfeng
 * @date 2021/7/11 /0011 0:45
 */
@Repository
public interface UserTokenMapper {
    /**
    * 查询用户token
    * @param uuid token唯一id
    * @return UserToken 用户token
    * @author chengfeng
    * @date 2021/7/11 /0011 0:48
    **/
    UserToken getUserToken(String uuid);

    /**
     * 保存用户token
     * @param userToken 用户token pojo
     * @return UserToken 用户token
     * @author chengfeng
     * @date 2021/7/11 /0011 0:48
     **/
    Integer insertUserToken(UserToken userToken);

    /**
    * 更新token信息
    * @author chengfeng
    * @date 2021/7/11 /0011 1:51
    **/
    Integer updateUserToken(Integer userId, String loginTime, Integer id);
}
