package com.example.emergencyhelper.fragment.near;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.TopicAdapter;
import com.example.emergencyhelper.entity.TopicEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopicFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<TopicEntity> topics = new ArrayList<>();
    public TopicFragment() {
        // Required empty public constructor
    }

    public static TopicFragment newInstance() {
        TopicFragment fragment = new TopicFragment();
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
        View v = inflater.inflate(R.layout.fragment_topic, container, false);
        recyclerView = v.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        addDate();
        recyclerView.setAdapter(new TopicAdapter(topics,getActivity()));
        return v;
    }
    public void addDate(){

        TopicEntity data1 = new TopicEntity();
        data1.setComment_num(0);
        data1.setSee_num(200);
        data1.setTitle("电视坏了怎么办?");
        data1.setDetail(" ");
        data1.setName("醉挽清风");
        data1.setImg_id(R.drawable.a3);
        topics.add(data1);

        TopicEntity data2 = new TopicEntity();
        data2.setComment_num(3);
        data2.setSee_num(110);
        data2.setTitle("有什么好的办法去解决门声音大的问题");
        data2.setDetail("可以在门缝里面放点纸，可以减小声音");
        data2.setName("慕绾晴");
        data2.setImg_id(R.drawable.a7);
        topics.add(data2);

        TopicEntity data3 = new TopicEntity();
        data3.setSee_num(800);
        data3.setComment_num(15);
        data3.setTitle("有什么好点的办法防止漏水");
        data3.setDetail("可以在网上买的防漏水的，学着说明书搞一下就");
        data3.setName("浪子回头");
        data3.setImg_id(R.drawable.a5);
        topics.add(data3);
    }
}