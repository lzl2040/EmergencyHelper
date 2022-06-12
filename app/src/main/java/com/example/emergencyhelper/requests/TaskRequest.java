package com.example.emergencyhelper.requests;

import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.util.StaticData;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 任务的请求类
 * author ： yxm521
 * time    ： 2022/4/28
 */
public class TaskRequest {
    private String TAG = "TaskRequest";
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Request request;
    private RequestBody requestBody;
    private final Gson gson = StaticData.getGson();
    private final MediaType jsonType=MediaType.Companion.parse("application/json;charset=utf-8");

    public Response postTask(Task task2){
        String jsonTask = gson.toJson(task2);
        System.out.println("字符串为:"+jsonTask);
        requestBody = RequestBody.Companion.create(jsonTask,jsonType);
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getAddTaskUrl())
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
     * 获得所有的任务
     * @return
     */
    public Response getTasks(){
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getGetAllTasksUrl())
                .build();
        try {
            return okHttpClient.newCall(request).execute();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据分类获得任务
     * @param categoryId 分类Id
     * @return
     */
    public Response getTaskByCategory(Integer categoryId,String phone,int pageNum){
        FormBody formBody = new FormBody.Builder()
                .add("categoryId",categoryId+"")
                .add("phone",phone)
                .add("pageNum",pageNum+"")
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getGetTasksByCategoryUrl())
                .post(formBody)
                .build();
        try {
            return okHttpClient.newCall(request).execute();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得推荐任务列表
     * @param phone
     * @return
     */
    public Response getTasksByRecommend(String phone){
        FormBody body = new FormBody.Builder()
                .add("phone",phone)
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getGetTasksByRecommend())
                .post(body)
                .build();
        try {
            return okHttpClient.newCall(request).execute();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
