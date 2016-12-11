package du.zhou.com.du.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import du.zhou.com.du.R;
import du.zhou.com.du.model.AddImageModel;

/**
 * Created by zhou on 2016/12/11.
 */

public class AddImageGroupAdapter extends RecyclerView.Adapter<AddImageGroupAdapter.AddImageHolder> {

    private List<AddImageModel> addImageModels;
    private Context context;

    public AddImageGroupAdapter(Context context, List<AddImageModel> addImageModels) {
        this.context = context;
        this.addImageModels = addImageModels;
    }

    @Override
    public AddImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AddImageHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return addImageModels.size();
    }

    public class AddImageHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public View delView;

        public AddImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img);
            delView = itemView.findViewById(R.id.img_del);
        }
    }
}
