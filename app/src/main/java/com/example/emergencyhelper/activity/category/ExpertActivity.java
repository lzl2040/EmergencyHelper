package com.example.emergencyhelper.activity.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.main.MainActivity;
import com.example.emergencyhelper.adapter.ExpertFragmentAdapter;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.constant.ViewConstant;
import com.example.emergencyhelper.fragment.expert.ExpertFragment;
import com.example.emergencyhelper.util.ViewUtil;
import com.google.android.material.tabs.TabLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ExpertActivity extends BaseActivity {
    private String TAG = ExpertActivity.class.getSimpleName();
    private TabLayout tabLayout;
    private ViewPager pager;
    private ImageView back;
    private List<Fragment> fragments = new ArrayList<>();
    private ExpertFragmentAdapter adapter;
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
        //super.initView();
        Log.d(TAG,"initView...");
        tabLayout = findViewById(R.id.tablayout);
        pager = findViewById(R.id.viewpager);
        back = findViewById(R.id.back);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.d(TAG,"setListener...");
        //设置顶部导航栏的监听事件
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
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
        Log.d(TAG,"setAdapter...");
        addFragments();
        adapter = new ExpertFragmentAdapter(getSupportFragmentManager(),ViewConstant.EXPERT_BAR_TITLES,context,fragments);
        pager.setOffscreenPageLimit(ViewConstant.EXPERT_BAR_TITLES.length);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
        tabLayout.setupWithViewPager(pager);
    }

    public void addFragments(){
        for(int i = 0;i < ViewConstant.EXPERT_BAR_TITLES.length;i++){
            fragments.add(ExpertFragment.newInstance(i));
        }
    }
}