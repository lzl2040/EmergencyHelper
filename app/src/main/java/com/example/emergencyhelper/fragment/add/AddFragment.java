package com.example.emergencyhelper.fragment.add;

import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.example.emergencyhelper.activity.category.FamilyActivity;
import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.util.DateUtils;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.my.PostActivity;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.util.StaticData;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.MiniLoadingDialog;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.configure.TimePickerType;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.xuexiang.xui.widget.progress.loading.ARCLoadingView;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddFragment extends BaseFragment implements EventListener {
    private String TAG = "AddFragment";
    private Button submit;
    private MultiLineEditText content;
    private EditText deadline,pay,site;
    private ImageView calendarImg;
    private ImageView locationImg;
    private TimePickerView timePickerViewDialog;
    private ImageView recordImg;
    private EventManager manager;
    private MiniLoadingDialog dialog;
    private Handler handler = new Handler();

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
        manager = EventManagerFactory.create(getActivity(),"asr");
        manager.registerListener(this);
        dialog = WidgetUtils.getMiniLoadingDialog(getActivity());
        dialog.updateMessage("正在进行自动分类...");
        submit = v.findViewById(R.id.commit);
        pay = v.findViewById(R.id.pay);
        content =v.findViewById(R.id.content);
        site = v.findViewById(R.id.site);
        deadline = v.findViewById(R.id.deadline);
        calendarImg = v.findViewById(R.id.calendar);
        locationImg = v.findViewById(R.id.location);
        recordImg = v.findViewById(R.id.recordImg);
        recordImg.setTag(R.mipmap.record);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.d(TAG,"setListener...");
        //绑定提交按钮的监听事件
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = new Task();
                String pays = pay.getText().toString();
                String contents = content.getContentText().toString();
                String sites = site.getText().toString();
                String deadlines = deadline.getText().toString();
                if(pays.length()!=0&&contents.length()!=0&&sites.length()!=0){
                    task.setContent(contents);
                    task.setDeadline(deadlines);
                    task.setSite(sites);
                    task.setReward(Integer.valueOf(pays));
                    task.setPostUser(StaticData.getUserList().get(0));
                    PostActivity.tasks.add(task);
                    //暂时固定为家庭服务
                    FamilyActivity.tasks.add(task);
                    dialog.show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            content.setContentText("");
                            site.setText("");
                            deadline.setText("");
                            pay.setText("");
                            dialog.dismiss();
                        }
                    },2000);
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
                    //获得当前时间的时间字符串
                    String timeStr = DateUtils.timeNum2String(System.currentTimeMillis());
                    //Log.d(TAG,"now time:"+timeStr);
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

        locationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                site.setText("琴湖18栋");
            }
        });

        recordImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.valueOf(recordImg.getTag().toString());
                if(value == R.mipmap.record){
                    recordImg.setImageResource(R.mipmap.stop);
                    recordImg.setTag(R.mipmap.stop);
                    manager.send(SpeechConstant.ASR_START,null,null,0,0);
                }else{
                    recordImg.setImageResource(R.mipmap.record);
                    recordImg.setTag(R.mipmap.record);
                    manager.send(SpeechConstant.ASR_STOP,null,null,0,0);
                }
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        if (name.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
            // 识别相关的结果都在这里
            if (params == null || params.isEmpty()) {
                return;
            }
            if (params.contains("\"final_result\"")) {
                // 一句话的最终识别结果
                String regrex = "\\[(.*?),";  //使用正则表达式抽取我们需要的内容
                Pattern pattern = Pattern.compile(regrex);
                Matcher matcher = pattern.matcher(params);
                if (matcher.find()) {
                    int a  = matcher.group(0).indexOf("[");
                    int b  = matcher.group(0).indexOf(",");
                    content.setContentText(matcher.group(0).substring(a+2,b-3));
                }
            }
        }
    }
}