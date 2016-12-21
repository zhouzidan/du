package du.zhou.com.du.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import du.zhou.com.du.R;

/**
 * Created by zhou on 2016/12/11.
 */

public class AddImageGroupAdapter extends RecyclerView.Adapter<AddImageGroupAdapter.AddImageHolder> {

    private List<String> localImagePaths;
    private Context context;
    private AddImageCallback callback;


    public AddImageGroupAdapter(Context context, List<String> localImagePaths) {
        this.context = context;
        this.localImagePaths = localImagePaths;
    }


    @Override
    public AddImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_add_image_group, null);
        return new AddImageHolder(view);
    }

    @Override
    public void onBindViewHolder(AddImageHolder holder, final int position) {
        if (position == localImagePaths.size()) {
            holder.imageView.setImageResource(R.mipmap.icon_plus);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callback != null) callback.addNewOne();
                }
            });
        }else {
            Glide.with(context).load(localImagePaths.get(position)).into(holder.imageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callback != null) callback.showLargeImg(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return localImagePaths.size() + 1;
    }

    public class AddImageHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public AddImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    public void setCallback(AddImageCallback callback){
        this.callback = callback;
    }


    public interface AddImageCallback{
        public void addNewOne();
        public void showLargeImg(int position);
    }
}
