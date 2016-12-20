package du.zhou.com.du.model;

import java.util.Date;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhou on 2016/12/20.
 */

public class Product extends BmobObject {

    private String content;
    private String title;
    private String creater;
    private Date createTime;
    private Date updateTime;


    @Override
    public void setTableName(String tableName) {
        super.setTableName("product");
    }
}
