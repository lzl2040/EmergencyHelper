package com.example.emergencyhelper.activity.enter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.CheckBox;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.activity.main.MainActivity;
import com.example.emergencyhelper.adapter.HomeSlidAdapter;
import com.example.emergencyhelper.application.HelperApplication;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.bean.User;
import com.example.emergencyhelper.fragment.enter.LoginFragment;
import com.example.emergencyhelper.fragment.enter.RegisterFragment;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

public class EnterActivity extends BaseActivity {
    public static SlidingTabLayout slidingTabLayout;
    public static ViewPager viewPager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String titles[] = {"注册","登录"};
    private Context context;
    private final String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.INTERNET};//需要获取的权限
    private List<String> mPermissons = new ArrayList<>();
    private boolean isPermissionRequested;
    private boolean hasPermissionDismiss = false;
    private static int REQUEST_CODE = 20;
    private MaterialDialog tipDialog;
    public static CheckBox checkBox;
    private String packageName = "com.example.emergencyhelper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        context = this;

        //判断是否登录过，登录过则跳过登录
        if(!HelperApplication.getUserSP().getString("phone","12").equals("12")) {
            ViewUtil.jumpTo(context, MainActivity.class);
            User user = new User(HelperApplication.getUserSP().getString("phone","12"),HelperApplication.getUserSP().getString("pwd","12"));
            StaticData.setCurUser(user);
            finish();
        }
        //动态申请权限
        checkPermissions();
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public void initView() {
        //super.initView()
        slidingTabLayout = findViewById(R.id.slides);
        checkBox = findViewById(R.id.checkbox);
        viewPager = findViewById(R.id.viewpager);
        fragments.add(RegisterFragment.newInstance());
        fragments.add(LoginFragment.newInstance());
    }

    @Override
    public void setListener() {
        //super.setListener();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        slidingTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void setAdapter() {
        //super.setAdapter();
        viewPager.setAdapter(new HomeSlidAdapter(getSupportFragmentManager(),titles,fragments));
        viewPager.setOffscreenPageLimit(fragments.size());
        slidingTabLayout.setViewPager(viewPager);
    }

    /**
     * 检查权限
     */
    private void checkPermissions(){
        mPermissons.clear();
        for(int i=0;i<permissions.length;i++){
            if(ContextCompat.checkSelfPermission(EnterActivity.this,permissions[i])!=PackageManager.PERMISSION_GRANTED){
                mPermissons.add(permissions[i]);
            }
        }
        //请求权限
        if(mPermissons.size()>0){
            ActivityCompat.requestPermissions(EnterActivity.this,permissions,REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE){
            for (int grantResult : grantResults) {
                if (grantResult == -1) {
                    hasPermissionDismiss = true;
                    break;
                }
            }
            if (hasPermissionDismiss){
                showPermissionDialog();
            }
        }
    }

    /**
     * 显示申请权限的对话框
     */
    private void showPermissionDialog(){
        if(tipDialog==null){
            tipDialog = new MaterialDialog.Builder(EnterActivity.this)
                    .title("已禁用权限，请手动授予")
                    .positiveText("设置")
                    .cancelable(false)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            cancelPermissionDialog();
                            Uri packgeUri = Uri.parse("package:"+packageName);
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packgeUri);
                            startActivity(intent);
                        }
                    })
                    .negativeText("取消")
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            cancelPermissionDialog();
                        }
                    }).build();
        }
        tipDialog.show();
    }

    private void cancelPermissionDialog() {
        tipDialog.dismiss();
    }

}