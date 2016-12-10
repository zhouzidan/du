package du.zhou.com.du;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
    private void initView(){
        navigation = (AHBottomNavigation) findViewById(R.id.navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("首页",R.mipmap.ic_launcher);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("广场",R.mipmap.ic_launcher);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("我的",R.mipmap.ic_launcher);
        navigation.addItem(item1);
        navigation.addItem(item2);
        navigation.addItem(item3);
        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
    }
}
