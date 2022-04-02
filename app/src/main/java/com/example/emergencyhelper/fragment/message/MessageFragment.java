package com.example.emergencyhelper.fragment.message;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.MessageUserAdapter;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.bean.Communicate;
import com.example.emergencyhelper.entity.MessageUserEntity;
import com.example.emergencyhelper.util.StaticData;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends BaseFragment {
    private String TAG = "MessageFragment";
    private RecyclerView recyclerView;
    private List<Communicate> communicates;
    //private List<MessageUserEntity> messageList=new ArrayList<>();
    public static MessageUserAdapter adapter;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//menu用到
    }

    @Override
    public void initView(View v) {
        //super.initView(v);
        Log.d(TAG,"initView...");
        recyclerView=(RecyclerView) v.findViewById(R.id.message_recycler);
    }

    @Override
    public void setListener() {
        super.setListener();
        Log.d(TAG,"setListener...");
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.d(TAG,"setAdapter...");
        getData();
        Log.e(TAG,"size:"+communicates.size());
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MessageUserAdapter(getActivity(),communicates);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView...");
        View v = inflater.inflate(R.layout.fragment_message, container, false);
        initView(v);
        setAdapter();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume...");
    }

    private void getData(){
        communicates = StaticData.getCommunicates();
    }
}