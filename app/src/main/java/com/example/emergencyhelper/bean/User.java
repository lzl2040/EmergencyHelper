package com.example.emergencyhelper.bean;

import java.io.Serializable;

/**
 * 用户的bean类
 * author ： yxm521
 * time    ： 2022/4/1
 */
public class User implements Serializable {
    private String phone;
    private String name;
    private String pwd;
    private String imgUrl;
    private Integer scores;

    public User() {
    }

    public User(String phone, String name, String pwd, String imgUrl, Integer scores) {
        this.phone = phone;
        this.name = name;
        this.pwd = pwd;
        this.imgUrl = imgUrl;
        this.scores = scores;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }
}
