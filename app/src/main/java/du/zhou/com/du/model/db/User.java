package du.zhou.com.du.model.db;

import cn.bmob.v3.BmobUser;

/**
 * 用户表
 * Created by zhou on 2016/12/20.
 */

public class User extends BmobUser {

    @Override
    public void setTableName(String tableName) {
        super.setTableName("_User");
    }


    @Override
    public String toString() {
        return "User{"+ "object:"+ getObjectId() +"username:"+ getUsername() +
                "}";
    }
}