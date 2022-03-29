package com.example.emergencyhelper.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.category.DisableActivity;
import com.example.emergencyhelper.activity.category.SocialActivity;
import com.example.emergencyhelper.activity.category.CoporateActivity;
import com.example.emergencyhelper.activity.category.EmergencyActivity;
import com.example.emergencyhelper.activity.category.FamilyActivity;
import com.example.emergencyhelper.activity.category.OldActivity;
import com.example.emergencyhelper.activity.category.SchoolActivity;
import com.example.emergencyhelper.activity.category.ChildrenActivity;
import com.example.emergencyhelper.adapter.TaskAdapter;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends BaseFragment implements View.OnClickListener{
    private String TAG = "HomePageFragment";
    private RecyclerView recyclerView;
    private List<TaskEntity>tasks = new ArrayList<>();
    private LinearLayout old,social,emergency,family,corporate,school,children,disable;
    public  TaskAdapter ta;
    public Context context;

    public HomePageFragment() {
        // Required empty public constructor
    }

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(View v) {
        //super.initView(v);
        Log.e(TAG,"initView...");
        context = getActivity();
        recyclerView = v.findViewById(R.id.recyclerview);
        children = v.findViewById(R.id.children);
        old = v.findViewById(R.id.old);
        emergency = v.findViewById(R.id.emergency);
        school = v.findViewById(R.id.school);
        corporate = v.findViewById(R.id.coporate);
        family = v.findViewById(R.id.family);
        social = v.findViewById(R.id.social);
        disable = v.findViewById(R.id.disable);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener...");
        children.setOnClickListener(this);
        old.setOnClickListener(this);
        emergency.setOnClickListener(this);
        school.setOnClickListener(this);
        corporate.setOnClickListener(this);
        family.setOnClickListener(this);
        social.setOnClickListener(this);
        disable.setOnClickListener(this);
    }

    @Override
    public void setAdapter() {
        Log.e(TAG,"setAdapter...");
        //super.setAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        addData();
        ta = new TaskAdapter(tasks,getActivity());
        recyclerView.setAdapter(ta);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_page, container, false);
        initView(v);
        setListener();
        setAdapter();
        return v;
    }

    public void addData(){
        TaskEntity data1 = new TaskEntity();
        data1.setDesc("下水道堵住了，需要找人维修，有修一下的不");
        data1.setDeadline("2021-7-11");
        data1.setSite("中新街道-452");
        data1.setReward("80");
        data1.setName("小西瓜");
        data1.setHeader(R.drawable.a13);
        tasks.add(data1);

        TaskEntity data2 = new TaskEntity();
        data2.setDesc("门锁坏了，回不了家了，有住在附近的吗，有方便上门维修一下的吗");
        data2.setName("张先生");
        data2.setDeadline("2021-7-10");
        data2.setSite("东坡街道-112");
        data2.setReward("40");
        data2.setHeader(R.drawable.a12);
        tasks.add(data2);

        TaskEntity data3 = new TaskEntity();
        data3.setDesc("本小区有不忙的吗，需要外出几天，想找人照顾一下宠物");
        data3.setName("张先生");
        data3.setDeadline("2021-7-9");
        data3.setSite("星海小区");
        data3.setReward("500");
        data3.setHeader(R.drawable.a11);
        tasks.add(data3);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.children:
                Intent intent = new Intent(getActivity(), ChildrenActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.family:
                Intent intent1 = new Intent(getActivity(), FamilyActivity.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.old:
                Intent intent2 = new Intent(getActivity(), OldActivity.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.coporate:
                Intent intent3 = new Intent(getActivity(), CoporateActivity.class);
                getActivity().startActivity(intent3);
                break;
            case R.id.emergency:
                Intent intent4 = new Intent(getActivity(), EmergencyActivity.class);
                getActivity().startActivity(intent4);
                break;
            case R.id.school:
                Intent intent5 = new Intent(getActivity(), SchoolActivity.class);
                getActivity().startActivity(intent5);
                break;
            case R.id.social:
                Intent intent6 = new Intent(getActivity(), SocialActivity.class);
                getActivity().startActivity(intent6);
                break;
            case R.id.disable:
                Intent intent7 = new Intent(getActivity(), DisableActivity.class);
                getActivity().startActivity(intent7);
                break;
            default:
                break;
        }
    }
}