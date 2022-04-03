package com.example.emergencyhelper.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.entity.SortItemEntity;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;


public class HomePageFragment extends BaseFragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private String TAG = "HomePageFragment";
    private RecyclerView recyclerView;
    private List<Task> tasks = new ArrayList<>();
    private EditText searchEdit;
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
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.e(TAG,"setListener...");
        searchEdit.setOnClickListener(this);
        pager.setOnPageChangeListener(this);
    }

    @Override
    public void setAdapter() {
        Log.e(TAG,"setAdapter...");
        //super.setAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        ta = new TaskAdapter(tasks,getActivity());
        recyclerView.setAdapter(ta);
        //设置viewpage适配器
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_page, container, false);
        initView(v);
        setListener();
        addData();
        setAdapter();
        return v;
    }

    public void addData(){

        tasks = StaticData.getCategories().get(4).getTasks();
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
}