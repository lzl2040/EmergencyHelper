package com.example.emergencyhelper.activity.topic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.adapter.CommentAdapter;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.entity.CommentEntity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends BaseActivity {
    private String TAG = "CommentActivity";
    private Button send;
    private EditText input_box;
    private List<CommentEntity> cs = new ArrayList<>();
    private RecyclerView recyclerView;
    public Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                recyclerView.setAdapter(new CommentAdapter(context,cs));
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
        setListener();
        setAdapter();
        addData();
    }

    public void addData(){
        CommentEntity ce = new CommentEntity();
        ce.setType(1);
        ce.setPage_post_time(getCurrentTime());
        ce.setPoster_header(R.drawable.a3);
        ce.setPoster_name("醉挽清风");
        cs.add(ce);
    }

    @Override
    public void initView() {
        super.initView();
        send = findViewById(R.id.send);
        input_box = findViewById(R.id.make_comment);
        recyclerView = findViewById(R.id.recyclerview);
    }

    @Override
    public void setListener() {
        Log.d(TAG,"setListener...");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = input_box.getText().toString();
                CommentEntity ce = new CommentEntity();
                ce.setContent(content);
                ce.setImg_id(R.drawable.a5);
                ce.setName("楚风");
                ce.setTime(getCurrentTime());
                ce.setType(2);
                cs.add(ce);
                handler.sendEmptyMessage(1);
                input_box.setText("");
            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        Log.d(TAG,"setAdapter...");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new CommentAdapter(this,cs));
    }

    public String getCurrentTime(){
        SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-YY hh:mm");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time = sf.format(timestamp);//时间
        return time;
    }
}