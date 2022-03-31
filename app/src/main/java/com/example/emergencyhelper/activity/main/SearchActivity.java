package com.example.emergencyhelper.activity.main;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.entity.TaskEntity;
import com.example.emergencyhelper.fragment.home.SearchFragment;
import com.example.emergencyhelper.util.CheckUtil;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {
    private String TAG = "SearchActivity";
    private FrameLayout frameLayout;
    private Button searchBtn;
    private EditText searchEdit;
    private ImageView backImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public void initView() {
        //super.initView();
        Log.e(TAG,"initView...");
        searchBtn = findViewById(R.id.search_btn);
        searchEdit = findViewById(R.id.search_box);
        backImg = findViewById(R.id.back);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener...");
        //返回按钮
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context, MainActivity.class);
                finish();
            }
        });
        //搜索按钮
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyWord = searchEdit.getText().toString();
                if(!CheckUtil.checkStringNull(keyWord)){
                    replaceFragment(keyWord);
                }else{
                    ViewUtil.showErrorToast(getString(R.string.search_task_tip),context);
                }
            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.e(TAG,"setAdapter...");
    }

    /**
     * 切换fragment
     */
    public void replaceFragment(String keyWord){
        FragmentManager fm = getSupportFragmentManager();
        if(fm == null){
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout, SearchFragment.newInstance(findFitTask(keyWord)));
        ft.commit();
    }

    public List<TaskEntity> findFitTask(String keyWord){
        List<TaskEntity> result = new ArrayList<>();
        List<TaskEntity> allTasks = StaticData.getTasks();
        for(TaskEntity entity:allTasks){
            String desc = entity.getDesc();
            if(desc.contains(keyWord)){
                result.add(entity);
            }
        }
        return result;
    }
}