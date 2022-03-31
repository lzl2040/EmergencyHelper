package com.example.emergencyhelper.activity.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.MainActivity;
import com.example.emergencyhelper.adapter.TaskAdapter;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.entity.TaskEntity;
import com.example.emergencyhelper.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

public class SchoolActivity extends BaseActivity {
    private String TAG = "SchoolActivity";
    private RecyclerView recyclerView;
    private ImageView backImg;
    private Context context;
    private List<TaskEntity> tasks = new ArrayList<>();
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
        data1.setDesc("有会高数的吗，能帮忙补习一下高数吗");
        data1.setName("凌末语");
        data1.setDeadline("2022-04-01 16:30");
        data1.setSite("湘潭大学");
        data1.setReward("100");
        data1.setHeader(R.drawable.a8);
        tasks.add(data1);

        TaskEntity data2 = new TaskEntity();
        data2.setDesc("有多余的c语言书，有需要的吗");
        data2.setName("小豆豆");
        data2.setDeadline("2022-03-31 12:23");
        data2.setSite("湘潭大学");
        data2.setReward("10");
        data2.setHeader(R.drawable.a6);
        tasks.add(data2);

        TaskEntity data3 = new TaskEntity();
        data3.setDesc("能帮忙代取一下琴湖的快递吗");
        data3.setName("青草香氕");
        data3.setDeadline("2021-04-01 18:00");
        data3.setSite("湘潭大学");
        data3.setReward("1");
        data3.setHeader(R.drawable.a12);
        tasks.add(data3);
    }
}