package du.zhou.com.du.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhou on 2016/12/10.
 */

public class SlideImageAdapter extends PagerAdapter {
    List<ImageView> imgUrls = new ArrayList<>();
    private Context mContext;

    public SlideImageAdapter(Context mContext, List<ImageView> imgUrls) {
        this.mContext = mContext;
        this.imgUrls = imgUrls;
    }

    @Override
    public int getCount() {
        return imgUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imgUrls.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeViewAt(position);
    }


}
