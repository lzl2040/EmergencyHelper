package com.example.emergencyhelper.fragment.home;

import android.content.Context;
import android.content.Intent;
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
import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.bean.UserAndTaskCategory;
import com.example.emergencyhelper.entity.SortItemEntity;
import com.example.emergencyhelper.ui.DragFloatActionButton;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class HomePageFragment extends BaseFragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private String TAG = "HomePageFragment";
    private RecyclerView recyclerView;
    private List<Task> tasks = new ArrayList<>();
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
    private List<Task> oldTasks = StaticData.getCategories().get(4).getTasks();

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
                //下拉刷新数据
                getRefreshData();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        ta.notifyDataSetChanged();
                    }
                },2000);

            }
        });

        dragBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dragBtn.setImageResource(R.mipmap.stop_record);
            }
        });
    }

    private boolean checkHasDifferent(List<UserAndTaskCategory> taskCategories){
        boolean flag = false;
        for(int i = 1;i < taskCategories.size();i++){
            if(taskCategories.get(i) != taskCategories.get(i-1)){
                return true;
            }
        }
        return flag;
    }

    /**
     * 得到下拉刷新的数据
     */
    public void getRefreshData(){
        List<UserAndTaskCategory> categories = StaticData.getUserAndTaskCategories();
        Collections.sort(categories);
        StaticData.setUserAndTaskCategories(categories);
        if(checkHasDifferent(categories)){
            //先选取老人,暂取定值
            int oldLen = oldTasks.size();
            int rand1 = (new Random().nextInt(oldLen));
            Task task = oldTasks.get(rand1);
            //然后取排名前一(2个)和前二(1个)的
            int firstIndex = categories.get(0).getCategory().getCategoryId() - 1;
            int secondIndex = categories.get(1).getCategory().getCategoryId() - 1;
            List<Task> firstTasks = StaticData.getCategories().get(firstIndex).getTasks();
            List<Task> secondTasks = StaticData.getCategories().get(secondIndex).getTasks();
            List<Task> randomFirst = getRandomTasks(2,firstTasks);
            List<Task> randomSecond = getRandomTasks(1,secondTasks);
            tasks.removeAll(tasks);
            tasks.add(task);
            tasks.addAll(randomFirst);
            tasks.addAll(randomSecond);
        }else{
            //先选取老人,暂取定值
            int oldLen = oldTasks.size();
            int rand1 = (new Random().nextInt(oldLen));
            Task task = oldTasks.get(rand1);
            //然后随机取
            int len = StaticData.getCategories().size();
            int rand = (new Random().nextInt(len));
            int rand2 = -1;
            while(rand2 != rand && rand2 != -1){
                rand2 = (new Random().nextInt(len));
            }
            List<Task> randList1 = StaticData.getCategories().get(rand).getTasks();
            List<Task> randList2 = StaticData.getCategories().get(rand2).getTasks();
            List<Task> resultTask = getRandomTasks(1,randList1);
            List<Task> resultTask2 = getRandomTasks(1,randList2);
            tasks.removeAll(tasks);
            tasks.add(task);
            tasks.addAll(resultTask);
            tasks.addAll(resultTask2);
        }

    }

    /**
     * 随机选取任务,不能重复
     * @param num 选取的数目
     * @param taskList 选取的源列表
     * @return
     */
    public List<Task> getRandomTasks(int num,List<Task> taskList){
        int randArr[] = new int[num];
        Arrays.fill(randArr,-1);
        int len = taskList.size();
        int counts = 0;
        List<Task> targetTasks = new ArrayList<>();
        while (counts < num){
            int rand = (new Random().nextInt(len));
            boolean hasExist = false;
            boolean isNear = false;
            for(int i = 0;i < randArr.length;i++){
                if(randArr[i] == rand){
                    hasExist = true;
                    break;
                }
            }

            String site = taskList.get(rand).getSite();
            if(site.contains("湘潭大学") || site.contains("湘大")){
                isNear = true;
            }
            if(!hasExist && isNear){
                randArr[counts] = rand;
                targetTasks.add(taskList.get(rand));
                counts++;
            }
        }
        return targetTasks;
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
        for(int i = 0;i < 5;i+=2){
            tasks.add(StaticData.getTaskList().get(i));
        }

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