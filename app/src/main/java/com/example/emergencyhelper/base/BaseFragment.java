package com.example.emergencyhelper.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * author ： yxm521
 * time    ： 2022/3/28
 */
public class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void initView(View v){

    }

    public void setListener(){

    }
}
