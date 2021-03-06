package com.example.emergencyhelper.requests;

import com.example.emergencyhelper.util.StaticData;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author ： yxm521
 * time    ： 2022/6/18
 */
public class TopicRequest {
    private String TAG = "TopicRequest";
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Request request;
    private RequestBody requestBody;
    private final Gson gson = StaticData.getGson();
    private final MediaType jsonType=MediaType.Companion.parse("application/json;charset=utf-8");

    /**
     * 获得话题列表
     * @param pageNum
     * @return
     */
    public Response getTopics(int pageNum){
        FormBody formBody = new FormBody.Builder()
                .add("pageNum",pageNum+"")
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getGetTopics())
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
     * 更新话题的观看次数
     * @param topicId
     * @return
     */
    public Response updateViewNum(int topicId){
        FormBody formBody = new FormBody.Builder()
                .add("topicId",topicId+"")
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getUpdateViewNum())
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
