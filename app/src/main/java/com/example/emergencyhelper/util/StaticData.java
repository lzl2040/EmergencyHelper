package com.example.emergencyhelper.util;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.bean.Expert;
import com.example.emergencyhelper.bean.ExpertCategory;
import com.example.emergencyhelper.bean.TaskCategory;
import com.example.emergencyhelper.bean.Communicate;
import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.bean.User;
import com.example.emergencyhelper.bean.UserAndTaskCategory;
import com.example.emergencyhelper.entity.SortItemEntity;
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
    //任务分类
    private static List<TaskCategory> categories;
    //任务数据
    private static List<Task> taskList;
    //聊天数据
    private static List<Communicate> communicates;
    //专家数据
    private static List<Expert> experts;
    //专家分类
    private static List<ExpertCategory> expertCategories;
    //用户与分类之间的对应关系
    private static List<UserAndTaskCategory> userAndTaskCategories;
    //底部导航栏的位置,默认为0
    private static int bottomPosition = 0;
    //对发布任务的操作图标
    public static AdapterItem[] menuItems = new AdapterItem[]{
            new AdapterItem("确认完成", R.mipmap.determine),
            new AdapterItem("取消任务", R.mipmap.error)
    };
    //首页分类的图标
    public static SortItemEntity[] sortItemEntities = new SortItemEntity[]{
            new SortItemEntity(R.drawable.family_service,R.string.home_service),
            new SortItemEntity(R.drawable.social_service,R.string.social_service),
            new SortItemEntity(R.mipmap.emergency_service,R.string.emergency_service),
            new SortItemEntity(R.drawable.school_service,R.string.school_service),
            new SortItemEntity(R.drawable.old_service,R.string.old_service),
            new SortItemEntity(R.drawable.children_service,R.string.children_service),
            new SortItemEntity(R.drawable.cooporate_service,R.string.cooporate_service),
            new SortItemEntity(R.mipmap.disable,R.string.disable_service),
            new SortItemEntity(R.mipmap.expert,R.string.expert_certificate)
    };
    private static User curUser;
    //聊天界面跳转界面的原界面
    private static Class jumpClass;

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

    public static List<TaskCategory> getCategories() {
        return categories;
    }

    public static void setCategories(List<TaskCategory> categories) {
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

    public static List<Communicate> getCommunicates() {
        return communicates;
    }

    public static void setCommunicates(List<Communicate> communicates) {
        StaticData.communicates = communicates;
    }

    public static Class getJumpClass() {
        return jumpClass;
    }

    public static void setJumpClass(Class jumpClass) {
        StaticData.jumpClass = jumpClass;
    }

    public static List<Expert> getExperts() {
        return experts;
    }

    public static void setExperts(List<Expert> experts) {
        StaticData.experts = experts;
    }

    public static List<ExpertCategory> getExpertCategories() {
        return expertCategories;
    }

    public static void setExpertCategories(List<ExpertCategory> expertCategories) {
        StaticData.expertCategories = expertCategories;
    }

    public static List<UserAndTaskCategory> getUserAndTaskCategories() {
        return userAndTaskCategories;
    }

    public static void setUserAndTaskCategories(List<UserAndTaskCategory> userAndTaskCategories) {
        StaticData.userAndTaskCategories = userAndTaskCategories;
    }
}
