package com.example.emergencyhelper.util;

import com.example.emergencyhelper.entity.User;

import java.util.List;

/**
 * 流转数据,便于操作
 * author ： yxm521
 * time    ： 2022/3/28
 */
public class StaticData {
    private static List<User> userList;
    //底部导航栏的位置,默认为0
    private static int bottomPosition = 0;

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
}
