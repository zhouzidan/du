package du.zhou.com.du.model;

import java.util.List;

import du.zhou.com.du.model.db.Product;
import du.zhou.com.du.model.db.ProductDetail;

/**
 * Created by zhou on 2016/12/21.
 */

public class ProductModel {
    private Product product;
    private List<ProductDetail> productDetails;

    public ProductModel(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
        if (productDetails != null && productDetails.size() > 0)
            this.product = productDetails.get(0).getProduct();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }
}
