package com.example.emergencyhelper.entity;

import java.io.Serializable;

public class CommentEntity implements Serializable {
    private Integer commentId;
    private String content;
    private String postTime;
    private String postUserPhone;
    private String postImgUrl;
    private String postUserName;
    private Integer topicId;

    public CommentEntity() {
    }

    public CommentEntity(Integer commentId, String content, String postTime, String postUserPhone, String postImgUrl, String postUserName, Integer topicId) {
        this.commentId = commentId;
        this.content = content;
        this.postTime = postTime;
        this.postUserPhone = postUserPhone;
        this.postImgUrl = postImgUrl;
        this.postUserName = postUserName;
        this.topicId = topicId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
}
