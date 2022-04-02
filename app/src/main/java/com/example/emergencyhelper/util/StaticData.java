package com.example.emergencyhelper.util;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.bean.Category;
import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.entity.TaskEntity;
import com.example.emergencyhelper.bean.User;
import com.xuexiang.xui.adapter.simple.AdapterItem;

import java.util.List;

/**
 * 流转数据,便于操作
 * author ： yxm521
 * time    ： 2022/3/28
 */
public class StaticData {
    //用户列表
    private static List<User> userList;
    //分类
    private static List<Category> categories;
    //任务
    private static List<Task> taskList;
    //底部导航栏的位置,默认为0
    private static int bottomPosition = 0;
    //对发布任务的操作图标
    public static AdapterItem[] menuItems = new AdapterItem[]{
            new AdapterItem("确认完成", R.mipmap.determine),
            new AdapterItem("取消任务", R.mipmap.error)
    };
    private static User curUser;

    public static List<TaskEntity> searchTasks;

    public static List<User> getUserList() {
        return userList;
    }

    public static void setUserList(List<User> userList) {
        StaticData.userList = userList;
    }

    public static int getBottomPosition() {
        return bottomPosition;
    }

    public static void setBottomPosition(int bottomPosition) {
        StaticData.bottomPosition = bottomPosition;
    }

    public static List<TaskEntity> getTasks() {
        return tasks;
    }

    public static void setTasks(List<TaskEntity> tasks) {
        StaticData.tasks = tasks;
    }

    public static List<Category> getCategories() {
        return categories;
    }

    public static void setCategories(List<Category> categories) {
        StaticData.categories = categories;
    }

    public static List<Task> getTaskList() {
        return taskList;
    }

    public static void setTaskList(List<Task> taskList) {
        StaticData.taskList = taskList;
    }

    public static User getCurUser() {
        return curUser;
    }

    public static void setCurUser(User curUser) {
        StaticData.curUser = curUser;
    }
}
