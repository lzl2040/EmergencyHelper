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

public class ChildrenActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<TaskEntity> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_children);
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
        data1.setName("安之若素");
        data1.setDeadline("2021-5-10");
        data1.setSite("中信小区");
        data1.setReward("300");
        data1.setHeader(R.drawable.a10);
        tasks.add(data1);

        TaskEntity data2 = new TaskEntity();
        data2.setDesc("家里的衣柜比较乱，希望找能收拾屋子的家里的衣柜比较乱，希望找能收拾屋子的");
        data2.setName("奶油桃子");
        data2.setDeadline("2021-5-8");
        data2.setSite("泰沙小区");
        data2.setReward("500");
        data2.setHeader(R.drawable.a2);
        tasks.add(data2);

        TaskEntity data3 = new TaskEntity();
        data3.setDesc("找一个上门打扫卫生的阿姨");
        data3.setName("青草香氕");
        data3.setDeadline("2021-5-1");
        data3.setSite("大兴小区");
        data3.setReward("200");
        data3.setHeader(R.drawable.a13);
        tasks.add(data3);
    }
}