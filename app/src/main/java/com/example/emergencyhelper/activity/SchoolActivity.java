package com.example.emergencyhelper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.TaskAdapter;
import com.example.emergencyhelper.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;

public class SchoolActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<TaskEntity> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        addData();
        recyclerView.setAdapter(new TaskAdapter(tasks,this));
    }
    public void addData(){
        TaskEntity data1 = new TaskEntity();
        data1.setDesc("有会高数的吗，能帮忙补习一下高数吗");
        data1.setName("凌末语");
        data1.setDeadline("2021-7-10");
        data1.setSite("湘潭大学");
        data1.setReward("100");
        data1.setHeader(R.drawable.a8);
        tasks.add(data1);

        TaskEntity data2 = new TaskEntity();
        data2.setDesc("有多余的c语言书，有需要的吗");
        data2.setName("小豆豆");
        data2.setDeadline("2021-7-8");
        data2.setSite("湘潭大学");
        data2.setReward("10");
        data2.setHeader(R.drawable.a6);
        tasks.add(data2);

        TaskEntity data3 = new TaskEntity();
        data3.setDesc("能帮忙代取一下琴湖的快递吗");
        data3.setName("青草香氕");
        data3.setDeadline("2021-7-1");
        data3.setSite("湘潭大学");
        data3.setReward("1");
        data3.setHeader(R.drawable.a12);
        tasks.add(data3);
    }
}