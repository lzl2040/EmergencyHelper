package com.example.emergencyhelper.bean;

import java.io.Serializable;

/**
 * author ： yxm521
 * time    ： 2022/6/18
 */
public class TopicEntity implements Serializable {
    private Integer topicId;
    private String postTime;
    private String postUserPhone;
    private String postImgUrl;
    private String postUserName;
    private String title;
    private Integer viewNum;
    private Integer commentNum;

    public TopicEntity() {
    }

    public TopicEntity(Integer topicId, String postTime, String postUserPhone, String postImgUrl, String postUserName, String title, Integer viewNum, Integer commentNum) {
        this.topicId = topicId;
        this.postTime = postTime;
        this.postUserPhone = postUserPhone;
        this.postImgUrl = postImgUrl;
        this.postUserName = postUserName;
        this.title = title;
        this.viewNum = viewNum;
        this.commentNum = commentNum;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostUserPhone() {
        return postUserPhone;
    }

    public void setPostUserPhone(String postUserPhone) {
        this.postUserPhone = postUserPhone;
    }

    public String getPostImgUrl() {
        return postImgUrl;
    }

    public void setPostImgUrl(String postImgUrl) {
        this.postImgUrl = postImgUrl;
    }

    public String getPostUserName() {
        return postUserName;
    }

    public void setPostUserName(String postUserName) {
        this.postUserName = postUserName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }
}
