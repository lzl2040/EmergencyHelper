package com.example.emergencyhelper.bean;

import java.io.Serializable;

/**
 * 用户的bean类
 * author ： yxm521
 * time    ： 2022/4/1
 */
public class User implements Serializable {
    //电话号码
    private String phone;
    //用户名
    private String username;
    //头像地址
    private int headerId;
    //密码
    private String pwd;

    public User(String phone, String username, int headerId, String pwd) {
        this.phone = phone;
        this.username = username;
        this.headerId = headerId;
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHeaderId() {
        return headerId;
    }

    public void setHeaderId(int headerId) {
        this.headerId = headerId;
    }
}
