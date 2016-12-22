package du.zhou.com.du.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import du.zhou.com.du.R;
import du.zhou.com.du.common.DotUtils;


/**
 * Created by zhou on 2016/12/21.
 */

public class IndexItemAdapter extends RecyclerView.Adapter<IndexItemAdapter.ViewHolder> {

    private List<String> imgUrls;
    private Context context;

    public IndexItemAdapter(Context context, List<String> imgUrls) {
        this.imgUrls = imgUrls;
        this.context = context;
    }

    @Override
    public IndexItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_index_item_recyclerview, null);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DotUtils.dip2px(context, 120), DotUtils.dip2px(context, 120));
        view.setLayoutParams(lp);
        return new IndexItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final IndexItemAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(imgUrls.get(position))
                .asBitmap()
                .override(DotUtils.dip2px(context, 120), DotUtils.dip2px(context, 120))
                .centerCrop()
                .into(new BitmapImageViewTarget(holder.imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
//                        super.setResource(resource);
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(10);
                        holder.imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return imgUrls.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }

}
