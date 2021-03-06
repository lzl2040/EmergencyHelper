package com.example.emergencyhelper.fragment.enter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.enter.EnterActivity;
import com.example.emergencyhelper.activity.main.MainActivity;
import com.example.emergencyhelper.base.BaseFragment;
import com.example.emergencyhelper.bean.User;
import com.example.emergencyhelper.requests.UserRequest;
import com.example.emergencyhelper.util.CheckUtil;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;
import com.google.gson.Gson;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import lombok.SneakyThrows;
import okhttp3.Response;

public class LoginFragment extends BaseFragment {
    private String TAG = "LoginFragment";
    private EditText telEdt;
    private EditText pwdEdt;
    private Button loginBtn;
    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView(View v) {
        telEdt = v.findViewById(R.id.get_tel);
        pwdEdt = v.findViewById(R.id.get_password);
        loginBtn = v.findViewById(R.id.login);
        //super.initView(v);
    }

    @Override
    public void setListener() {
        //super.setListener();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = telEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                boolean isCheck = EnterActivity.checkBox.isChecked();
                if(!isCheck){
                    ViewUtil.showErrorToast("?????????????????????!",getActivity());
                } else if(!CheckUtil.checkPhoneValid(phone)){
                    ViewUtil.showErrorToast("????????????????????????11???!",getActivity());
                } else if(!CheckUtil.checkPwdValid(pwd)){
                    ViewUtil.showErrorToast("?????????????????????8-15?????????!",getActivity());
                } else{
                    new LoginTask().execute(phone,pwd);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        initView(v);
        setListener();
        return v;
    }

    @SuppressLint("StaticFieldLeak")
    public class LoginTask extends AsyncTask<String,Integer,Integer>{
        @SneakyThrows
        @Override
        protected Integer doInBackground(String... strings) {
            Response response = new UserRequest().login(strings[0],strings[1]);
            if(response == null){
                return -1;
            }
            Gson gson = StaticData.getGson();
            String body = response.body().string();
            User user = gson.fromJson(body,User.class);
            Log.e(TAG, "doInBackground: get the user" + body);
            if(response.code() == 200){
                StaticData.setUserSP(user);
                StaticData.setCurUser(user);
            }
            return response.code();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            String msg = null;
            switch (integer){
                case -1:
                    msg = "???????????????????????????????????????";
                    ViewUtil.showErrorToast(msg,getActivity());
                    break;
                case 200:
                    ViewUtil.showNotice("????????????",getActivity());
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    break;
                case 401:
                    msg = "?????????????????????";
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