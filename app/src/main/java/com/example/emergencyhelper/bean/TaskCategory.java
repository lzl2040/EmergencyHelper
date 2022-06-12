package com.example.emergencyhelper.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务分类
 * author ： yxm521
 * time    ： 2022/4/1
 */
public class TaskCategory {
    private int categoryId;
    private String name;
    private List<TaskEntity> tasks = new ArrayList<>();

    public TaskCategory(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }
}
