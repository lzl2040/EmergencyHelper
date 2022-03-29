package com.example.emergencyhelper.entity;

import java.io.Serializable;

/**
 * 任务的实体类,暂时没有区分领取任务、发布任务,都用这个实体类
 */
public class TaskEntity implements Serializable {
    //用户名字
    private String name;
    //任务描述
    private String desc;
    //用户头像
    private int header;
    //截至日期
    private String deadline;
    //任务地点
    private String site;
    //报酬
    private String reward;
    //领取任务的人
    private User getTaskUser;
    //发布任务的人
    private User postTaskUser;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getHeader() {
        return header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public User getGetTaskUser() {
        return getTaskUser;
    }

    public void setGetTaskUser(User getTaskUser) {
        this.getTaskUser = getTaskUser;
    }

    public User getPostTaskUser() {
        return postTaskUser;
    }

    public void setPostTaskUser(User postTaskUser) {
        this.postTaskUser = postTaskUser;
    }
}
