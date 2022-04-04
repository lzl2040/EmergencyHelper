package com.example.emergencyhelper.bean;


import java.io.Serializable;

/**
 * 任务的bean类
 * author ： yxm521
 * time    ： 2022/4/1
 */
public class Task implements Serializable {
    //任务ID
    private int taskId;
    //任务内容
    private String content;
    //任务截止日期
    private String deadline;
    //任务地址
    private String site;
    //任务奖励
    private int reward;
    //任务发布者
    private User postUser;
    //任务领取者
    private User receiveUser;
    //分类
    private TaskCategory category;

    public Task(String content, String deadline, String site, int reward, User postUser, TaskCategory category) {
        this.content = content;
        this.deadline = deadline;
        this.site = site;
        this.reward = reward;
        this.postUser = postUser;
        this.category = category;
    }

    public Task() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public User getPostUser() {
        return postUser;
    }

    public void setPostUser(User postUser) {
        this.postUser = postUser;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }
}
