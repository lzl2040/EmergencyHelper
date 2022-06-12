package com.example.emergencyhelper.fragment.home;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.TaskAdapter;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.bean.TaskEntity;

import java.util.List;

public class SearchFragment extends BaseFragment {
    private String TAG = "SearchFragment";
    private RecyclerView recyclerView;
    private static List<TaskEntity> tasks;
    //private static List<TaskEntity> tasks;
    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(List<TaskEntity> tasks1) {
        SearchFragment fragment = new SearchFragment();
        tasks = tasks1;
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
        recyclerView = v.findViewById(R.id.recyclerview);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.d(TAG,"setListener...");
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.d(TAG,"setAdapter...");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        TaskAdapter adapter = new TaskAdapter(tasks,getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initView(view);
        setListener();
        setAdapter();
        return view;
    }
}