package com.example.emergencyhelper.activity.my;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.main.MainActivity;
import com.example.emergencyhelper.adapter.PostTaskAdapter;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.bean.TaskEntity;
import com.example.emergencyhelper.requests.TaskRequest;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import okhttp3.Response;

public class PostActivity extends BaseActivity {
    private String TAG = "PostActivity";
    private RecyclerView recyclerView;
    private List<TaskEntity> tasks = new ArrayList<>();
    private ImageView backImg;
    private int pageNum = 1;
    private PostTaskAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private MyReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initView();
        setListener();
        setAdapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void initView() {
        //super.initView();
        //注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("cancelTask");
        receiver = new MyReceiver();
        registerReceiver(receiver,filter);
        backImg = findViewById(R.id.back);
        recyclerView = findViewById(R.id.recyclerview);
        refreshLayout = findViewById(R.id.refreshLayout);
    }

    @Override
    public void setListener() {
        //super.setListener();
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context, MainActivity.class);
                finish();
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = pageNum + 1;
                new GetSelfReleaseTasksTask().execute(StaticData.getCurUser().getPhone());
            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<TaskEntity> mid = new ArrayList<>();
        adapter = new PostTaskAdapter(this,mid);
        recyclerView.setAdapter(adapter);
        addData();
    }

    public void addData(){
        new GetSelfReleaseTasksTask().execute(StaticData.getCurUser().getPhone());
    }

    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int removePos = intent.getIntExtra("removePos",0);
            tasks.remove(removePos);
            Log.e(TAG,"移除"+removePos+"位元素");
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class GetSelfReleaseTasksTask extends AsyncTask<String,Integer,Integer>{
        @SneakyThrows
        @Override
        protected Integer doInBackground(String... strings) {
            Response response = new TaskRequest().getSelfReleaseTasks(strings[0],pageNum);
            if(response == null){
                return -1;
            }
            Gson gson = StaticData.getGson();
            String body = response.body().string();
            Log.e(TAG,"GetRecommendTasksTask:"+body);
            Type type = new TypeToken<List<TaskEntity>>(){}.getType();
            List<TaskEntity> mid = gson.fromJson(body,type);
            tasks.addAll(mid);
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
                    adapter.updateData(tasks);
                    refreshLayout.setRefreshing(false);
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }
}