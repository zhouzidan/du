package du.zhou.com.du.model.db;

import cn.bmob.v3.BmobObject;

/**
 * 产品的详情
 * Created by zhou on 2016/12/21.
 */

public class ProductDetail extends BmobObject {
    private Product product;
    private String type;
    private String value;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void setTableName(String tableName) {
        super.setTableName("ProductDetail");
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "product=" + product +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
