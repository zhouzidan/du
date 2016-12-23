package du.zhou.com.du.business;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import du.zhou.com.du.R;
import du.zhou.com.du.adapter.MainViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    AHBottomNavigation navigation;
    AHBottomNavigationViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        navigation = (AHBottomNavigation) findViewById(R.id.navigation);
        // Change colors
        navigation.setAccentColor(getResources().getColor(R.color.colorPrimary));
        navigation.setInactiveColor(Color.DKGRAY);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("首页", R.mipmap.ic_index);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("广场", R.mipmap.ic_yes);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("我的", R.mipmap.ic_mine);
        navigation.addItem(item1);
        navigation.addItem(item2);
        navigation.addItem(item3);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position);
                return true;
            }
        });
    }
}
