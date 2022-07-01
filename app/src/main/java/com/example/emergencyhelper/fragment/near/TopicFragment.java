package com.example.emergencyhelper.fragment.near;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.TopicAdapter;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.bean.TaskEntity;
import com.example.emergencyhelper.bean.TopicEntity;
import com.example.emergencyhelper.requests.TopicRequest;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import okhttp3.Response;

public class TopicFragment extends BaseFragment {
    private String TAG = "TopicFragment";
    private RecyclerView recyclerView;
    private List<TopicEntity> topics = new ArrayList<>();
    private SmartRefreshLayout refreshLayout;
    private boolean isLoadMore = true;
    private int pageNum = 1;
    private TopicAdapter adapter;
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
    public void initView(View v) {
        //super.initView(v);
        Log.d(TAG,"initView...");
        recyclerView = v.findViewById(R.id.recyclerview);
        refreshLayout = v.findViewById(R.id.refreshLayout);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.d(TAG,"setListener...");
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(isLoadMore);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadMore = false;
                pageNum = pageNum + 1;
            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.d(TAG,"setAdapter...");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new TopicAdapter(topics,getActivity());
        recyclerView.setAdapter(adapter);
        addData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_topic, container, false);
        initView(v);
        setListener();
        setAdapter();
        return v;
    }

    public void addData(){
        new GetTopicsTask().execute(pageNum);
    }

    @SuppressLint("StaticFieldLeak")
    public class GetTopicsTask extends AsyncTask<Integer,Integer,Integer>{
        @SneakyThrows
        @Override
        protected Integer doInBackground(Integer... integers) {
            Response response = new TopicRequest().getTopics(integers[0]);
            if(response == null){
                return -1;
            }
            Gson gson = StaticData.getGson();
            String body = response.body().string();
            Log.e(TAG,"GetTopicsTask:"+body);
            Type type = new TypeToken<List<TopicEntity>>(){}.getType();
            List<TopicEntity> mid = gson.fromJson(body,type);
            topics.addAll(mid);
            return response.code();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            String msg = "";
            switch (integer){
                case -1:
                    msg = "请检查网络状态后重新尝试！";
                    ViewUtil.showErrorToast(msg, getActivity());
                    break;
                case 200:
                    adapter.notifyDataSetChanged();
                    refreshLayout.finishLoadMore();
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,getActivity());
                    break;
            }
        }
    }
}