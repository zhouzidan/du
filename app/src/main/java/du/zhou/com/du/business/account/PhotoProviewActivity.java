package du.zhou.com.du.business.account;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import du.zhou.com.du.R;
import du.zhou.com.du.common.ToastUtil;
import du.zhou.com.du.model.db.User;

public class PhotoProviewActivity extends AppCompatActivity {
    private Context mContext = PhotoProviewActivity.this;
    public final static String EXTRA_PHOTO = "extra_photo";

    private ImageView mPhotoPreview;
    private String mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_proview);
        mPhotoPreview = (ImageView) findViewById(R.id.img);
        if (getIntent() != null && getIntent().hasExtra(EXTRA_PHOTO)) {
            mFilePath = getIntent().getStringExtra(EXTRA_PHOTO);
            Glide.with(this).load(mFilePath).into(mPhotoPreview);
        }

    }

    public void onClickSavePhoto(View view) {
        if (TextUtils.isEmpty(mFilePath) == false) {
            //上传图片
            final BmobFile bmobFile = new BmobFile(new File(mFilePath));
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        //更新服务器数据
                        updateUser(bmobFile.getFileUrl());
                    } else
                        ToastUtil.show(R.string.update_failed);
                }
            });
        }
    }

    private void updateUser(String remoteUrl) {
        User user = new User();
        user.setFaceUrl(remoteUrl);
        BmobUser bmobUser = BmobUser.getCurrentUser();
        if (bmobUser != null && TextUtils.isEmpty(bmobUser.getObjectId()) == false) {
            user.update(bmobUser.getObjectId(), new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        ToastUtil.show(R.string.update_success);
                    } else {
                        ToastUtil.show(R.string.update_failed);
                    }
                }
            });
        }

    }
}
