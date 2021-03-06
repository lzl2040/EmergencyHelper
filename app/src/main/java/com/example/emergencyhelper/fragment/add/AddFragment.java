package com.example.emergencyhelper.fragment.add;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.example.emergencyhelper.bean.TaskEntity;
import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.requests.TaskRequest;
import com.example.emergencyhelper.util.DateUtils;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.dialog.MiniLoadingDialog;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.configure.TimePickerType;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.SneakyThrows;
import okhttp3.Response;


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
    private MaterialSpinner categorySpinner;
    private int chooseCategoryId;
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
        dialog.updateMessage("????????????????????????...");
        submit = v.findViewById(R.id.commit);
        pay = v.findViewById(R.id.pay);
        content =v.findViewById(R.id.content);
        site = v.findViewById(R.id.site);
        deadline = v.findViewById(R.id.deadline);
        calendarImg = v.findViewById(R.id.calendar);
        locationImg = v.findViewById(R.id.location);
        recordImg = v.findViewById(R.id.recordImg);
        recordImg.setTag(R.mipmap.record);
        categorySpinner = v.findViewById(R.id.category_choose);
        categorySpinner.setItems(StaticData.categoryNames);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.d(TAG,"setListener...");
        //?????????????????????????????????
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskEntity task = new TaskEntity();
                String pays = pay.getText().toString();
                String contents = content.getContentText().toString();
                String sites = site.getText().toString();
                String deadlines = deadline.getText().toString();
                if(pays.length()!=0&&contents.length()!=0&&sites.length()!=0){
                    //System.out.println("??????????????????:"+StaticData.getCurUser().getPhone());
                    Task task2 = new Task(contents,deadlines,sites,pays,chooseCategoryId,StaticData.getCurUser().getPhone());
                    dialog.show();
                    new PostTaskTask().execute(task2);
                }
            }
        });

        //?????????????????????????????????
        calendarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timePickerViewDialog == null){
                    Log.d(TAG,"calendarImg:click..");
                    Calendar calendar = Calendar.getInstance();
                    //????????????????????????????????????
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
                            .setTitleText("????????????")
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
                site.setText("??????18???");
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

        categorySpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                chooseCategoryId = position + 1;
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
            // ?????????????????????????????????
            if (params == null || params.isEmpty()) {
                return;
            }
            if (params.contains("\"final_result\"")) {
                // ??????????????????????????????
                String regrex = "\\[(.*?),";  //????????????????????????????????????????????????
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

    @SuppressLint("StaticFieldLeak")
    class PostTaskTask extends AsyncTask<Task,Integer,Integer>{
        @SneakyThrows
        @Override
        protected Integer doInBackground(Task... task2s) {
            Response response = new TaskRequest().postTask(task2s[0]);
            //????????????
            if(response == null){
                return -1;
            }
            return response.code();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            String msg = null;
            dialog.dismiss();
            switch (integer){
                case -1:
                    msg = "???????????????????????????????????????";
                    ViewUtil.showErrorToast(msg,getActivity());
                    break;
                case 200:
                    content.setContentText("");
                    site.setText("");
                    deadline.setText("");
                    pay.setText("");
                    ViewUtil.showNotice("????????????",getActivity());
                    break;
                case 409:
                    msg = "???????????????";
                    ViewUtil.showErrorToast(msg,getActivity());
                    break;
                default:
                    msg = "????????????!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,getActivity());
                    break;
            }
        }
    }
}