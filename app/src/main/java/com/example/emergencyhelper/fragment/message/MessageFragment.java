package com.example.emergencyhelper.fragment.message;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.MessageUserAdapter;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.entity.MessageUserEntity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends BaseFragment {
    private String TAG = "MessageFragment";
    private RecyclerView recyclerView;
    private List<MessageUserEntity> messageList=new ArrayList<>();
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
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MessageUserAdapter(getActivity(),messageList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_message, container, false);
        initMessage();
        initView(v);
        setAdapter();
        return v;
    }

    private void initMessage(){
        MessageUserEntity data1=new MessageUserEntity(R.drawable.a10,"旧梦伤","请问你那个工作还在吗","19:40");
        messageList.add(data1);
//        MessageUserEntity data2=new MessageUserEntity(R.drawable.a2,"海阔天空","能快点来修一下家里的水管不","18:21");
//        messageList.add(data2);
//        MessageUserEntity data3=new MessageUserEntity(R.drawable.a3,"大西瓜","你好呀","16:25");
//        messageList.add(data3);
//        MessageUserEntity data4=new MessageUserEntity(R.drawable.a4,"小卷毛","在吗，在吗有急事","15:10");
//        messageList.add(data4);
    }
}