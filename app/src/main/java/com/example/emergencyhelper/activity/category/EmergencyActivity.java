package com.example.emergencyhelper.activity.category;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.main.MainActivity;
import com.example.emergencyhelper.adapter.TaskAdapter;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.entity.TaskEntity;
import com.example.emergencyhelper.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

public class EmergencyActivity extends BaseActivity {
    private String TAG = "EmergencyActivity";
    private RecyclerView recyclerView;
    private ImageView backImg;
    private Context context;
    private List<TaskEntity> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
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
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.e(TAG,"setAdapter...");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        addData();
        recyclerView.setAdapter(new TaskAdapter(tasks,this));
    }

    public void addData(){
        TaskEntity data1 = new TaskEntity();
        data1.setDesc("需要外出几天，需要照顾一下宠物");
        data1.setName("垂耳兔");
        data1.setDeadline("2022-04-12 06:00");
        data1.setSite("典鑫小区");
        data1.setReward("200");
        data1.setHeader(R.drawable.a16);
        tasks.add(data1);

        TaskEntity data2 = new TaskEntity();
        data2.setDesc("家里水管破了，急需上门修理水管");
        data2.setName("余默");
        data2.setDeadline("2022-04-03 12:00");
        data2.setSite("惠霄小区");
        data2.setReward("80");
        data2.setHeader(R.drawable.a2);
        tasks.add(data2);

        TaskEntity data3 = new TaskEntity();
        data3.setDesc("厕所堵住了，有疏通厕所的上门疏通一下吗");
        data3.setName("飘逸的云");
        data3.setDeadline("2022-04-01 10:30");
        data3.setSite("大兴小区");
        data3.setReward("100");
        data3.setHeader(R.drawable.a13);
        tasks.add(data3);
    }
}