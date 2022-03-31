package com.example.emergencyhelper.application;

import android.app.Application;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.entity.User;
import com.example.emergencyhelper.util.StaticData;

import java.util.ArrayList;
import java.util.List;

/**
 * author ： yxm521
 * time    ： 2022/3/29
 */
public class HelperApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        saveUsers();
        saveTasks();
    }

    public void saveUsers(){
        List<User> users = new ArrayList<>();
        User user1 = new User("小西瓜", R.drawable.a13);
        User user2 = new User("张先生",R.drawable.a12);
        User user3 = new User("lZL2040",R.drawable.a11);
        User user4 = new User("安之若素",R.drawable.a10);
        User user5 = new User("奶油桃子",R.drawable.a9);
        User user6 = new User("青青草原",R.drawable.a8);
        User user7 = new User("墨水轻轻",R.drawable.a7);
        User user8 = new User("石梅",R.drawable.a6);
        User user9 = new User("小兔子",R.drawable.a5);
        User user10 = new User("余生",R.drawable.a4);
        User user11 = new User("飘飘呼",R.drawable.a3);
        User user12 = new User("寒烟若雨",R.drawable.a2);
        User user13 = new User("镜花水月",R.drawable.a14);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);
        users.add(user11);
        users.add(user12);
        users.add(user13);
        StaticData.setUserList(users);
    }
    
    public void saveTasks(){
        
    }
}
