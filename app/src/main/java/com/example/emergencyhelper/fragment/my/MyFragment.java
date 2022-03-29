package com.example.emergencyhelper.fragment.my;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.my.DingdanActivity;
import com.example.emergencyhelper.activity.my.PointActivity;
import com.example.emergencyhelper.activity.my.PostActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {
    LinearLayout dingdan;
    LinearLayout post;
    LinearLayout integral;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my, container, false);
        dingdan = v.findViewById(R.id.dingdan);
        post = v.findViewById(R.id.post);
        integral=v.findViewById(R.id.integral);
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
        return v;
    }
}