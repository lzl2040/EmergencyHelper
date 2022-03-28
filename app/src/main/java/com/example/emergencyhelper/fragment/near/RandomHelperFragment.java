package com.example.emergencyhelper.fragment.near;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.HelperAdaper;
import com.example.emergencyhelper.adapter.TopicAdapter;
import com.example.emergencyhelper.entity.HelperEntity;
import com.example.emergencyhelper.entity.TaskEntity;
import com.example.emergencyhelper.entity.TopicEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomHelperFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<HelperEntity> helper = new ArrayList<>();
    public RandomHelperFragment() {
        // Required empty public constructor
    }

    public static RandomHelperFragment newInstance() {
        RandomHelperFragment fragment = new RandomHelperFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_random_helper, container, false);
        recyclerView = v.findViewById(R.id.recyclerview_helper);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        addDate();
        recyclerView.setAdapter(new HelperAdaper(helper,getActivity()));
        return v;
    }
    public void addDate(){
        HelperEntity data1 = new HelperEntity();
        data1.setDesc("有在食堂的吗，能帮忙带个饭吗");
        data1.setName("白了少年头");
        data1.setTime("2021-5-20");
        data1.setSite("琴湖15栋");
        data1.setReward("100积分");
        data1.setHeader(R.drawable.a16);
        helper.add(data1);

        HelperEntity data2 = new HelperEntity();
        data2.setDesc("有在菜鸟驿站吗，能顺便取个快递吗");
        data2.setName("黎夕旧梦");
        data2.setTime("2021-5-15");
        data2.setSite("琴湖14栋");
        data2.setReward("100积分");
        data2.setHeader(R.drawable.a14);
        helper.add(data2);

        HelperEntity data3 = new HelperEntity();
        data3.setDesc("谁在东门超市，能帮忙买点东西不");
        data3.setName("南筏");
        data3.setTime("2021-5-14");
        data3.setSite("琴湖12栋");
        data3.setReward("50积分");
        data3.setHeader(R.drawable.a13);
        helper.add(data3);
    }
}