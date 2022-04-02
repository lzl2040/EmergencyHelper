package com.example.emergencyhelper.entity;

/**
 * 用户的实体
 * author ： yxm521
 * time    ： 2022/3/29
 */
public class User {
    //电话号码
    private String phone;
    //用户名
    private String username;
    //头像ID
    private int imgId;
    //密码
    private String pwd;

    public User(String username, int imgId) {
        this.username = username;
        this.imgId = imgId;
    }

    public User(String phone, String username, int imgId, String pwd) {
        this.phone = phone;
        this.username = username;
        this.imgId = imgId;
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
