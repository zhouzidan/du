package du.zhou.com.du.business.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elvishew.xlog.XLog;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import du.zhou.com.du.R;
import du.zhou.com.du.adapter.IndexAdapter;
import du.zhou.com.du.common.Contants;
import du.zhou.com.du.common.SpacesItemDecoration;
import du.zhou.com.du.model.ProductModel;
import du.zhou.com.du.model.db.Product;
import du.zhou.com.du.model.db.ProductDetail;


public class IndexFragment extends Fragment {

    List<ProductModel> productModels = new ArrayList<>();
    IndexAdapter indexAdapter = null;

    public IndexFragment() {
        // Required empty public constructor
    }

    public static IndexFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        XRecyclerView recyclerView = (XRecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(5));
        indexAdapter = new IndexAdapter(getContext(), productModels);
        recyclerView.setAdapter(indexAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }


    private void loadData() {
// 查询 分页
        BmobQuery<Product> productBmobQuery = new BmobQuery<>();
        productBmobQuery.setLimit(Contants.PAGE_SIZE);
        productBmobQuery.order("-createdAt");

        BmobQuery<ProductDetail> productDetailBmobQuery = new BmobQuery<ProductDetail>();
        productDetailBmobQuery.addWhereMatchesQuery("product", "Product", productBmobQuery);
        productDetailBmobQuery.order("createdAt");
        productDetailBmobQuery.include("product");
        productDetailBmobQuery.findObjects(new FindListener<ProductDetail>() {
            @Override
            public void done(List<ProductDetail> list, BmobException e) {
                if (e == null) {
                    String productObjId = "";
                    List<ProductDetail> temps = new ArrayList<ProductDetail>();
                    for (ProductDetail detail : list) {
                        XLog.e(detail.toString());
                        if (detail.getProduct().getObjectId().equals(productObjId)) {
                            temps.add(detail);
                        } else {
                            if (temps.size() > 0) {
                                productModels.add(new ProductModel(temps));
                                temps.clear();
                            }
                            productObjId = detail.getProduct().getObjectId();
                            temps.add(detail);
                        }
                    }
                    if (temps.size() > 0)
                        productModels.add(new ProductModel(temps));
                    indexAdapter.notifyDataSetChanged();
                } else {
                    XLog.e(e.toString());
                }
            }
        });

    }

}
