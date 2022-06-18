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

    /**
     * 获得自己发布的任务
     * @param phone 电话号码
     * @param pageNum 页数
     * @return
     */
    public Response getSelfReleaseTasks(String phone,int pageNum){
        FormBody formBody = new FormBody.Builder()
                .add("phone",phone)
                .add("pageNum",pageNum+"")
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getGetSelfReleaseTasks())
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
     * 获得自己领取的任务
     * @param phone 电话号码
     * @param pageNum 页数
     * @return
     */
    public Response getSelfReceiveTasks(String phone,int pageNum){
        FormBody formBody = new FormBody.Builder()
                .add("phone",phone)
                .add("pageNum",pageNum+"")
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getGetSelfReceiveTasks())
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
     * 领取任务
     * @param phone 电话号码
     * @param taskId 任务ID
     * @param categoryId 分类ID
     * @return
     */
    public Response receiveTask(String phone,int taskId,int categoryId){
        FormBody formBody = new FormBody.Builder()
                .add("phone",phone)
                .add("taskId",taskId+"")
                .add("categoryId",categoryId+"")
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getReceiveTask())
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
     * 根据关键词搜索任务
     * @param keyWord 关键词
     * @param phone 本用户的电话号码
     * @param pageNum 页数
     * @return
     */
    public Response searchTask(String keyWord,String phone,int pageNum){
        FormBody formBody = new FormBody.Builder()
                .add("phone",phone)
                .add("keyWord",keyWord)
                .add("pageNum",pageNum+"")
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getSearchTask())
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
     * 确认任务完成
     * @param taskId 任务ID
     * @return
     */
    public Response confirmComplete(int taskId,String phone,String score){
        FormBody formBody = new FormBody.Builder()
                .add("taskId",taskId+"")
                .add("phone",phone)
                .add("score",score)
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getConfirmTaskComplete())
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
     * 取消任务
     * @param taskId 任务ID
     * @return
     */
    public Response cancelTask(int taskId){
        FormBody formBody = new FormBody.Builder()
                .add("taskId",taskId+"")
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getCancelTask())
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
