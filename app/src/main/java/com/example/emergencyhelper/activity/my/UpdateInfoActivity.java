package com.example.emergencyhelper.activity.my;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.emergencyhelper.activity.main.MainActivity;
import com.example.emergencyhelper.base.BaseActivity;
import com.example.emergencyhelper.R;
import com.example.emergencyhelper.bean.User;
import com.example.emergencyhelper.requests.UserRequest;
import com.example.emergencyhelper.util.CheckUtil;
import com.example.emergencyhelper.util.FileUtils;
import com.example.emergencyhelper.util.StaticData;
import com.example.emergencyhelper.util.ViewUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

import lombok.SneakyThrows;
import okhttp3.Response;
import rx.functions.Action1;

public class UpdateInfoActivity extends BaseActivity implements View.OnClickListener {
    private String TAG = "UpdateInfoActivity";
    private TextView editPhoneText,editNameText,viewPwdText,pwdText;
    private EditText phoneEdit,nameEdit;
    private RelativeLayout changeHeaderLayout;
    private Context context;
    private ImageView backImg;
    private RadiusImageView headerImg;
    private BottomSheetDialog bottomSheetDialog;
    private TextView photoText,albumText;

    public String path = "";
    public Uri photoUri;
    private File file;
    //拍照
    private static final int TAKE_PICTURE = 0;
    private static final int CHOOSE_ALBUM = 1;
    private static final int RESULT_OK=-1;
    private static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;
    private static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        initView();
        setListener();
        setAdapter();
    }

    @Override
    public void initView() {
        //super.initView();
        context = this;
        editPhoneText = findViewById(R.id.edit_phone);
        phoneEdit = findViewById(R.id.phone);
        phoneEdit.setText(StaticData.getCurUser().getPhone());
        editNameText = findViewById(R.id.edit_name);
        nameEdit = findViewById(R.id.name);
        nameEdit.setText(StaticData.getCurUser().getName());
        viewPwdText = findViewById(R.id.view_pwd);
        pwdText = findViewById(R.id.pwd);
        pwdText.setText("********");
        changeHeaderLayout = findViewById(R.id.change_header);
        backImg = findViewById(R.id.back);
        bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(context).inflate(R.layout.photo_choose_layout,null);
        bottomSheetDialog.setContentView(view);
        photoText = view.findViewById(R.id.photo);
        albumText = view.findViewById(R.id.album);
        headerImg = findViewById(R.id.header);
        String url = StaticData.getCurUser().getImgUrl();
        if(!CheckUtil.checkStringNull(url)){
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(headerImg);
        }
    }

    @Override
    public void setListener() {
        //super.setListener();
        editPhoneText.setOnClickListener(this);
        editNameText.setOnClickListener(this);
        viewPwdText.setOnClickListener(this);
        changeHeaderLayout.setOnClickListener(this);
        backImg.setOnClickListener(this);
        photoText.setOnClickListener(this);
        albumText.setOnClickListener(this);
    }

    @Override
    public void setAdapter() {
        super.setAdapter();
    }

    @Override
    public void onClick(View view) {
        String content = "";
        switch (view.getId()){
            case R.id.edit_phone:
                //设置可以为可以更改
                content = editPhoneText.getText().toString();
                if(content.equals("编辑")){
                    phoneEdit.setEnabled(true);
                    editPhoneText.setText(R.string.determine);
                }else{
                    phoneEdit.setEnabled(false);
                    editPhoneText.setText(R.string.edit);
                    //更新信息
                    User eidtUser = StaticData.getCurUser();
                    String newPhone = phoneEdit.getText().toString();
                    if(CheckUtil.checkPhoneValid(newPhone)){
                        eidtUser.setPhone(phoneEdit.getText().toString());
                        new UpdateInfoTask().execute(eidtUser);
                    }else{
                        ViewUtil.showErrorToast("电话号码长度必须11位!",context);
                    }
                }
                
                break;
            case R.id.edit_name:
                content = editNameText.getText().toString();
                if(content.equals("编辑")){
                    nameEdit.setEnabled(true);
                    editNameText.setText(R.string.determine);
                }else{
                    nameEdit.setEnabled(false);
                    editNameText.setText(R.string.edit);
                    //更新信息
                    User eidtUser = StaticData.getCurUser();
                    String newName = nameEdit.getText().toString();
                    if(newName != null && newName.length() != 0){
                        eidtUser.setName(nameEdit.getText().toString());
                        new UpdateInfoTask().execute(eidtUser);
                    }else{
                        ViewUtil.showErrorToast("用户名不能为空!",context);
                    }
                }
                break;
            case R.id.view_pwd:
                content = viewPwdText.getText().toString();
                String pwd = StaticData.getCurUser().getPwd();
                if(content.equals("查看")){
                    viewPwdText.setText(R.string.hide);
                    pwdText.setText(pwd);
                }else{
                    viewPwdText.setText(R.string.view);
                    pwdText.setText("********");
                }
                break;
            case R.id.change_header:
                bottomSheetDialog.show();
                break;
            case R.id.back:
                ViewUtil.jumpTo(context, MainActivity.class);
                finish();
                break;
            case R.id.photo:
                bottomSheetDialog.dismiss();
                if (checkPermission(CAMERA_PERMISSION)) {
                    Log.e(TAG,"开始拍照");
                    Toast.makeText(context,"开始拍照",Toast.LENGTH_LONG).show();
                    photo();
                } else {
                    //申请拍照权限和读取权限
                    Log.e(TAG,"申请拍照");
                    startRequestPhotoPermission();
                }
                break;
            case R.id.album:
                bottomSheetDialog.dismiss();
                if (checkPermission(READ_EXTERNAL_STORAGE)) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, CHOOSE_ALBUM);
                }
                else {
                    //申请拍照权限和读取权限
                    startRequestReadPermission();
                }
                break;
            default:
        }
    }

    private boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                Log.e("checkPermission", "PERMISSION_GRANTED" + ContextCompat.checkSelfPermission(context, permission));
                return true;
            } else {
                Log.e("checkPermission", "PERMISSION_DENIED" + ContextCompat.checkSelfPermission(context, permission));
                return false;
            }
        } else {
            Log.e("checkPermission", "M以下" + ContextCompat.checkSelfPermission(context, permission));
            return true;
        }
    }

    private void startRequestPhotoPermission() {
        //请求多个权限
        RxPermissions.getInstance(context)
                .request(Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)//多个权限用","隔开
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //当所有权限都允许之后，返回true
                            photo();
                        } else {
                            //只要有一个权限禁止，返回false，
                            //下一次申请只申请没通过申请的权限
                            return;
                        }
                    }
                });
    }

    private void startRequestReadPermission() {
        RxPermissions.getInstance(context)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)//多个权限用","隔开
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //当所有权限都允许之后，返回true
                            Intent i = new Intent(
                                    // 相册
                                    Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(i, CHOOSE_ALBUM);
                        } else {
                            //只要有一个权限禁止，返回false，
                            //下一次申请只申请没通过申请的权限
                            return;
                        }
                    }
                });
    }

    /**
     * 拍照
     */
    public void photo() {
        try {
            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String sdcardState = Environment.getExternalStorageState();
            String sdcardPathDir = Environment.getExternalStorageDirectory().getPath() + "/tempImage/";
            file = null;
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                // 有sd卡，是否有myImage文件夹
                File fileDir = new File(sdcardPathDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                // 是否有headImg文件
                long l = System.currentTimeMillis();
                String img_name = sdcardPathDir+l+".png";
                file = new File(img_name);
            }
            if (file != null) {
                path = file.getPath();
                photoUri = Uri.fromFile(file);
                if (Build.VERSION.SDK_INT >= 24) {
                    photoUri = FileProvider.getUriForFile(context, "com.example.emergencyhelper.fileProvider", file);
                } else {
                    photoUri = Uri.fromFile(file);
                }
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(openCameraIntent, TAKE_PICTURE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (file != null && file.exists()){
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    headerImg.setImageBitmap(bitmap);
                    new UploadHeaderTask().execute(file);
                }
                break;
            case CHOOSE_ALBUM:
                if (resultCode == RESULT_OK && null != data) {
                    Uri uri = data.getData();
                    path = getRealPathFromUri(context,uri);
                    Log.e(TAG,path);
                    if (path != null && path.length() != 0) {
                        Bitmap bitmap2 = BitmapFactory.decodeFile(path);
                        //设置头像
                        headerImg.setImageBitmap(bitmap2);
                        //更新头像信息
                        File img_file = new File(path);
                        new UploadHeaderTask().execute(img_file);
                    }
                }
                break;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class UploadHeaderTask extends AsyncTask<File,Integer,Integer>{

        @SneakyThrows
        @Override
        protected Integer doInBackground(File... files) {
            Response response = new UserRequest().uploadHeader(files[0]);
            if(response == null){
                return -1;
            }
            //返回头像网址
            String body = response.body().string();
            Log.e(TAG,"UploadHeaderTask:"+body);
            if(response.code() == 200) {
                //保存用户信息
                User newUser = StaticData.getCurUser();
                newUser.setImgUrl(body);
                StaticData.setUserSP(newUser);
                StaticData.setCurUser(newUser);
            }
            return response.code();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            String msg = "";
            switch (integer){
                case -1:
                    msg = "请检查网络状态后重新尝试！";
                    ViewUtil.showErrorToast(msg,context);
                    break;
                case 200:
                    ViewUtil.showNotice("头像上传成功",context);
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class UpdateInfoTask extends AsyncTask<User,Integer,Integer>{

        @SneakyThrows
        @Override
        protected Integer doInBackground(User... users) {
            Response response = new UserRequest().updateInfo(users[0],StaticData.getCurUser().getPhone());
            if(response == null){
                return -1;
            }
            //返回值里面有user信息
            Gson gson = StaticData.getGson();
            String body = response.body().string();
            User user = gson.fromJson(body,User.class);
            Log.e(TAG, "doInBackground: get the user" + body);
            if(response.code() == 200){
                //保存用户信息
                StaticData.setUserSP(user);
                StaticData.setCurUser(user);
            }
            return response.code();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            String msg = "";
            switch (integer){
                case -1:
                    msg = "请检查网络状态后重新尝试！";
                    ViewUtil.showErrorToast(msg,context);
                    break;
                case 200:
                    ViewUtil.showNotice("修改信息成功",context);
                    break;
                case 401:
                    msg = "产生冲突";
                    ViewUtil.showErrorToast(msg,context);
                    break;
                default:
                    msg = "未知错误!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }

    /**
     * 根据Uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    public static String getRealPathFromUri(Context context, Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= 19) { // api >= 19
            return getRealPathFromUriAboveApi19(context, uri);
        } else { // api < 19
            return getRealPathFromUriBelowAPI19(context, uri);
        }
    }

    /**
     * 适配api19以下(不包括api19),根据uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    private static String getRealPathFromUriBelowAPI19(Context context, Uri uri) {
        return getDataColumn(context, uri, null, null);
    }

    /**
     * 适配api19及以上,根据uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    @SuppressLint("NewApi")
    private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) { // MediaProvider
                // 使用':'分割
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())){
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        return filePath;
    }

    /**
     * 获取数据库表中的 _data 列，即返回Uri对应的文件路径
     * @return
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is MediaProvider
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
}