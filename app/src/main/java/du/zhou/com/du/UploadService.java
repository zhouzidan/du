package du.zhou.com.du;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import du.zhou.com.du.model.AddImageModel;

/**
 * 文件上传服务
 * Created by zhou on 2016/12/20.
 */

public class UploadService extends IntentService {

    public static final String CODE = "code" ;

    public static final String KEY_TXT = "key_txt";
    public static final String KEY_IMGS = "key_imgs";

    public UploadService() {
        super("UploadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.hasExtra(KEY_TXT) && intent.hasExtra(KEY_IMGS)){
            String txt = intent.getStringExtra(KEY_TXT);
            List<AddImageModel> addImageModels = intent.getParcelableArrayListExtra(KEY_IMGS);
            // 上传图片
            // 上传文字和图片URL
            BmobFile bmobFile = null;
        }
    }
}
