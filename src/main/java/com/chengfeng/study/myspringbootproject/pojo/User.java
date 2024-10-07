package com.chengfeng.study.myspringbootproject.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * User class
 * 用户
 * @author chengfeng
 * @date 2020/7/12 /0012 23:00
 */
@ApiModel("用户实体类 - User") //swagger 给生成的实体类加注释
@Entity(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue
    @ApiModelProperty("唯一id")  //swagger 给实体类字段加注释
    private Integer id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("权限")
    private String perms;

    private String nickname; //昵称
    private String department; //部门
    private String note; //备注
    private String status; //状态
    private String createTime; //创建时间

    public User() {
    }

    public User(Integer id, String name, String password, String phone, String perms, String nickname, String department, String note, String status, String createTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.perms = perms;
        this.nickname = nickname;
        this.department = department;
        this.note = note;
        this.status = status;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(phone, user.phone) && Objects.equals(perms, user.perms) && Objects.equals(nickname, user.nickname) && Objects.equals(department, user.department) && Objects.equals(note, user.note) && Objects.equals(status, user.status) && Objects.equals(createTime, user.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, phone, perms, nickname, department, note, status, createTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", perms='" + perms + '\'' +
                ", nickname='" + nickname + '\'' +
                ", department='" + department + '\'' +
                ", note='" + note + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
