package com.example.emergencyhelper.fragment.expert;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.ExpertAdapter;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.bean.Expert;
import com.example.emergencyhelper.util.StaticData;

import java.util.List;

public class ExpertFragment extends BaseFragment {
    private String TAG = ExpertFragment.class.getSimpleName();
    private int fragmentIndex = 0;
    private RecyclerView recyclerView;
    private ExpertAdapter adapter;
    private List<Expert> experts;
    public ExpertFragment(int position) {
        // Required empty public constructor
        fragmentIndex = position;
    }

    public static ExpertFragment newInstance(int position) {
        ExpertFragment fragment = new ExpertFragment(position);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(View v) {
        //super.initView(v);
        Log.d(TAG,"initView");
        recyclerView = v.findViewById(R.id.recyclerview);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.d(TAG,"setListener");
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.d(TAG,"setAdapter");
        addData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ExpertAdapter(experts,getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expert, container, false);
        initView(view);
        setListener();
        setAdapter();
        return view;
    }

    public void addData(){
        experts = StaticData.getExpertCategories().get(fragmentIndex).getExperts();
    }
}