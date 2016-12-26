package du.zhou.com.du.business.account;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v13.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import java.io.File;

import cn.bmob.v3.BmobUser;
import du.zhou.com.du.R;
import du.zhou.com.du.common.CapturePhotoHelper;
import du.zhou.com.du.common.FolderManager;

public class AccountSettingActivity extends AppCompatActivity {
    private static final int CODE_REQUEST_IMG = 1112;
    private static final int CODE_PHOTO_PROVIEW = 1113;


    private final static String EXTRA_RESTORE_PHOTO = "extra_restore_photo";

    /**
     * 运行时权限申请码
     */
    private final static int RUNTIME_PERMISSION_REQUEST_CODE = 0x1;

    private CapturePhotoHelper mCapturePhotoHelper;
    private File mRestorePhotoFile;

    // 账户设置  头像 密码 邮箱

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mCapturePhotoHelper != null) {
            mRestorePhotoFile = mCapturePhotoHelper.getPhoto();
            if (mRestorePhotoFile != null) {
                outState.putSerializable(EXTRA_RESTORE_PHOTO, mRestorePhotoFile);
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mCapturePhotoHelper != null) {
            mRestorePhotoFile = (File) savedInstanceState.getSerializable(EXTRA_RESTORE_PHOTO);
            mCapturePhotoHelper.setPhoto(mRestorePhotoFile);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CapturePhotoHelper.CAPTURE_PHOTO_REQUEST_CODE) {
            File photoFile = mCapturePhotoHelper.getPhoto();
            if (photoFile != null) {
                if (resultCode == RESULT_OK) {
                String imgFilePath = photoFile.getPath();
                    Intent intent = new Intent(AccountSettingActivity.this, PhotoProviewActivity.class);
                    intent.putExtra(PhotoProviewActivity.EXTRA_PHOTO,imgFilePath);
                    startActivityForResult(intent,CODE_PHOTO_PROVIEW);
                } else {
                    if (photoFile.exists()) {
                        photoFile.delete();
                    }
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onClickLogout(View view){
        BmobUser.logOut();
        finish();
    }

    public void onClickChoosePhotoDialog(View view) {
        new MaterialDialog.Builder(this)
                .title("选择头像")
                .items(new String[]{"拍照", "相册"})
                .negativeText("取消")
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        if (which == 0) {
                            takePhotoByCarema();
                        } else if (which == 1) {
                            choosePhotoFromLocal();
                        }
                    }
                })
                .show();
    }


    /**
     * 拍照
     */
    private void takePhotoByCarema(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //Android M 处理Runtime Permission
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {//检查是否有写入SD卡的授权
//                Log.i(TAG, "granted permission!");
                turnOnCamera();
            } else {
//                Log.i(TAG, "denied permission!");
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                    Log.i(TAG, "should show request permission rationale!");
                }
                requestPermission();
            }
        } else {
            turnOnCamera();
        }
    }
    /**
     * 本地相册
     */
    private void choosePhotoFromLocal(){
        GalleryConfig config = new GalleryConfig.Build()
                .singlePhoto(true)
                .hintOfPick("this is pick hint")
                .filterMimeTypes(new String[]{"image/jpeg"})
                .build();
        GalleryActivity.openActivity(AccountSettingActivity.this, CODE_REQUEST_IMG, config);

    }

    /**
     * 开启相机
     */
    private void turnOnCamera() {
        if (mCapturePhotoHelper == null) {
            mCapturePhotoHelper = new CapturePhotoHelper(this, FolderManager.getPhotoFolder());
        }
        mCapturePhotoHelper.capture();
    }

    /**
     * 申请写入sd卡的权限
     */
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RUNTIME_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RUNTIME_PERMISSION_REQUEST_CODE) {
            for (int index = 0; index < permissions.length; index++) {
                String permission = permissions[index];
                if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {
                    if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
//                        Log.i(TAG, "onRequestPermissionsResult: permission is granted!");
                        turnOnCamera();

                    } else {
                        showMissingPermissionDialog();

                    }
                }
            }
        }
    }

    /**
     * 显示打开权限提示的对话框
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.help);
        builder.setMessage(R.string.help_content);

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AccountSettingActivity.this, R.string.camera_open_error, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                turnOnSettings();
            }
        });

        builder.show();
    }

    /**
     * 启动系统权限设置界面
     */
    private void turnOnSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

}
