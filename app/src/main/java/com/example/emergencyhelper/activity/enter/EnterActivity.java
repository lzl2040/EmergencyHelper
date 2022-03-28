package com.example.emergencyhelper.activity.enter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.HomeSlidAdapter;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.fragment.enter.LoginFragment;
import com.example.emergencyhelper.fragment.enter.RegisterFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class EnterActivity extends BaseActivity {
    public static SlidingTabLayout slidingTabLayout;
    public static ViewPager viewPager;
    ArrayList<Fragment> fragments = new ArrayList<>();
    String titles[] = {"注册","登录"};
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        initView();
    }
    @Override
    public void initView() {
        super.initView();
        context = this;
        slidingTabLayout = findViewById(R.id.slides);
        viewPager = findViewById(R.id.viewpager);
        fragments.add(RegisterFragment.newInstance());
        fragments.add(LoginFragment.newInstance());
        viewPager.setAdapter(new HomeSlidAdapter(getSupportFragmentManager(),titles,fragments));
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        slidingTabLayout.setViewPager(viewPager);
    }

}