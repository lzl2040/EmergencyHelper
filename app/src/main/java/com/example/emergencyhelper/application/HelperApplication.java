package com.example.emergencyhelper.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.multidex.MultiDex;

/**
 * author ： yxm521
 * time    ： 2022/3/29
 */
public class HelperApplication extends Application {
    private static SharedPreferences userSP;
    private Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //存放登录用户信息的
        userSP = context.getSharedPreferences("user", MODE_PRIVATE);
    }

    public static SharedPreferences getUserSP() {
        return userSP;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
