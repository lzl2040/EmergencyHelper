package com.example.emergencyhelper.entity;

import java.io.Serializable;

public class TopicEntity implements Serializable {
    private String name;
    private String title;
    private String detail;
    private int see_num;
    private int comment_num;
    private int img_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getSee_num() {
        return see_num;
    }

    public void setSee_num(int see_num) {
        this.see_num = see_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
