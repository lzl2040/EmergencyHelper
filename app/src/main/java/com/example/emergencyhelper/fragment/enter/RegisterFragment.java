package com.example.emergencyhelper.fragment.enter;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.enter.EnterActivity;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.bean.User;
import com.example.emergencyhelper.requests.UserRequest;
import com.example.emergencyhelper.util.CheckUtil;
import com.example.emergencyhelper.util.ViewUtil;

import lombok.SneakyThrows;
import okhttp3.Response;

public class RegisterFragment extends BaseFragment {
    private EditText getTelEdt;
    private EditText pwdEdt;
    private EditText checkPwdEdt;
    private Button registerBtn;
    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(View v) {
        //super.initView(v);
        getTelEdt = v.findViewById(R.id.get_tel);
        pwdEdt = v.findViewById(R.id.pwd);
        checkPwdEdt = v.findViewById(R.id.check_pwd);
        registerBtn = v.findViewById(R.id.register);
    }

    @Override
    public void setListener() {
        //super.setListener();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = getTelEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                String rpwd = checkPwdEdt.getText().toString();
                if(!CheckUtil.checkPhoneValid(phone)){
                    ViewUtil.showErrorToast("电话号码长度必须11位!",getActivity());
                }else if(!CheckUtil.checkPwdValid(pwd)){
                    ViewUtil.showErrorToast("密码长度必须在8-15位之间!",getActivity());
                }else if(!CheckUtil.checkPwdIsSame(pwd,rpwd)){
                    ViewUtil.showErrorToast("两次密码不一致!",getActivity());
                }else{
                    new RegisterTask().execute(phone,pwd);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initView(view);
        setListener();
        return view;
    }

    @SuppressLint("StaticFieldLeak")
    public class RegisterTask extends AsyncTask<String,Integer,Integer>{
        @SneakyThrows
        @Override
        protected Integer doInBackground(String... strings) {
            Response response = new UserRequest().register(strings[0],strings[1]);
            if(response == null){
                return -1;
            }
            return response.code();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            //super.onPostExecute(integer);
            String msg = null;
            switch (integer){
                case -1:
                    msg = "请检查网络状态后重新尝试！";
                    ViewUtil.showErrorToast(msg,getActivity());
                    break;
                case 200:
                    msg = "注册成功!";
                    checkPwdEdt.setText(R.string.blank_string);
                    pwdEdt.setText(R.string.blank_string);
                    getTelEdt.setText(R.string.blank_string);
                    ViewUtil.showNotice(msg,getActivity());
                    EnterActivity.viewPager.setCurrentItem(1);
                    break;
                case 409:
                    msg = "该电话号码已被注册过!";
                    ViewUtil.showErrorToast(msg,getActivity());
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,getActivity());
                    break;
            }
        }
    }
}