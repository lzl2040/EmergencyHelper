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
import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

public class EmergencyActivity extends BaseActivity {
    private String TAG = "EmergencyActivity";
    private RecyclerView recyclerView;
    private ImageView backImg;
    private Context context;
    private List<Task> tasks = new ArrayList<>();
    //private List<TaskEntity> tasks = new ArrayList<>();
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
        tasks = StaticData.getCategories().get(2).getTasks();
    }
}