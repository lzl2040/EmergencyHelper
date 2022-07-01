package com.example.emergencyhelper.activity.topic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.main.MainActivity;
import com.example.emergencyhelper.adapter.CommentAdapter;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.bean.Comment;
import com.example.emergencyhelper.bean.TaskEntity;
import com.example.emergencyhelper.bean.TopicEntity;
import com.example.emergencyhelper.entity.CommentEntity;
import com.example.emergencyhelper.requests.CommentRequest;
import com.example.emergencyhelper.util.CheckUtil;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import okhttp3.Response;

public class CommentActivity extends BaseActivity {
    private String TAG = "CommentActivity";
    private Button send;
    private EditText input_box;
    private ImageView backImg;
    private List<CommentEntity> comments = new ArrayList<>();
    private CommentAdapter adapter;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private int pageNum = 1;
    private TopicEntity topic;
    private Comment comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
        setListener();
        setAdapter();
    }

    public void addData(){
        new GetCommentsTask().execute(topic.getTopicId());
    }

    @Override
    public void initView() {
        super.initView();
        Intent intent = getIntent();
        topic = (TopicEntity) intent.getSerializableExtra("topic");
        send = findViewById(R.id.send);
        input_box = findViewById(R.id.make_comment);
        recyclerView = findViewById(R.id.recyclerview);
        refreshLayout = findViewById(R.id.refreshLayout);
        backImg = findViewById(R.id.back);
    }

    @Override
    public void setListener() {
        Log.d(TAG,"setListener...");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = input_box.getText().toString();
                if(!CheckUtil.checkStringNull(content)){
                    comment = new Comment();
                    comment.setContent(content);
                    comment.setPostTime(getCurrentTime());
                    comment.setPostUser(StaticData.getCurUser().getPhone());
                    comment.setTopicId(topic.getTopicId());
                    new AddCommentTask().execute(comment);
                    input_box.setText("");
                }else{
                    ViewUtil.showErrorToast("评论不能为空",context);
                }
            }
        });

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context, MainActivity.class);
                finish();
            }
        });

        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum = pageNum + 1;
                new GetCommentsTask().execute(topic.getTopicId());
            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.d(TAG,"setAdapter...");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CommentAdapter(this,comments,topic);
        recyclerView.setAdapter(adapter);
        addData();
    }

    public String getCurrentTime(){
        SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-YY hh:mm");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time = sf.format(timestamp);//时间
        return time;
    }

    @SuppressLint("StaticFieldLeak")
    public class GetCommentsTask extends AsyncTask<Integer,Integer,Integer>{
        @SneakyThrows
        @Override
        protected Integer doInBackground(Integer... integers) {
            Response response = new CommentRequest().getComments(integers[0],pageNum);
            if(response == null){
                return -1;
            }
            Gson gson = StaticData.getGson();
            String body = response.body().string();
            Log.e(TAG,"GetCommentsTask:"+body);
            Type type = new TypeToken<List<CommentEntity>>(){}.getType();
            List<CommentEntity> mid = gson.fromJson(body,type);
            comments.addAll(mid);
            return response.code();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            String msg = "";
            switch (integer){
                case -1:
                    msg = "请检查网络状态后重新尝试！";
                    ViewUtil.showErrorToast(msg, context);
                    break;
                case 200:
                    refreshLayout.finishLoadMore();
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class AddCommentTask extends AsyncTask<Comment,Integer,Integer>{

        @Override
        protected Integer doInBackground(Comment... comments) {
            Response response = new CommentRequest().addComment(comments[0]);
            if(response == null){
                return -1;
            }
            return response.code();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            String msg = "";
            switch (integer){
                case -1:
                    msg = "请检查网络状态后重新尝试！";
                    ViewUtil.showErrorToast(msg, context);
                    break;
                case 200:
                    CommentEntity entity = new CommentEntity();
                    entity.setContent(comment.getContent());
                    entity.setPostImgUrl(StaticData.getCurUser().getImgUrl());
                    entity.setPostUserName(StaticData.getCurUser().getName());
                    entity.setPostTime(comment.getPostTime());
                    comments.add(entity);
                    adapter.notifyDataSetChanged();
                    msg = "发送成功!";
                    ViewUtil.showNotice(msg,context);
                    break;
                case 401:
                    msg = "发生冲突";
                    ViewUtil.showErrorToast(msg,context);
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }
}