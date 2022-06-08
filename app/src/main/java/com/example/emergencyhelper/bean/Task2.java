package com.example.emergencyhelper.bean;

/**
 * author ： yxm521
 * time    ： 2022/6/7
 */
public class Task2 {
    private Integer taskId;
    private String taskContent;
    private String taskDeadline;
    private String taskSite;
    private String taskReward;
    private Integer categoryId;
    private String releaseUser;
    private String receiveUser;
    private Integer isComplete;

    public Task2(String taskContent, String taskDeadline, String taskSite, String taskReward, Integer categoryId, String releaseUser) {
        this.taskContent = taskContent;
        this.taskDeadline = taskDeadline;
        this.taskSite = taskSite;
        this.taskReward = taskReward;
        this.categoryId = categoryId;
        this.releaseUser = releaseUser;
    }

    public Task2(Integer taskId, String taskContent, String taskDeadline, String taskSite, String taskReward, Integer categoryId, String releaseUser, String receiveUser, Integer isComplete) {
        this.taskId = taskId;
        this.taskContent = taskContent;
        this.taskDeadline = taskDeadline;
        this.taskSite = taskSite;
        this.taskReward = taskReward;
        this.categoryId = categoryId;
        this.releaseUser = releaseUser;
        this.receiveUser = receiveUser;
        this.isComplete = isComplete;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getReleaseUser() {
        return releaseUser;
    }

    public void setReleaseUser(String releaseUser) {
        this.releaseUser = releaseUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }
}
