package du.zhou.com.du.common;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import du.zhou.com.du.App;

/**
 * Created by zhou on 2016/11/17.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    /**
     * 单位dp
     *
     * @param space
     */
    public SpacesItemDecoration(int space) {
        this.space = DotUtils.dip2px(App.getInstance(), space);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildPosition(view) == 0){}

    }
}
