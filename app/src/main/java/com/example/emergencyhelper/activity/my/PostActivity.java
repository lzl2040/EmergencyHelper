package com.example.emergencyhelper.activity.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.PostTaskAdapter;
import com.example.emergencyhelper.bean.Task;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static List<Task> tasks = new ArrayList<>();
    //public static List<TaskEntity> tasks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        PostTaskAdapter pa = new PostTaskAdapter(this,tasks);
        recyclerView.setAdapter(pa);
    }
}