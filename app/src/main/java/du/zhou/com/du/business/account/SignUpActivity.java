package du.zhou.com.du.business.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.elvishew.xlog.XLog;
import com.rengwuxian.materialedittext.MaterialEditText;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import du.zhou.com.du.R;
import du.zhou.com.du.common.ToastUtil;
import du.zhou.com.du.model.db.User;

/**
 * 注册
 * Created by zhou on 2016/12/20.
 */

public class SignUpActivity extends AppCompatActivity {
    private MaterialEditText editEmail;
    private MaterialEditText editPassword;
    private MaterialEditText editRepetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editEmail = (MaterialEditText) findViewById(R.id.edit_email);
        editPassword = (MaterialEditText) findViewById(R.id.edit_password);
        editRepetPassword = (MaterialEditText) findViewById(R.id.edit_repet_password);
    }

    public void onClickSignUp(View view) {
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String repetPassword = editRepetPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            editEmail.setError(getString(R.string.error_empty_username));
        } else if (isValidEmail(email) == false) {
            editEmail.setError(getString(R.string.error_invalid_email));
        } else if (TextUtils.isEmpty(password)) {
            editPassword.setError(getString(R.string.error_empty_password));
        } else if (password.equals(repetPassword) == false) {
            editRepetPassword.setError(getString(R.string.error_equal_password));
        } else {
            String username = null;
            String[] emailArray = email.split("@");
            if (emailArray != null && emailArray.length > 0) {
                username = emailArray[0];
            }
            showDialog();
            User bu = new User();
            bu.setUsername(username);
            bu.setEmail(email);
            bu.setPassword(password);
            bu.signUp(new SaveListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    dismissDialog();
                    if (e == null) {
                        XLog.e(user.toString());
                        ToastUtil.show(getString(R.string.msg_signup_success));
                        finish();
                    } else {
                        editEmail.setError(e.getMessage());
                    }
                }
            });
        }
    }

    public void onClickShowLogin(View view) {
        Intent mIntent = new Intent(this, LoginActivity.class);
        startActivity(mIntent);
        finish();
    }

    /**
     * 判断是否是一个合法的email
     *
     * @param email
     * @return
     */
    private boolean isValidEmail(String email) {
        return TextUtils.isEmpty(email) == false && email.matches(Patterns.EMAIL_ADDRESS.pattern());
    }
    private MaterialDialog dialog = null;

    private void showDialog() {
        if (dialog == null)
            dialog = new MaterialDialog.Builder(this)
                    .title("请等待...")
                    .progress(true, 0)
                    .build();
        dialog.show();
    }

    private void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
