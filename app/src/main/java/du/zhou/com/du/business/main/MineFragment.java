package du.zhou.com.du.business.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import cn.bmob.v3.BmobUser;
import du.zhou.com.du.R;
import du.zhou.com.du.business.account.LoginActivity;
import du.zhou.com.du.business.account.AccountSettingActivity;
import du.zhou.com.du.model.db.User;

/**
 */
public class MineFragment extends Fragment {

    private ImageView faceImageView;
    private TextView nameTextView;


    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initView(View view) {
        faceImageView = (ImageView) view.findViewById(R.id.img_face);
        nameTextView = (TextView) view.findViewById(R.id.tv_name);
        view.findViewById(R.id.layout_collection).setOnClickListener(onClickListener);
        view.findViewById(R.id.layout_info).setOnClickListener(onClickListener);
        view.findViewById(R.id.layout_photos).setOnClickListener(onClickListener);
    }

    private void initData() {
        User user = BmobUser.getCurrentUser(User.class);
        if (user == null) {
            Glide.with(getActivity()).load(R.mipmap.ic_face_defult).into(faceImageView);
        } else if (TextUtils.isEmpty(user.getFaceUrl()) == false) {
            Glide.with(getActivity()).load(user.getFaceUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(faceImageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    super.setResource(resource);
                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    faceImageView.setImageDrawable(circularBitmapDrawable);
                }
            });
        }

        if (user != null) {
            nameTextView.setText(BmobUser.getCurrentUser().getUsername());
        } else {
            nameTextView.setText("未登录");
        }

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.layout_info:
                    onClickInfo();
                    break;
                case R.id.layout_collection:
                    onClickCollection();
                    break;
                case R.id.layout_photos:
                    onClickShowPhotos();
                    break;
            }
        }
    };

    private void onClickInfo() {
        User user = BmobUser.getCurrentUser(User.class);
        if (user == null){
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }else {
            startActivity(new Intent(getActivity(), AccountSettingActivity.class));
        }
    }


    private void onClickCollection() {

    }

    private void onClickShowPhotos() {

    }
}
