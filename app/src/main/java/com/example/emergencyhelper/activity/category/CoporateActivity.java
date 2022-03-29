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

public class CoporateActivity extends BaseActivity {
    private String TAG = "CoporateActivity";
    private RecyclerView recyclerView;
    private ImageView backImg;
    private Context context;
    private List<TaskEntity> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coporate);
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
        data1.setDesc("项目缺人，想找一个一起一起做的");
        data1.setName("墨玲珑");
        data1.setDeadline("2021-7-10");
        data1.setSite("凡汐公司");
        data1.setReward("1000");
        data1.setHeader(R.drawable.a8);
        tasks.add(data1);

        TaskEntity data2 = new TaskEntity();
        data2.setDesc("本人最近事务繁多，需要找一位帮忙完成项目的开发，只需要1-2天时间即可");
        data2.setName("十梦九他");
        data2.setDeadline("2021-7-8");
        data2.setSite("欣鑫公司");
        data2.setReward("1100");
        data2.setHeader(R.drawable.a15);
        tasks.add(data2);
    }
}