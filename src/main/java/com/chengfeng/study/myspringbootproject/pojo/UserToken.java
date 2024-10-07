package com.chengfeng.study.myspringbootproject.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * UserToken class
 * 用户登录token
 * @author chengfeng
 * @date 2021/7/11 /0011 0:34
 */
@Entity(name = "userToken")
public class UserToken {
    @Id
    @GeneratedValue
    public Integer id;
    public String uuid;
    public Integer userId;
    public String loginTime;
    public String createTime;
    public Integer state;

    public UserToken(Integer id, String uuid, Integer userId, String loginTime, String createTime, Integer state) {
        this.id = id;
        this.uuid = uuid;
        this.userId = userId;
        this.loginTime = loginTime;
        this.createTime = createTime;
        this.state = state;
    }

    public UserToken() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToken userToken = (UserToken) o;
        return Objects.equals(id, userToken.id) && Objects.equals(uuid, userToken.uuid) && Objects.equals(userId, userToken.userId) && Objects.equals(loginTime, userToken.loginTime) && Objects.equals(createTime, userToken.createTime) && Objects.equals(state, userToken.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, userId, loginTime, createTime, state);
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", userId='" + userId + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", state=" + state +
                '}';
    }
}
