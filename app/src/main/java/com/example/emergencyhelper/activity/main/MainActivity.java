package com.example.emergencyhelper.activity.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.fragment.add.AddFragment;
import com.example.emergencyhelper.fragment.home.HomePageFragment;
import com.example.emergencyhelper.fragment.message.MessageFragment;
import com.example.emergencyhelper.fragment.my.MyFragment;
import com.example.emergencyhelper.fragment.near.NearFragment;
import com.example.emergencyhelper.util.StaticData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity{
    private String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;
    private ImageView callPoliceImg;
    //fragment管理器
    private FragmentManager fragmentManager;
    //fragment事务
    private FragmentTransaction fragmentTransaction;
    private Fragment preFragment = null;
    //存放fragment的Map
    private Map<Integer,Fragment> fragmentMap;
    //当前fragment对应的int值
    private int fragmentIds[] = new int[]{R.id.home,R.id.near,R.id.add,R.id.message,R.id.my};
    private int curId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initFragment();
        setListener();
    }

    @Override
    public void initView() {
        //super.initView();
        Log.e(TAG,"initView...");
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        callPoliceImg = findViewById(R.id.call_police);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener...");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.e(TAG,"item_id="+item.getItemId());
                StaticData.setBottomPosition(item.getItemId());
                return setFragmentTransaction(fragmentMap.get(item.getItemId()));
            }
        });

        callPoliceImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + "110");
                intent.setData(data);
                startActivity(intent);
            }
        });
    }

    public void initFragment(){
        Log.e(TAG,"initFragment...");
        fragmentManager = getSupportFragmentManager();
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.home, HomePageFragment.newInstance());
        fragmentMap.put(R.id.near, NearFragment.newInstance());
        fragmentMap.put(R.id.add, AddFragment.newInstance());
        fragmentMap.put(R.id.message, MessageFragment.newInstance());
        fragmentMap.put(R.id.my, MyFragment.newInstance());
        curId = StaticData.getBottomPosition();
        if(curId == 0){
            curId = R.id.home;
        }
        Log.e(TAG,"curId:"+curId);
        //设置当前的
        setFragmentTransaction(fragmentMap.get(curId));

        //通过getItem的方式默认将最左便的导航设置成未选择
        //findItem根据id找相应的MenuItem
        bottomNavigationView.getMenu().findItem(curId).setChecked(true);
    }

    /**
     * 设置fragment切换的逻辑
     * @param nowFragment 当前的fragment
     * @return
     */
    private boolean setFragmentTransaction(Fragment nowFragment){
        Log.e(TAG,"setFragmentTransaction...");
        fragmentTransaction = fragmentManager.beginTransaction();
        if(nowFragment == null){
            Log.e(TAG, "setFragmentTransaction: nowFragment is null do nothing!");
            return false;
        }
        if(preFragment != null){
            //隐藏上一步的fragment
            fragmentTransaction.hide(preFragment);
            preFragment = nowFragment;
        }
        if(!nowFragment.isAdded()){
            fragmentManager.beginTransaction().remove(nowFragment).commit();
            fragmentTransaction.add(R.id.frame_container, nowFragment);
        }
        fragmentTransaction.show(nowFragment);
        //更新上一次的fragment
        preFragment=nowFragment;
        fragmentTransaction.commitAllowingStateLoss();
        return true;
    }
}