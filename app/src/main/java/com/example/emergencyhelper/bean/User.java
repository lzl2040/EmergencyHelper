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
    private String name;
    //头像地址
    private Integer headerId;
    //密码
    private String pwd;

    public User(String phone, String name, Integer headerId, String pwd) {
        this.phone = phone;
        this.name = name;
        this.headerId = headerId;
        this.pwd = pwd;
    }

    public User(String phone, String pwd) {
        this.phone = phone;
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

    public int getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Integer headerId) {
        this.headerId = headerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
