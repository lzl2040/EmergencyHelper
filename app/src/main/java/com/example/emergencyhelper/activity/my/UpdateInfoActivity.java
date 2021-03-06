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
    //??????
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
                //???????????????????????????
                content = editPhoneText.getText().toString();
                if(content.equals("??????")){
                    phoneEdit.setEnabled(true);
                    editPhoneText.setText(R.string.determine);
                }else{
                    phoneEdit.setEnabled(false);
                    editPhoneText.setText(R.string.edit);
                    //????????????
                    User eidtUser = StaticData.getCurUser();
                    String newPhone = phoneEdit.getText().toString();
                    if(CheckUtil.checkPhoneValid(newPhone)){
                        eidtUser.setPhone(phoneEdit.getText().toString());
                        new UpdateInfoTask().execute(eidtUser);
                    }else{
                        ViewUtil.showErrorToast("????????????????????????11???!",context);
                    }
                }
                
                break;
            case R.id.edit_name:
                content = editNameText.getText().toString();
                if(content.equals("??????")){
                    nameEdit.setEnabled(true);
                    editNameText.setText(R.string.determine);
                }else{
                    nameEdit.setEnabled(false);
                    editNameText.setText(R.string.edit);
                    //????????????
                    User eidtUser = StaticData.getCurUser();
                    String newName = nameEdit.getText().toString();
                    if(newName != null && newName.length() != 0){
                        eidtUser.setName(nameEdit.getText().toString());
                        new UpdateInfoTask().execute(eidtUser);
                    }else{
                        ViewUtil.showErrorToast("?????????????????????!",context);
                    }
                }
                break;
            case R.id.view_pwd:
                content = viewPwdText.getText().toString();
                String pwd = StaticData.getCurUser().getPwd();
                if(content.equals("??????")){
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
                    Log.e(TAG,"????????????");
                    Toast.makeText(context,"????????????",Toast.LENGTH_LONG).show();
                    photo();
                } else {
                    //?????????????????????????????????
                    Log.e(TAG,"????????????");
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
                    //?????????????????????????????????
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
            Log.e("checkPermission", "M??????" + ContextCompat.checkSelfPermission(context, permission));
            return true;
        }
    }

    private void startRequestPhotoPermission() {
        //??????????????????
        RxPermissions.getInstance(context)
                .request(Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)//???????????????","??????
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //???????????????????????????????????????true
                            photo();
                        } else {
                            //????????????????????????????????????false???
                            //????????????????????????????????????????????????
                            return;
                        }
                    }
                });
    }

    private void startRequestReadPermission() {
        RxPermissions.getInstance(context)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)//???????????????","??????
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //???????????????????????????????????????true
                            Intent i = new Intent(
                                    // ??????
                                    Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(i, CHOOSE_ALBUM);
                        } else {
                            //????????????????????????????????????false???
                            //????????????????????????????????????????????????
                            return;
                        }
                    }
                });
    }

    /**
     * ??????
     */
    public void photo() {
        try {
            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String sdcardState = Environment.getExternalStorageState();
            String sdcardPathDir = Environment.getExternalStorageDirectory().getPath() + "/tempImage/";
            file = null;
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                // ???sd???????????????myImage?????????
                File fileDir = new File(sdcardPathDir);
                if (!fileDir.exists()) {
                    fileDir.mkdirs();
                }
                // ?????????headImg??????
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
                        //????????????
                        headerImg.setImageBitmap(bitmap2);
                        //??????????????????
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
            //??????????????????
            String body = response.body().string();
            Log.e(TAG,"UploadHeaderTask:"+body);
            if(response.code() == 200) {
                //??????????????????
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
                    msg = "???????????????????????????????????????";
                    ViewUtil.showErrorToast(msg,context);
                    break;
                case 200:
                    ViewUtil.showNotice("??????????????????",context);
                    break;
                default:
                    msg = "????????????!\n code:" + integer;
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
            //??????????????????user??????
            Gson gson = StaticData.getGson();
            String body = response.body().string();
            User user = gson.fromJson(body,User.class);
            Log.e(TAG, "doInBackground: get the user" + body);
            if(response.code() == 200){
                //??????????????????
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
                    msg = "???????????????????????????????????????";
                    ViewUtil.showErrorToast(msg,context);
                    break;
                case 200:
                    ViewUtil.showNotice("??????????????????",context);
                    break;
                case 401:
                    msg = "????????????";
                    ViewUtil.showErrorToast(msg,context);
                    break;
                default:
                    msg = "????????????!\n code:" + integer;
                    ViewUtil.showErrorToast(msg,context);
                    break;
            }
        }
    }

    /**
     * ??????Uri???????????????????????????
     *
     * @param context ???????????????
     * @param uri     ?????????Uri
     * @return ??????Uri?????????????????????, ????????????????????????????????????, ????????????null
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
     * ??????api19??????(?????????api19),??????uri???????????????????????????
     *
     * @param context ???????????????
     * @param uri     ?????????Uri
     * @return ??????Uri?????????????????????, ????????????????????????????????????, ????????????null
     */
    private static String getRealPathFromUriBelowAPI19(Context context, Uri uri) {
        return getDataColumn(context, uri, null, null);
    }

    /**
     * ??????api19?????????,??????uri???????????????????????????
     *
     * @param context ???????????????
     * @param uri     ?????????Uri
     * @return ??????Uri?????????????????????, ????????????????????????????????????, ????????????null
     */
    @SuppressLint("NewApi")
    private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ?????????document????????? uri, ?????????document id???????????????
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) { // MediaProvider
                // ??????':'??????
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())){
            // ????????? content ????????? Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // ????????? file ????????? Uri,?????????????????????????????????
            filePath = uri.getPath();
        }
        return filePath;
    }

    /**
     * ???????????????????????? _data ???????????????Uri?????????????????????
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