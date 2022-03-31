package com.example.emergencyhelper.fragment.near;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.HomeSlidAdapter;
import com.example.emergencyhelper.base.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class NearFragment extends BaseFragment {
    private String TAG = "NearFragment";
    private SlidingTabLayout slide;
    private ViewPager viewPager;
    private String titles[] = {"话题","随手帮"};
    private ArrayList<Fragment> fragments = new ArrayList<>();
    public NearFragment() {
        // Required empty public constructor
    }

    public static NearFragment newInstance() {
        NearFragment fragment = new NearFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(View v) {
        //super.initView(v);
        Log.d(TAG,"initView...");
        slide = v.findViewById(R.id.slide);
        viewPager = v.findViewById(R.id.viewpager);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.d(TAG,"setListener...");
        slide.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.d(TAG,"setAdapter...");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_near, container, false);
        initView(v);
        setFragments();
        setListener();
        return v;
    }

    public void setFragments(){
        fragments.add(TopicFragment.newInstance());
        fragments.add(RandomHelperFragment.newInstance());
        viewPager.setAdapter(new HomeSlidAdapter(getChildFragmentManager(),titles,fragments));
        slide.setViewPager(viewPager);
    }
}