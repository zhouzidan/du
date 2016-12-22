package du.zhou.com.du.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elvishew.xlog.XLog;

import java.util.ArrayList;
import java.util.List;

import du.zhou.com.du.R;
import du.zhou.com.du.common.SpacesItemDecoration;
import du.zhou.com.du.model.ProductModel;
import du.zhou.com.du.model.db.ProductDetail;

/**
 * 首页的adapter
 * Created by zhou on 2016/12/11.
 */

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {

    private List<ProductModel> productModels;
    private Context context;


    public IndexAdapter(Context context, List<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_index_recyclerview, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductModel model = productModels.get(position);
        holder.tvDatetime.setText(model.getProduct().getCreatedAt());
        String numberOfPhotos = context.getString(R.string.number_of_photos, model.getProductDetails().size());
        holder.tvPhotoNumber.setText(numberOfPhotos);
        holder.recyclerviewItemPhoto.setAdapter(new IndexItemAdapter(context, getImageUrls(model.getProductDetails())));
    }

    @Override
    public int getItemCount() {
        XLog.e("getItemCount:"+productModels.size());
        return productModels.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDatetime;
        private TextView tvPhotoNumber;
        private RecyclerView recyclerviewItemPhoto;

        public ViewHolder(View view) {
            super(view);
            tvDatetime = (TextView) view.findViewById(R.id.tv_datetime);
            tvPhotoNumber = (TextView) view.findViewById(R.id.tv_photo_number);
            recyclerviewItemPhoto = (RecyclerView) view.findViewById(R.id.recyclerview_item_photo);
            recyclerviewItemPhoto.addItemDecoration(new SpacesItemDecoration(5));
            recyclerviewItemPhoto.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        }
    }

    private List<String> getImageUrls(List<ProductDetail> productDetails) {
        List<String> imgUrls = new ArrayList<>();
        for (ProductDetail detail : productDetails) {
            imgUrls.add(detail.getValue());
        }
        return imgUrls;
    }

}
