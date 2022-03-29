package com.example.emergencyhelper.activity.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.TaskAdapter;
import com.example.emergencyhelper.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;

public class EmergencyActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<TaskEntity> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        recyclerView = findViewById(R.id.recyclerview);
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
        data1.setDeadline("2021-6-20");
        data1.setSite("典鑫小区");
        data1.setReward("200");
        data1.setHeader(R.drawable.a16);
        tasks.add(data1);

        TaskEntity data2 = new TaskEntity();
        data2.setDesc("家里水管破了，急需上门修理水管");
        data2.setName("余默");
        data2.setDeadline("2021-6-18");
        data2.setSite("惠霄小区");
        data2.setReward("80");
        data2.setHeader(R.drawable.a2);
        tasks.add(data2);

        TaskEntity data3 = new TaskEntity();
        data3.setDesc("厕所堵住了，有疏通厕所的上门疏通一下吗");
        data3.setName("飘逸的云");
        data3.setDeadline("2021-6-1");
        data3.setSite("大兴小区");
        data3.setReward("100");
        data3.setHeader(R.drawable.a13);
        tasks.add(data3);
    }
}