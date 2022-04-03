package com.example.emergencyhelper.activity.my;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.GetTaskAdapter;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.bean.Task;

import java.util.ArrayList;
import java.util.List;

public class DingdanActivity extends BaseActivity {
    private String TAG = "DingdanActivity";
    public static RecyclerView recyclerView;
    public static Context context;
    //public static List<TaskEntity> tasks = new ArrayList<>();
    public static List<Task> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public void initView() {
        //super.initView();
        Log.d(TAG,"initView...");
        context = this;
        recyclerView = findViewById(R.id.recyclerview);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.d(TAG,"setListener...");
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.d(TAG,"setAdapter...");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        GetTaskAdapter adapter = new GetTaskAdapter(context,tasks);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}