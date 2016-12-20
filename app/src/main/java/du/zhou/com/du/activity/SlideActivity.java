package du.zhou.com.du.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import du.zhou.com.du.R;
import du.zhou.com.du.adapter.SlideImageAdapter;

/**
 * Created by zhou on 2016/12/9.
 */

public class SlideActivity extends AppCompatActivity {
    AutoScrollViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        viewPager = (AutoScrollViewPager) findViewById(R.id.view_pager);
        List<ImageView> imgUrls = new ArrayList<>();
        ImageView imageView = new ImageView(this);
        Glide.with(this).load(R.mipmap.slide_1).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imgUrls.add(imageView);
        imageView = new ImageView(this);
        Glide.with(this).load(R.mipmap.slide_2).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imgUrls.add(imageView);
        viewPager.setAdapter(new SlideImageAdapter(this,imgUrls));
    }
}
