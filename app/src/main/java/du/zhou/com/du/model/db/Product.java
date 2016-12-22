package du.zhou.com.du.model.db;

import cn.bmob.v3.BmobObject;

/**
 * 产品信息表
 * Created by zhou on 2016/12/20.
 */

public class Product extends BmobObject {

    private String content;
    private String title;
    private User creator;


    @Override
    public void setTableName(String tableName) {
        super.setTableName("product");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }


    @Override
    public String toString() {
        return "Product{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", creator=" + creator +
                ", objectId=" + getObjectId() +
                ", createTime=" + getCreatedAt() +

                '}';
    }
}
