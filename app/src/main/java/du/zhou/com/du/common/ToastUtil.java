package du.zhou.com.du.common;

import android.view.Gravity;
import android.widget.Toast;

import du.zhou.com.du.App;

/**
 * Created by zhou on 2016/12/23.
 */

public class ToastUtil {

    public static void show(int resId) {
        show(App.getInstance().getString(resId));
    }

    public static void show(String msg) {
        Toast toast = Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
