package com.example.emergencyhelper.activity.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.bean.TaskEntity;
import com.example.emergencyhelper.fragment.home.SearchFragment;
import com.example.emergencyhelper.requests.TaskRequest;
import com.example.emergencyhelper.util.CheckUtil;
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

public class SearchActivity extends BaseActivity {
    private String TAG = "SearchActivity";
    private FrameLayout frameLayout;
    private Button searchBtn;
    private EditText searchEdit;
    private ImageView backImg;
    private int pageNum = 1;
    private List<TaskEntity> tasks = new ArrayList<>();
    private SmartRefreshLayout refreshLayout;
    private boolean isLoadMore = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public void initView() {
        //super.initView();
        Log.e(TAG,"initView...");
        searchBtn = findViewById(R.id.search_btn);
        searchEdit = findViewById(R.id.search_box);
        backImg = findViewById(R.id.back);
        refreshLayout = findViewById(R.id.refreshLayout);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener...");
        //返回按钮
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewUtil.jumpTo(context, MainActivity.class);
                finish();
            }
        });

        //搜索按钮
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyWord = searchEdit.getText().toString();
                pageNum = 1;
                tasks.clear();
                if(!CheckUtil.checkStringNull(keyWord)){
                    //replaceFragment(keyWord);
                    new SearchTasksTask().execute(keyWord);
                }else{
                    ViewUtil.showErrorToast(getString(R.string.search_task_tip),context);
                }
            }
        });

        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(isLoadMore);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isLoadMore = false;
                pageNum = pageNum + 1;
                String keyWord = searchEdit.getText().toString();
                new SearchTasksTask().execute(keyWord);
            }
        });

    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.e(TAG,"setAdapter...");
    }

    /**
     * 切换fragment
     */
    private void replaceFragment(){
        FragmentManager fm = getSupportFragmentManager();
        if(fm == null){
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framelayout, SearchFragment.newInstance(tasks));
        ft.commit();
    }

    @SuppressLint("StaticFieldLeak")
    public class SearchTasksTask extends AsyncTask<String,Integer,Integer>{

        @SneakyThrows
        @Override
        protected Integer doInBackground(String... strings) {
            Response response = new TaskRequest().searchTask(strings[0],StaticData.getCurUser().getPhone(),pageNum);
            if(response == null){
                return -1;
            }
            Gson gson = StaticData.getGson();
            String body = response.body().string();
            Log.e(TAG,"SearchTasksTask:"+body);
            Type type = new TypeToken<List<TaskEntity>>(){}.getType();
            List<TaskEntity> mid = gson.fromJson(body,type);
            tasks.addAll(mid);
            return response.code();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            String msg = "";
            switch (integer){
                case -1:
                    msg = "请检查网络状态后重新尝试！";
                    ViewUtil.showErrorToast(msg, context);
                    break;
                case 200:
                    replaceFragment();
                    refreshLayout.finishLoadMore();
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }


}