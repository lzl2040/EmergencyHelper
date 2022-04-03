package com.example.emergencyhelper.activity.category;

import androidx.appcompat.app.AppCompatActivity;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.base.BaseActivity;

import android.os.Bundle;

public class ExpertActivity extends BaseActivity {
    private String TAG = ExpertActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expert);
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void setListener() {
        super.setListener();
    }

    @Override
    public void setAdapter() {
        super.setAdapter();
    }
}