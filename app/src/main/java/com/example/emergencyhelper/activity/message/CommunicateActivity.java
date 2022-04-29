package com.example.emergencyhelper.activity.message;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emergencyhelper.activity.main.MainActivity;
import com.example.emergencyhelper.adapter.MessageAdapter;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.bean.Communicate;
import com.example.emergencyhelper.bean.Message;
import com.example.emergencyhelper.util.StaticData;

import java.util.ArrayList;
import java.util.List;

public class CommunicateActivity extends BaseActivity {
    private Context context;
    private String TAG = "CommunicateActivity";
    private RecyclerView recyclerView;
    private TextView communicateUserTxt;
    private Button sendBtn;
    private EditText contentEdit;
    private ImageView backImg;
    private List<Message> messages = new ArrayList<>();
    private Communicate communicate;
    private MessageAdapter adapter;
    private int communicateIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public void initView() {
        //super.initView();
        Log.d(TAG,"initView...");
        context = this;
        //获取上一个数据
        Intent intent = getIntent();
        communicate = (Communicate) intent.getSerializableExtra("communicate");
        communicateIndex = intent.getIntExtra("index",0);
        String communicateUserName = communicate.getAcceptUser().getName();
        List<Message> msgs = communicate.getMessages();
        messages = msgs;
        msgs = null;

        recyclerView = findViewById(R.id.recyclerview);
        communicateUserTxt = findViewById(R.id.communicate_user);
        sendBtn = findViewById(R.id.send_btn);
        contentEdit = findViewById(R.id.message_edit);
        backImg = findViewById(R.id.back);
        communicateUserTxt.setText(communicateUserName);
    }

    @Override
    public void setListener() {
        //super.setListener();
        Log.d(TAG,"setListener...");
        //发送按钮
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = contentEdit.getText().toString();
                Message message = new Message(content,"2022-04-03 12:30",0, StaticData.getCurUser());
                //修改这个数据源会导致recyclerview发生变化
                communicate.getMessages().add(message);
                adapter.notifyDataSetChanged();
                List<Communicate> communicates = StaticData.getCommunicates();
                communicates.set(communicateIndex,communicate);
                StaticData.setCommunicates(communicates);
                contentEdit.setText(R.string.blank_string);
            }
        });
        //返回按钮
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class cls = StaticData.getJumpClass();
                Intent intent = new Intent(context, cls);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.d(TAG,"setAdapter...");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        addData();
        adapter = new MessageAdapter(this,messages);
        recyclerView.setAdapter(adapter);
    }

    public void addData(){
//        Message message = new Message(R.drawable.a9,"你好呀",0);
//        Message message1 = new Message(R.drawable.a10,"你好",1);
//        Message message2 = new Message(R.drawable.a9,"请问这个任务的具体细节是什么呢?",0);
//        messages.add(message);
//        messages.add(message1);
//        messages.add(message2);
    }
}