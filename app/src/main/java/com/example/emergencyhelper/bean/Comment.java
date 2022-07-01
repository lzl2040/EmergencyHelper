package com.example.emergencyhelper.bean;

/**
 * author ： yxm521
 * time    ： 2022/6/18
 */
public class Comment {
    private Integer commentId;
    private String content;
    private String postTime;
    private String postUser;
    private Integer topicId;

    public Comment() {
    }

    public Comment(Integer commentId, String content, String postTime, String postUser, Integer topicId) {
        this.commentId = commentId;
        this.content = content;
        this.postTime = postTime;
        this.postUser = postUser;
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

    public String getPostUser() {
        return postUser;
    }

    public void setPostUser(String postUser) {
        this.postUser = postUser;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
}
