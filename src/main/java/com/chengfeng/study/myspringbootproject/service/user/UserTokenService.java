package com.chengfeng.study.myspringbootproject.service.user;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.mapper.user.UserMapper;
import com.chengfeng.study.myspringbootproject.mapper.user.UserTokenMapper;
import com.chengfeng.study.myspringbootproject.pojo.User;
import com.chengfeng.study.myspringbootproject.pojo.UserToken;
import com.chengfeng.study.myspringbootproject.utils.ResultUtil;
import com.chengfeng.study.myspringbootproject.websocket.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserTokenService class
 *
 * @author chengfeng
 * @date 2021/7/11 /0011 0:53
 */
@Service
public class UserTokenService {
    @Autowired
    UserTokenMapper userTokenMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 查询用户token
     *
     * @author chengfeng
     * @date 2021/7/11 /0011 0:56
     **/
    public ResponseResult getUserToken(String uuid, String name, String password) {
        UserToken userToken = userTokenMapper.getUserToken(uuid);
        if (userToken == null) {
            return ResultUtil.error(404, "未找到用户token信息!");
        }
        User user = userMapper.getUser(name, password);
        if (user == null) {
            return ResultUtil.error(404, "未找到用户信息!");
        }
        String createTime = userToken.getCreateTime();
        String now = DateUtil.now();
        if (DateUtil.between(DateUtil.parse(createTime), DateUtil.parse(DateUtil.now()), DateUnit.SECOND, false) > 3 * 60) {
            //二维码三分钟失效
            return ResultUtil.error(500, "二维码已失效!");
        }
        //更新用户token信息
        Integer integer = userTokenMapper.updateUserToken(user.getId(), now, userToken.getId());
        System.out.println("更新token结果: " + integer);
        ResponseResult result = ResultUtil.success(user, "scanLogin");
        //websocket给页面发送消息
        WebSocketClient.appointSending(uuid, JSON.toJSONString(result));
        return result;
    }

    /**
     * 保存用户token
     *
     * @author chengfeng
     * @date 2021/7/11 /0011 0:59
     **/
    public int insertUserToken(UserToken userToken) {
        return userTokenMapper.insertUserToken(userToken);
    }

}
