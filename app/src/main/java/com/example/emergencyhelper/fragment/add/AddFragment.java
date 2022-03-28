package com.example.emergencyhelper.fragment.add;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.emergencyhelper.constant.TimeType;
import com.example.emergencyhelper.util.DateUtils;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.PostActivity;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.entity.TaskEntity;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.configure.TimePickerType;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;

import java.util.Calendar;
import java.util.Date;


public class AddFragment extends BaseFragment {
    private String TAG = "AddFragment";
    private Button submit;
    private MultiLineEditText content;
    private EditText deadline,pay,site;
    private ImageView calendarImg;
    private TimePickerView timePickerViewDialog;
    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(View v) {
        //super.initView();
        Log.d(TAG,"initView...");
        submit = v.findViewById(R.id.commit);
        pay = v.findViewById(R.id.pay);
        content =v.findViewById(R.id.content);
        site = v.findViewById(R.id.site);
        deadline = v.findViewById(R.id.deadline);
        calendarImg = v.findViewById(R.id.calendar);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.d(TAG,"setListener...");
        //绑定提交按钮的监听事件
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskEntity taskEntity = new TaskEntity();
                String pays = pay.getText().toString();
                String contents = content.getContentText().toString();
                String sites = site.getText().toString();
                String deadlines = deadline.getText().toString();
                if(pays.length()!=0&&contents.length()!=0&&sites.length()!=0){
                    taskEntity.setDesc(contents);
                    taskEntity.setSite(sites);
                    taskEntity.setReward(Integer.valueOf(pays)+"");
                    taskEntity.setDeadline(deadlines);
                    PostActivity.tasks.add(taskEntity);
                    Toast.makeText(getActivity(),"发布成功",Toast.LENGTH_LONG).show();
                }
            }
        });
        //设置点击日历的监听事件
        calendarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timePickerViewDialog == null){
                    Log.d(TAG,"calendarImg:click..");
                    Calendar calendar = Calendar.getInstance();
                    String timeStr = DateUtils.timeNum2String(System.currentTimeMillis());
                    Log.d(TAG,"now time:"+timeStr);
                    calendar.setTime(DateUtils.string2Date(timeStr, DateUtils.yyyyMMddHHmm.get()));
                    timePickerViewDialog = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelected(Date date, View v) {
                            String dataStr = DateUtils.date2String(date,DateUtils.yyyyMMddHHmm.get());
                            deadline.setText(dataStr);
                        }
                    }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                        @Override
                        public void onTimeSelectChanged(Date date) {

                        }
                    }).setType(TimePickerType.DATE)
                            .setTitleText("时间选择")
                            .isDialog(true)
                            .setOutSideCancelable(false)
                            .setDate(calendar)
                            .build();
                }
                timePickerViewDialog.show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        initView(v);
        setListener();
        return v;
    }
}