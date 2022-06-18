package com.example.emergencyhelper.fragment.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.category.DisableActivity;
import com.example.emergencyhelper.activity.category.ExpertActivity;
import com.example.emergencyhelper.activity.category.SocialActivity;
import com.example.emergencyhelper.activity.category.CoporateActivity;
import com.example.emergencyhelper.activity.category.EmergencyActivity;
import com.example.emergencyhelper.activity.category.FamilyActivity;
import com.example.emergencyhelper.activity.category.OldActivity;
import com.example.emergencyhelper.activity.category.SchoolActivity;
import com.example.emergencyhelper.activity.category.ChildrenActivity;
import com.example.emergencyhelper.activity.main.SearchActivity;
import com.example.emergencyhelper.adapter.GridViewAdapter;
import com.example.emergencyhelper.adapter.TaskAdapter;
import com.example.emergencyhelper.adapter.ViewPageAdapter;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.bean.TaskEntity;
import com.example.emergencyhelper.bean.UserAndTaskCategory;
import com.example.emergencyhelper.entity.SortItemEntity;
import com.example.emergencyhelper.requests.TaskRequest;
import com.example.emergencyhelper.ui.DragFloatActionButton;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import okhttp3.Response;


public class HomePageFragment extends BaseFragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private String TAG = "HomePageFragment";
    private RecyclerView recyclerView;
    private List<TaskEntity> tasks = new ArrayList<>();
    private EditText searchEdit;
    private SwipeRefreshLayout refreshLayout;
    public static DragFloatActionButton dragBtn;
    private TaskAdapter ta;
    private Context context;
    private ViewGroup pointsLayout;
    //小圆点图片集合
    private ImageView[] pointsIcon;
    private ViewPager pager;
    private int totalPage;
    private int pageMaxSize = 8;
    private int currentPage;
    private List<View> views = new ArrayList<>();
    private List<SortItemEntity> data = new ArrayList<>();
    private List<Class> cls = new ArrayList<>();
    private Handler handler = new Handler();
    public HomePageFragment() {
        // Required empty public constructor
    }

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(View v) {
        //super.initView(v);
        Log.e(TAG,"initView...");
        context = getActivity();
        recyclerView = v.findViewById(R.id.recyclerview);
        searchEdit = v.findViewById(R.id.search_box);
        pager = v.findViewById(R.id.viewPager);
        pointsLayout = v.findViewById(R.id.points);
        refreshLayout = v.findViewById(R.id.freshLayout);
        dragBtn = v.findViewById(R.id.drag_btn);
        //设置刷新的颜色变化
        refreshLayout.setColorSchemeResources(R.color.gold,R.color.orange,R.color.bisque);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener...");
        searchEdit.setOnClickListener(this);
        pager.setOnPageChangeListener(this);
        //下拉刷新的监视器
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetRecommendTasksTask().execute(StaticData.getCurUser().getPhone());
            }
        });

        dragBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dragBtn.setImageResource(R.mipmap.stop_record);
            }
        });
    }

    @Override
    public void setAdapter() {
        Log.e(TAG,"setAdapter...");
        //super.setAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<TaskEntity> mid = new ArrayList<>();
        ta = new TaskAdapter(mid,getActivity());
        recyclerView.setAdapter(ta);
        pager.setAdapter(new ViewPageAdapter(views));
        addPoint();
    }

    /**
     * 添加小圆点
     */
    public void addPoint(){
        pointsIcon = new ImageView[totalPage];
        for(int i = 0;i < totalPage;i++){
            ImageView imageView = new ImageView(getActivity());
            //设置宽高
            imageView.setLayoutParams(new ViewGroup.LayoutParams(27,27));
            if(i == 0){
                imageView.setImageResource(R.mipmap.select);
            }else{
                imageView.setImageResource(R.mipmap.unselect);
            }
            pointsIcon[i] = imageView;
            //设置外边距
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(27,27));
            layoutParams.leftMargin = 20;
            layoutParams.rightMargin = 20;
            pointsLayout.addView(imageView,layoutParams);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_page, container, false);
        initView(v);
        setListener();
        addData();
        setAdapter();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume...");
        List<TaskEntity> mid = new ArrayList<>();
        ta = new TaskAdapter(mid,getActivity());
        recyclerView.setAdapter(ta);
        new GetRecommendTasksTask().execute(StaticData.getCurUser().getPhone());
    }

    public void addData(){
        new GetRecommendTasksTask().execute(StaticData.getCurUser().getPhone());
        //首页分类栏
        for(int i = 0;i < StaticData.sortItemEntities.length;i++){
            data.add(StaticData.sortItemEntities[i]);
        }
        //计算页数
        totalPage = (int)Math.ceil((1.0 * data.size()) / pageMaxSize);
        //将gridview添加到view列表中
        for(int i = 0;i < totalPage;i++){
            GridView gridView = (GridView) LayoutInflater.from(getActivity()).inflate(R.layout.item_gridview,pager,false);
            gridView.setAdapter(new GridViewAdapter(data,getActivity(),i,pageMaxSize,cls));
            views.add(gridView);
        }
        //添加class,便于跳转
        cls.add(FamilyActivity.class);
        cls.add(SocialActivity.class);
        cls.add(EmergencyActivity.class);
        cls.add(SchoolActivity.class);
        cls.add(OldActivity.class);
        cls.add(ChildrenActivity.class);
        cls.add(CoporateActivity.class);
        cls.add(DisableActivity.class);
        cls.add(ExpertActivity.class);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position);
        currentPage = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 改变点点点的切换效果
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < pointsIcon.length; i++) {
            if (i == selectItems) {
                pointsIcon[i].setImageResource(R.mipmap.select);
            } else {
                pointsIcon[i].setImageResource(R.mipmap.unselect);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_box:
                ViewUtil.jumpTo(context, SearchActivity.class);
                break;
            default:
                break;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class GetRecommendTasksTask extends AsyncTask<String,Integer,Integer>{
        @SneakyThrows
        @Override
        protected Integer doInBackground(String... strings) {
            Response response = new TaskRequest().getTasksByRecommend(strings[0]);
            if(response == null){
                return -1;
            }
            Gson gson = StaticData.getGson();
            String body = response.body().string();
            Log.e(TAG,"GetRecommendTasksTask:"+body);
            Type type = new TypeToken<List<TaskEntity>>(){}.getType();
            List<TaskEntity> mid = gson.fromJson(body,type);
            tasks.clear();
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
                    ViewUtil.showErrorToast(msg, getActivity());
                    break;
                case 200:
                    ta.updateData(tasks);
                    refreshLayout.setRefreshing(false);
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }
}