package du.zhou.com.du;

import android.app.Application;
import android.widget.Toast;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import du.zhou.com.du.common.PreferenceUtils;
import du.zhou.com.du.model.User;

/**
 * Created by zhou on 2016/12/19.
 */

public class App extends Application {
    private static App instance;
    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        XLog.init(LogLevel.ALL);
        Bmob.initialize(this,"70cd28d7e0e1294eef479303c4e9f731");


        loginAccount();
    }

    private void loginAccount(){
        User user = new User();
        user.setUsername("zhou_guobao");
        user.setPassword("123456");
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                XLog.e(user.getObjectId() + "" + user.getUsername());
                PreferenceUtils.setPrefString(instance,"user_id",user.getObjectId());
                Toast.makeText(instance,"登录成功-"+user.getObjectId(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
