package com.example.emergencyhelper.activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.fragment.add.AddFragment;
import com.example.emergencyhelper.fragment.HomePageFragment;
import com.example.emergencyhelper.fragment.message.MessageFragment;
import com.example.emergencyhelper.fragment.my.MyFragment;
import com.example.emergencyhelper.fragment.near.NearFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout home;
    private RelativeLayout near;
    private RelativeLayout add;
    private RelativeLayout message;
    private RelativeLayout my;
    private ImageView home_img,near_img,add_img,message_img,my_img;
    private TextView home_text,near_text,add_text,message_text,my_text;
    private List<ImageView>imageViews = new ArrayList<>();
    private List<TextView> textViews = new ArrayList<>();
    private int unselected_icon[] = {R.mipmap.home,R.mipmap.near,R.mipmap.add,R.mipmap.message,R.mipmap.my};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setUnSelected();
        replaceFragment(new HomePageFragment());
        home_text.setTextColor(this.getResources().getColor(R.color.blue));
        home_img.setImageResource(R.mipmap.home_s);
    }

    @Override
    public void initView() {
        super.initView();
        home = findViewById(R.id.home_page);
        home.setOnClickListener(this);
        near = findViewById(R.id.near);
        near.setOnClickListener(this);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
        message = findViewById(R.id.message);
        message.setOnClickListener(this);
        my = findViewById(R.id.my);
        my.setOnClickListener(this);
        home_img = findViewById(R.id.dd);
        near_img = findViewById(R.id.dd2);
        add_img = findViewById(R.id.dd5);
        message_img = findViewById(R.id.dd3);
        my_img = findViewById(R.id.dd4);
        imageViews.add(home_img);
        imageViews.add(near_img);
        imageViews.add(add_img);
        imageViews.add(message_img);
        imageViews.add(my_img);
        home_text = findViewById(R.id.home_text);
        near_text = findViewById(R.id.near_text);
        message_text = findViewById(R.id.message_text);
        my_text = findViewById(R.id.my_text);
        add_text = findViewById(R.id.add_text);
        textViews.add(home_text);
        textViews.add(near_text);
        textViews.add(add_text);
        textViews.add(message_text);
        textViews.add(my_text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_page:
                replaceFragment(new HomePageFragment());
                setUnSelected();
                home_img.setImageResource(R.mipmap.home_s);
                home_text.setTextColor(this.getResources().getColor(R.color.blue));
                break;
            case R.id.near:
                replaceFragment(new NearFragment());
                setUnSelected();
                near_img.setImageResource(R.mipmap.near_s);
                near_text.setTextColor(this.getResources().getColor(R.color.blue));
                break;
            case R.id.add:
                setUnSelected();
                replaceFragment(new AddFragment());
                add_text.setTextColor(this.getResources().getColor(R.color.blue));
                break;
            case R.id.message:
                setUnSelected();
                message_img.setImageResource(R.mipmap.message_s);
                message_text.setTextColor(this.getResources().getColor(R.color.blue));
                replaceFragment(new MessageFragment());
                break;
            case R.id.my:
                setUnSelected();
                my_img.setImageResource(R.mipmap.mys);
                my_text.setTextColor(this.getResources().getColor(R.color.blue));
                replaceFragment(new MyFragment());
                break;
            default:
                break;
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
    }
    public void setUnSelected(){
        for(int i = 0;i<imageViews.size();i++){
            ImageView imageView = imageViews.get(i);
            TextView textView = textViews.get(i);
            imageView.setImageResource(unselected_icon[i]);
            textView.setTextColor(this.getResources().getColor(R.color.black));
        }
    }
}