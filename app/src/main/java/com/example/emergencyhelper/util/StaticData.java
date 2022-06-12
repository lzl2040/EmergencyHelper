package com.example.emergencyhelper.util;

import android.content.SharedPreferences;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.application.HelperApplication;
import com.example.emergencyhelper.bean.Expert;
import com.example.emergencyhelper.bean.ExpertCategory;
import com.example.emergencyhelper.bean.TaskCategory;
import com.example.emergencyhelper.bean.Communicate;
import com.example.emergencyhelper.bean.TaskEntity;
import com.example.emergencyhelper.bean.User;
import com.example.emergencyhelper.bean.UserAndTaskCategory;
import com.example.emergencyhelper.entity.SortItemEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xuexiang.xui.adapter.simple.AdapterItem;

import java.util.List;

/**
 * 流转数据,便于操作
 * author ： yxm521
 * time    ： 2022/3/28
 */
public class StaticData {
    private static SharedPreferences userSP = HelperApplication.getUserSP();
    //用户列表
    private static List<User> userList;
    //任务数据
    private static List<TaskEntity> taskList;
    //聊天数据
    private static List<Communicate> communicates;
    //专家数据
    private static List<Expert> experts;
    //专家分类
    private static List<ExpertCategory> expertCategories;
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
    public static String categoryNames[] = {"家庭服务","社会服务","应急服务","学校服务","老人服务","小孩服务","合作互助","残障服务"};
    private static User curUser;
    //聊天界面跳转界面的原界面
    private static Class jumpClass;
    //Gson
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    /**
     * 后端使用的接口
     */
    //基础地址
    private static String baseUrl = "http://121.5.128.157:8060/HelperBs";
    //登录
    private static String loginUrl = "/login";
    //注册
    private static String registerUrl = "/register";
    //得到所有任务
    private static String getAllTasksUrl = "/get/tasks";
    //添加任务
    private static String addTaskUrl = "/add/task";
    //根据类别查找任务
    private static String getTasksByCategoryUrl = "/get/tasks/category";
    //上传头像
    private static String uploadHeader = "/upload/header";
    //更新用户信息
    private static String updateInfo = "/update/user";
    //获得推荐任务列表
    private static String getTasksByRecommend = "/get/tasks/recommend";

    /**
     * 将用户信息存放至 名为 user 的 SharedPreferences中
     * @param user 注册返回的用户
     */
    public static void setUserSP(User user){
        SharedPreferences.Editor userEditor = userSP.edit();
        userEditor.putString("phone",user.getPhone());
        userEditor.putString("pwd",user.getPwd());
        if(user.getName() != null){
            userEditor.putString("name",user.getName());
        }
        if(user.getImgUrl() != null){
            userEditor.putString("imgurl",user.getImgUrl());
        }
        if(user.getScores() == null){
            userEditor.putInt("scores",0);
        }else{
            userEditor.putInt("scores",user.getScores());
        }

        userEditor.apply();
    }

    public static Gson getGson() {
        return gson;
    }

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

    public static List<TaskEntity> getTaskList() {
        return taskList;
    }

    public static void setTaskList(List<TaskEntity> taskList) {
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

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getLoginUrl() {
        return loginUrl;
    }

    public static String getRegisterUrl() {
        return registerUrl;
    }

    public static String getGetAllTasksUrl() {
        return getAllTasksUrl;
    }

    public static void setGetAllTasksUrl(String getAllTasksUrl) {
        StaticData.getAllTasksUrl = getAllTasksUrl;
    }

    public static String getAddTaskUrl() {
        return addTaskUrl;
    }

    public static String getGetTasksByCategoryUrl() {
        return getTasksByCategoryUrl;
    }

    public static String getUploadHeader() {
        return uploadHeader;
    }

    public static String getUpdateInfo() {
        return updateInfo;
    }

    public static String getGetTasksByRecommend() {
        return getTasksByRecommend;
    }
}
