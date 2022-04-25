package com.example.emergencyhelper.bean;

/**
 * author ： yxm521
 * time    ： 2022/4/4
 */
public class UserAndTaskCategory implements Comparable<UserAndTaskCategory>{
    private User user;
    private TaskCategory category;
    private int num;

    public UserAndTaskCategory(User user, TaskCategory category, int num) {
        this.user = user;
        this.category = category;
        this.num = num;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int compareTo(UserAndTaskCategory userAndTaskCategory) {
        //降序排序
        return userAndTaskCategory.getNum() - num;
    }
}
