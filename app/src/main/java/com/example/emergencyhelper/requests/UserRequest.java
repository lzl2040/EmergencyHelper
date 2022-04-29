package com.example.emergencyhelper.requests;

import com.example.emergencyhelper.bean.User;
import com.example.emergencyhelper.util.StaticData;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 用户的请求类
 * author ： yxm521
 * time    ： 2022/4/28
 */
public class UserRequest {
    private String TAG = "UserRequest";
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Request request;
    private RequestBody requestBody;
    private final Gson gson = StaticData.getGson();
    private final MediaType jsonType=MediaType.Companion.parse("application/json;charset=utf-8");

    /**
     * 注册接口
     * @param phone 电话号码
     * @param pwd 密码
     * @return 200为成功
     */
    public Response register(String phone, String pwd){
        User user = new User(phone,pwd);
        String jsonUser = gson.toJson(user);
        requestBody = RequestBody.Companion.create(jsonUser, jsonType);
        request = new Request.Builder()
                .url(StaticData.getBaseUrl()+StaticData.getRegisterUrl())
                .post(requestBody)
                .build();
        try {
            return okHttpClient.newCall(request).execute();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 登录接口
     * @param phone 电话号码
     * @param pwd 密码
     * @return 200成功
     */
    public Response login(String phone,String pwd){
        FormBody formBody = new FormBody.Builder()
                .add("phone",phone)
                .add("pwd",pwd)
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getLoginUrl())
                .post(formBody)
                .build();
        try {
            return okHttpClient.newCall(request).execute();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
