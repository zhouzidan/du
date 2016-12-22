package du.zhou.com.du.business.addproduct;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;

import com.elvishew.xlog.XLog;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import java.util.ArrayList;
import java.util.List;

import du.zhou.com.du.R;
import du.zhou.com.du.adapter.AddImageGroupAdapter;
import du.zhou.com.du.business.account.LoginActivity;
import du.zhou.com.du.common.SpacesItemDecoration;
import du.zhou.com.du.model.db.User;
import du.zhou.com.du.view.TitleView;

/**
 * Created by zhou on 2016/12/11.
 */

public class AddProductActivity extends AppCompatActivity {
    EditText contentEditText;
    XRecyclerView recyclerView;
    TitleView titleView;

    private int IMG_MAX_SIZE = 12;

    private int CODE_REQUEST_IMG = 1111;

    private ArrayList<String> localImagePaths = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        XLog.e("是否为空：" + (User.getCurrentUser(User.class) == null));
        if (User.getCurrentUser(User.class) == null) {
            Intent mIntent = new Intent(this, LoginActivity.class);
            startActivity(mIntent);
        }
    }

    private void initView() {
        titleView = (TitleView) findViewById(R.id.titleView);
        contentEditText = (EditText) findViewById(R.id.edit_content);
        recyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        AddImageGroupAdapter addImageGroupAdapter = new AddImageGroupAdapter(this, localImagePaths);
        recyclerView.setAdapter(addImageGroupAdapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(5));
        addImageGroupAdapter.setCallback(addImageCallback);
        titleView.setRightTextViewClickListener(rightClickListener);
    }

    private View.OnClickListener rightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String content = contentEditText.getText().toString().trim();
//            addImageModels;
            Intent intent = new Intent(AddProductActivity.this, UploadService.class);
            intent.putExtra(UploadService.KEY_TXT, content);
            intent.putStringArrayListExtra(UploadService.KEY_IMGS, localImagePaths);
            startService(intent);

        }
    };

    AddImageGroupAdapter.AddImageCallback addImageCallback = new AddImageGroupAdapter.AddImageCallback() {
        @Override
        public void addNewOne() {
            GalleryConfig config = new GalleryConfig.Build()
                    .limitPickPhoto(IMG_MAX_SIZE - localImagePaths.size())
                    .singlePhoto(false)
                    .hintOfPick("this is pick hint")
                    .filterMimeTypes(new String[]{"image/jpeg"})
                    .build();
            GalleryActivity.openActivity(AddProductActivity.this, CODE_REQUEST_IMG, config);
        }

        @Override
        public void showLargeImg(int position) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_REQUEST_IMG && data != null && data.hasExtra(GalleryActivity.PHOTOS)) {
            //list of photos of seleced
            List<String> photos = (List<String>) data.getSerializableExtra(GalleryActivity.PHOTOS);
            for (String photoLocalPath : photos) {
                localImagePaths.add(photoLocalPath);
            }
            recyclerView.getAdapter().notifyDataSetChanged();
        }

    }
}
