package com.example.emergencyhelper.activity.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emergencyhelper.activity.MainActivity;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.entity.TaskEntity;
import com.example.emergencyhelper.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

public class DisableActivity extends BaseActivity {
    private String TAG = "DisableActivity";
    private RecyclerView recyclerView;
    private ImageView backImg;
    private Context context;
    private List<TaskEntity> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disable);
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
        //设置返回按钮
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }
}