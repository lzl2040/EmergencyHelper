package com.example.emergencyhelper.activity.my;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.GetTaskAdapter;
import com.example.emergencyhelper.adapter.TaskAdapter;
import com.example.emergencyhelper.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;

public class DingdanActivity extends AppCompatActivity {
    public static RecyclerView recyclerView;
    public static Context context;
    public static TaskEntity task;
    public static List<TaskEntity> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        context = this;
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("add_task");
//        receiver = new Receiver();
//        registerReceiver(receiver,intentFilter);
        recyclerView = findViewById(R.id.recyclerview);
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