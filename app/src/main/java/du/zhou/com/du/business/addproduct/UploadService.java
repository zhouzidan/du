package du.zhou.com.du.business.addproduct;

import android.app.IntentService;
import android.content.Intent;

import com.elvishew.xlog.XLog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import du.zhou.com.du.common.Contants;
import du.zhou.com.du.model.db.Product;
import du.zhou.com.du.model.db.ProductDetail;
import du.zhou.com.du.model.db.User;

/**
 * 文件上传服务
 * Created by zhou on 2016/12/20.
 */

public class UploadService extends IntentService {
    public static final String CODE = "code";

    public static final String KEY_TXT = "key_txt";
    public static final String KEY_IMGS = "key_imgs";

    public UploadService() {
        super("UploadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent.hasExtra(KEY_TXT) && intent.hasExtra(KEY_IMGS)) {
            final String txt = intent.getStringExtra(KEY_TXT);
            List<String> localImagePaths = intent.getStringArrayListExtra(KEY_IMGS);
            final String[] imgPathArray = localImagePaths.toArray(new String[localImagePaths.size()]);
            // 上传图片
            // 上传文字和图片URL
            BmobFile.uploadBatch(imgPathArray, new UploadBatchListener() {
                @Override
                public void onSuccess(List<BmobFile> list, List<String> list1) {
                    if (imgPathArray.length == list.size()) {
                        for (UploadCallBack callback : mUploadCallSet) {
                            callback.imgUploadDone(list.size(), imgPathArray.length);
                        }
                        //TODO all success
                        Product product = new Product();
                        product.setContent(txt);
                        product.setCreator(BmobUser.getCurrentUser(User.class));

                        List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
                        for (String imgURL : list1) {
                            ProductDetail detail = new ProductDetail();
                            detail.setType(Contants.ProductDetailType.TYPE_IMG.getValue());
                            detail.setValue(imgURL);
                            productDetails.add(detail);
                        }
                        insertBatch(product, productDetails);
                    } else {
                        //TODO 成功第list.size()个图片
                    }
                }

                @Override
                public void onProgress(int curIndex, int curPercent, int total,
                                       int totalPercent) {
                    for (UploadCallBack callback : mUploadCallSet) {
                        callback.imgUploadProgress(curIndex, curPercent, total, totalPercent);
                    }
                }

                @Override
                public void onError(int statuscode, String errormsg) {
                    for (UploadCallBack callback : mUploadCallSet) {
                        callback.imgUploadDone(0, imgPathArray.length);
                    }
                }
            });

        }
    }

    private
    void insertBatch(final Product product, final List<ProductDetail> productDetails) {
        product.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    product.setObjectId(objectId);
                    List<BmobObject> bmobObjects = new ArrayList<BmobObject>();
                    for (ProductDetail detail : productDetails) {
                        detail.setProduct(product);
                        bmobObjects.add(detail);
                    }

                    new BmobBatch().insertBatch(bmobObjects).doBatch(new QueryListListener<BatchResult>() {
                        @Override
                        public void done(List<BatchResult> list, BmobException e) {
                            if (e == null) {
                                for (int i = 0; i < list.size(); i++) {
                                    BatchResult result = list.get(i);
                                    if (result.getError() == null) {
                                        XLog.e("添加成功--" + result.getObjectId() + "--" + result.getUpdatedAt());
                                    } else {
                                        XLog.e("第" + i + "条数据添加失败--" + e.getErrorCode());
                                    }
                                }
                                for (UploadCallBack callback : mUploadCallSet) {
                                    callback.insertDataDone(true);
                                }
                            }
                        }
                    });
                } else {
                    for (UploadCallBack callback : mUploadCallSet) {
                        callback.insertDataDone(false);
                    }
                }
            }
        });
    }

    public interface UploadCallBack {
        public void imgUploadProgress(int curIndex, int curPercent, int total,
                                      int totalPercent);

        public void imgUploadDone(int successSize, int totalSize);

        public void insertDataDone(boolean isSuccess);

    }

    private static Set<UploadCallBack> mUploadCallSet = new HashSet<>();

    public static void addCallBack(UploadCallBack callback) {
        mUploadCallSet.add(callback);
    }

    public static void removeCallBack(UploadCallBack callback) {
        mUploadCallSet.remove(callback);
    }


}
