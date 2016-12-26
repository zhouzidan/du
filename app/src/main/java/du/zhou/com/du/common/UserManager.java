package du.zhou.com.du.common;

import android.text.TextUtils;

import com.elvishew.xlog.XLog;
import com.yakivmospan.scytale.Crypto;
import com.yakivmospan.scytale.Options;
import com.yakivmospan.scytale.Store;

import javax.crypto.SecretKey;

import du.zhou.com.du.App;

/**
 * Created by zhou on 2016/12/26.
 */
public class UserManager {

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private static UserManager ourInstance = new UserManager();

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
        // init key
        Store store = new Store(App.getInstance());
        if (!store.hasKey("du"))
            store.generateSymmetricKey("du", "du".toCharArray());


    }

    public void saveTempUserName(String username) {
        PreferenceUtils.setPrefString(App.getInstance(), KEY_USERNAME, username);
    }

    public String getTempUserName() {
        return PreferenceUtils.getPrefString(App.getInstance(), KEY_USERNAME, "");
    }

    public void saveTempPassword(String password) {
        if (TextUtils.isEmpty(password) == false) {
            SecretKey secretKey = new Store(App.getInstance()).getSymmetricKey("du", "du".toCharArray());
            Crypto crypto = new Crypto(Options.TRANSFORMATION_SYMMETRIC);
            String encryptedData = crypto.encrypt(password, secretKey);
            PreferenceUtils.setPrefString(App.getInstance(), KEY_PASSWORD, encryptedData);
        }
    }

    public String getTempPassword() {
        String password = PreferenceUtils.getPrefString(App.getInstance(), KEY_PASSWORD, "");
        XLog.e("getTempPassword:"+password);
        if (TextUtils.isEmpty(password) == false) {
            SecretKey secretKey = new Store(App.getInstance()).getSymmetricKey("du", "du".toCharArray());
            Crypto crypto = new Crypto(Options.TRANSFORMATION_SYMMETRIC);
            String encryptedData = crypto.decrypt(password, secretKey);
            XLog.e("getTempPassword:encryptedData"+encryptedData);
            return encryptedData;
        }
        return "";
    }
}
