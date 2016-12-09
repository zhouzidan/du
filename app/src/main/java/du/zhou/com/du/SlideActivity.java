package du.zhou.com.du;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by zhou on 2016/12/9.
 */

public class SlideActivity extends AppCompatActivity {
    AutoScrollViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewPager = (AutoScrollViewPager) findViewById(R.id.view_pager);

    }
}
