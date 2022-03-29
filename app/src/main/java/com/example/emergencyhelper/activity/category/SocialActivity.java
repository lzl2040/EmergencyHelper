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

public class SocialActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<TaskEntity> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        addData();
        recyclerView.setAdapter(new TaskAdapter(tasks,this));
    }
    public void addData(){
        TaskEntity data1 = new TaskEntity();
        data1.setDesc("项目需要人帮忙辅助做一下，需要精通java");
        data1.setName("安之若素");
        data1.setDeadline("2021-7-30");
        data1.setSite("凡汐科技");
        data1.setReward("1000");
        data1.setHeader(R.drawable.a5);
        tasks.add(data1);

        TaskEntity data2 = new TaskEntity();
        data2.setDesc("在泰山旅游，想找一个熟悉这里环境的当地人");
        data2.setName("清晓花浅笑");
        data2.setDeadline("2021-7-8");
        data2.setSite("泰山");
        data2.setReward("400");
        data2.setHeader(R.drawable.a15);
        tasks.add(data2);

    }
}