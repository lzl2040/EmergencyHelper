package com.example.emergencyhelper.activity.category;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.main.MainActivity;
import com.example.emergencyhelper.adapter.TaskAdapter;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.bean.TaskEntity;
import com.example.emergencyhelper.requests.TaskRequest;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import lombok.SneakyThrows;
import okhttp3.Response;

public class SchoolActivity extends BaseActivity {
    private String TAG = "SchoolActivity";
    private RecyclerView recyclerView;
    private ImageView backImg;
    private Context context;
    private SmartRefreshLayout smartRefreshLayout;
    private List<TaskEntity> tasks = new ArrayList<>();
    private int categoryId = 4,pageNum = 1;
    private int refreshFlag = 1;
    private boolean isLoadMore = true;
    private TaskAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public void initView() {
        //super.initView();
        Log.e(TAG,"initView...");
        context = this;
        recyclerView = findViewById(R.id.recyclerview);
        backImg = findViewById(R.id.back);
        smartRefreshLayout = findViewById(R.id.refreshLayout);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener...");
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context, MainActivity.class);
                finish();
            }
        });

        smartRefreshLayout.setEnableLoadMore(isLoadMore);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshFlag = 0;
                new GetTasksByCategoryTask().execute(categoryId,1);
                pageNum = 1;
            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshFlag = 1;
                isLoadMore = false;
                pageNum = pageNum + 1;
                new GetTasksByCategoryTask().execute(categoryId,pageNum);
            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.e(TAG,"setAdapter...");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<TaskEntity> mid = new ArrayList<>();
        adapter = new TaskAdapter(mid,context);
        recyclerView.setAdapter(adapter);
        addData();
    }

    public void addData(){
        new GetTasksByCategoryTask().execute(categoryId,pageNum);
    }

    @SuppressLint("StaticFieldLeak")
    public class GetTasksByCategoryTask extends AsyncTask<Integer,Integer,Integer> {
        @SneakyThrows
        @Override
        protected Integer doInBackground(Integer... integers) {
            Response response = new TaskRequest().getTaskByCategory(integers[0], StaticData.getCurUser().getPhone(),integers[1]);
            if(response == null){
                return -1;
            }
            Gson gson = StaticData.getGson();
            String body = response.body().string();
            Log.e(TAG,"GetTasksByCategoryTask:"+body);
            Type type = new TypeToken<List<TaskEntity>>(){}.getType();
            List<TaskEntity> mid = gson.fromJson(body,type);
            if(refreshFlag == 0){
                //刷新
                tasks.clear();
                tasks.addAll(mid);
            }else{
                //加载更多
                tasks.addAll(mid);
            }
            return response.code();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            String msg = null;
            switch (integer){
                case -1:
                    msg = "请检查网络状态后重新尝试！";
                    ViewUtil.showErrorToast(msg, context);
                    break;
                case 200:
                    adapter.updateData(tasks);
                    if(refreshFlag == 1){
                        smartRefreshLayout.finishLoadMore();
                    }else{
                        smartRefreshLayout.finishRefresh();
                    }
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }

}