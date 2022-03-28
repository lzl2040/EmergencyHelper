package com.example.emergencyhelper.entity;

import java.io.Serializable;

public class CommentEntity implements Serializable {
    private String name;
    private String time;
    private String content;
    private int img_id;
    private String poster_name;
    private String page_desc;
    private String page_post_time;
    private int poster_header;
    private int type;               //1代表是发布者的内容,2代表是评论
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getPoster_name() {
        return poster_name;
    }

    public void setPoster_name(String poster_name) {
        this.poster_name = poster_name;
    }

    public String getPage_desc() {
        return page_desc;
    }

    public void setPage_desc(String page_desc) {
        this.page_desc = page_desc;
    }

    public String getPage_post_time() {
        return page_post_time;
    }

    public void setPage_post_time(String page_post_time) {
        this.page_post_time = page_post_time;
    }

    public int getPoster_header() {
        return poster_header;
    }

    public void setPoster_header(int poster_header) {
        this.poster_header = poster_header;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
