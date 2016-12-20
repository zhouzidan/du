package du.zhou.com.du.activity;

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

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import du.zhou.com.du.R;
import du.zhou.com.du.adapter.AddImageGroupAdapter;
import du.zhou.com.du.common.SpacesItemDecoration;
import du.zhou.com.du.model.AddImageModel;
import du.zhou.com.du.model.User;
import du.zhou.com.du.view.TitleView;

/**
 * Created by zhou on 2016/12/11.
 */

public class AddProductActivity extends AppCompatActivity {
    EditText contentEditText;
    XRecyclerView recyclerView;
    TitleView titleView;

    private List<AddImageModel> addImageModels = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }

    private void initView() {
        titleView = (TitleView) findViewById(R.id.titleView);
        contentEditText = (EditText) findViewById(R.id.edit_content);
        recyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        AddImageGroupAdapter addImageGroupAdapter = new AddImageGroupAdapter(this, addImageModels);
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

            User user = new User();
            user.setEmail("369178391@qq.com");
            user.setPassword("123456");
            user.setUsername("zhou_guobao");
            user.signUp(new SaveListener<User>() {
                @Override
                public void done(User s, BmobException e) {
                    XLog.e(s + "--" + e.toString());
                }
            });

        }
    };

    private void onClickBack() {

    }

    AddImageGroupAdapter.AddImageCallback addImageCallback = new AddImageGroupAdapter.AddImageCallback() {
        @Override
        public void addNewOne() {
            GalleryConfig config = new GalleryConfig.Build()
                    .limitPickPhoto(3)
                    .singlePhoto(false)
                    .hintOfPick("this is pick hint")
                    .filterMimeTypes(new String[]{"image/jpeg"})
                    .build();
            GalleryActivity.openActivity(AddProductActivity.this, 1, config);
        }

        @Override
        public void showLargeImg(int position) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.hasExtra(GalleryActivity.PHOTOS)) {
            //list of photos of seleced
            List<String> photos = (List<String>) data.getSerializableExtra(GalleryActivity.PHOTOS);
            for (String photoLocalPath : photos) {
                AddImageModel model = new AddImageModel();
                model.localPath = photoLocalPath;
                addImageModels.add(model);
            }
            recyclerView.getAdapter().notifyDataSetChanged();
        }

    }
}
