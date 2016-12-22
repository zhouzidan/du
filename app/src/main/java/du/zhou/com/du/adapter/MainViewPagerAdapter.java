package du.zhou.com.du.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

import du.zhou.com.du.business.main.IndexFragment;
import du.zhou.com.du.business.main.MineFragment;
import du.zhou.com.du.business.main.SquareFragment;

/**
 * Created by zhou on 2016/12/10.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.clear();
        fragments.add(IndexFragment.newInstance());
        fragments.add(SquareFragment.newInstance());
        fragments.add(MineFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * Get the current fragment
     */
    public Fragment getCurrentFragment() {
        return currentFragment;
    }
}
