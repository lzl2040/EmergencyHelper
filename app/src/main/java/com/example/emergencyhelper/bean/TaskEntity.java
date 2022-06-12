package com.example.emergencyhelper.bean;


import java.io.Serializable;

/**
 * 任务的bean类
 * author ： yxm521
 * time    ： 2022/4/1
 */
public class TaskEntity implements Serializable {
    private Integer taskId;
    private String taskContent;
    private String taskDeadline;
    private String taskSite;
    private String taskReward;
    private Integer isComplete;
    //发布任务者信息
    private String releaseUsername;
    private String releasePhone;
    private String releaseImgUrl;
    //接受任务者信息
    private String receivePhone;
    private String receiveUsername;
    private String receiveImgUrl;
    //任务类别
    private Integer categoryId;

    public TaskEntity() {
    }

    public TaskEntity(Integer taskId, String taskContent, String taskDeadline, String taskSite, String taskReward, Integer isComplete, String releaseUsername, String releasePhone, String releaseImgUrl, String receivePhone, String receiveUsername, String receiveImgUrl, Integer categoryId) {
        this.taskId = taskId;
        this.taskContent = taskContent;
        this.taskDeadline = taskDeadline;
        this.taskSite = taskSite;
        this.taskReward = taskReward;
        this.isComplete = isComplete;
        this.releaseUsername = releaseUsername;
        this.releasePhone = releasePhone;
        this.releaseImgUrl = releaseImgUrl;
        this.receivePhone = receivePhone;
        this.receiveUsername = receiveUsername;
        this.receiveImgUrl = receiveImgUrl;
        this.categoryId = categoryId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(String taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getTaskSite() {
        return taskSite;
    }

    public void setTaskSite(String taskSite) {
        this.taskSite = taskSite;
    }

    public String getTaskReward() {
        return taskReward;
    }

    public void setTaskReward(String taskReward) {
        this.taskReward = taskReward;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public String getReleaseUsername() {
        return releaseUsername;
    }

    public void setReleaseUsername(String releaseUsername) {
        this.releaseUsername = releaseUsername;
    }

    public String getReleasePhone() {
        return releasePhone;
    }

    public void setReleasePhone(String releasePhone) {
        this.releasePhone = releasePhone;
    }

    public String getReleaseImgUrl() {
        return releaseImgUrl;
    }

    public void setReleaseImgUrl(String releaseImgUrl) {
        this.releaseImgUrl = releaseImgUrl;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveUsername() {
        return receiveUsername;
    }

    public void setReceiveUsername(String receiveUsername) {
        this.receiveUsername = receiveUsername;
    }

    public String getReceiveImgUrl() {
        return receiveImgUrl;
    }

    public void setReceiveImgUrl(String receiveImgUrl) {
        this.receiveImgUrl = receiveImgUrl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
