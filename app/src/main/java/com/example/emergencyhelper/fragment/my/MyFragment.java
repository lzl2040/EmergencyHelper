package com.example.emergencyhelper.fragment.my;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.my.DingdanActivity;
import com.example.emergencyhelper.activity.my.PointActivity;
import com.example.emergencyhelper.activity.my.PostActivity;
import com.example.emergencyhelper.base.BaseFragment;

public class MyFragment extends BaseFragment {
    private String TAG = "MyFragment";
    private LinearLayout dingdan;
    private LinearLayout post;
    private LinearLayout integral;
    private TextView usernameTxt;
    private ImageView userHeaderImg;
    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
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
        dingdan = v.findViewById(R.id.dingdan);
        post = v.findViewById(R.id.post);
        integral=v.findViewById(R.id.integral);
        userHeaderImg = v.findViewById(R.id.img_header);
        usernameTxt = v.findViewById(R.id.username);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.d(TAG,"setListener...");
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PostActivity.class);
                getActivity().startActivity(intent);
            }
        });
        dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DingdanActivity.class);
                getActivity().startActivity(intent);
            }
        });
        integral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PointActivity.class);
                getActivity().startActivity(intent);
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
        View v = inflater.inflate(R.layout.fragment_my, container, false);
        initView(v);
        setListener();
        setAdapter();
        return v;
    }
}