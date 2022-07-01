package com.example.emergencyhelper.requests;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.bean.Comment;
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
public class CommentRequest {
    private String TAG = "TopicRequest";
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Request request;
    private RequestBody requestBody;
    private final Gson gson = StaticData.getGson();
    private final MediaType jsonType=MediaType.Companion.parse("application/json;charset=utf-8");

    /**
     * 添加评论
     * @param comment
     * @return
     */
    public Response addComment(Comment comment){
        String commentJson = gson.toJson(comment);
        requestBody = RequestBody.Companion.create(commentJson,jsonType);
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getAddComment())
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
     * 得到评论
     * @param topicId
     * @param pageNum
     * @return
     */
    public Response getComments(int topicId,int pageNum){
        FormBody formBody = new FormBody.Builder()
                .add("topicId",topicId+"")
                .add("pageNum",pageNum+"")
                .build();
        request = new Request.Builder()
                .url(StaticData.getBaseUrl() + StaticData.getGetComments())
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
