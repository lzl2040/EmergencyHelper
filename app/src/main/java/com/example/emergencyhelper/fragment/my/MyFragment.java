package com.example.emergencyhelper.fragment.my;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.enter.EnterActivity;
import com.example.emergencyhelper.activity.my.ReceiveActivity;
import com.example.emergencyhelper.activity.my.PointActivity;
import com.example.emergencyhelper.activity.my.PostActivity;
import com.example.emergencyhelper.activity.my.UpdateInfoActivity;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.bean.User;
import com.example.emergencyhelper.util.CheckUtil;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;

public class MyFragment extends BaseFragment {
    private String TAG = "MyFragment";
    private LinearLayout dingdan;
    private LinearLayout post;
    private LinearLayout integral;
    private TextView usernameTxt;
    private ImageView userHeaderImg;
    private Button editBtn;
    private Button exitBtn;
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
        String imgUrl = StaticData.getCurUser().getImgUrl();
        if(!CheckUtil.checkStringNull(imgUrl)){
            Glide.with(getActivity())
                    .load(imgUrl)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(userHeaderImg);
        }
        usernameTxt = v.findViewById(R.id.username);
        String name = StaticData.getCurUser().getName();
        if(!CheckUtil.checkStringNull(name)){
            usernameTxt.setText(name);
        }
        editBtn = v.findViewById(R.id.edit_btn);
        exitBtn = v.findViewById(R.id.exit_btn);
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
                Intent intent = new Intent(getActivity(), ReceiveActivity.class);
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
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(getActivity(), UpdateInfoActivity.class);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                StaticData.setUserSP(user);
                StaticData.setBottomPosition(0);
                ViewUtil.jumpTo(getActivity(), EnterActivity.class);
                getActivity().finish();
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

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume:"+StaticData.getCurUser().getImgUrl());
        String name = StaticData.getCurUser().getName();
        if(!CheckUtil.checkStringNull(name)){
            usernameTxt.setText(name);
        }
        String imgUrl = StaticData.getCurUser().getImgUrl();
        if(!CheckUtil.checkStringNull(imgUrl)){
            Glide.with(getActivity())
                    .load(imgUrl)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(userHeaderImg);
        }

    }
}