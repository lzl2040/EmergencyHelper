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

public class FamilyActivity extends BaseActivity {
    private String TAG = "FamilyActivity";
    private RecyclerView recyclerView;
    private ImageView backImg;
    private Context context;
    private List<TaskEntity> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
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
        data1.setDesc("需要找一个能辅导孩子数学的老师，在暑假期间带着孩子一起学习");
        data1.setName("烟寒若雨");
        data1.setDeadline("2021-4-10");
        data1.setSite("电子厂旁");
        data1.setReward("100");
        data1.setHeader(R.drawable.a10);
        tasks.add(data1);

        TaskEntity data2 = new TaskEntity();
        data2.setDesc("找一位能够辅导孩子在化学的，马上就要学习化学了，希望能够带着孩子入门化学的学习，最好事化学专业的大学生或者研究生");
        data2.setName("镜湖月");
        data2.setDeadline("2021-4-8");
        data2.setSite("泰沙小区");
        data2.setReward("100");
        data2.setHeader(R.drawable.a2);
        tasks.add(data2);

        TaskEntity data3 = new TaskEntity();
        data3.setDesc("想找一位能带领孩子学习篮球的老师，希望周末有时间教导孩子的篮球");
        data3.setName("青草香氕");
        data3.setDeadline("2021-4-1");
        data3.setSite("大兴小区");
        data3.setReward("120");
        data3.setHeader(R.drawable.a13);
        tasks.add(data3);
    }
}