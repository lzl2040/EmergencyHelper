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

public class OldActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<TaskEntity> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old);
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        addData();
        recyclerView.setAdapter(new TaskAdapter(tasks,this));
    }
    public void addData(){
        TaskEntity data1 = new TaskEntity();
        data1.setDesc("需要找位人，去看望一下家里的老人");
        data1.setName("冷心为王");
        data1.setDeadline("2021-7-10");
        data1.setSite("兴安小区");
        data1.setReward("300");
        data1.setHeader(R.drawable.a10);
        tasks.add(data1);

        TaskEntity data2 = new TaskEntity();
        data2.setDesc("老人一人在家，家里老人需要去看医院，想找一个能一起去医院看病的");
        data2.setName("慕绾晴");
        data2.setDeadline("2021-7-8");
        data2.setSite("沙尾小区");
        data2.setReward("500");
        data2.setHeader(R.drawable.a2);
        tasks.add(data2);

        TaskEntity data3 = new TaskEntity();
        data3.setDesc("老人家里卫生很乱，需要找人打理一下");
        data3.setName("妄念");
        data3.setDeadline("2021-7-1");
        data3.setSite("大兴小区");
        data3.setReward("200");
        data3.setHeader(R.drawable.a13);
        tasks.add(data3);
    }
}