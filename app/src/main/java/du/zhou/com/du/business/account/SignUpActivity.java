package du.zhou.com.du.business.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

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
    private MaterialEditText editUsername;
    private MaterialEditText editPassword;
    private MaterialEditText editRepetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editUsername = (MaterialEditText) findViewById(R.id.edit_username);
        editPassword = (MaterialEditText) findViewById(R.id.edit_password);
        editRepetPassword = (MaterialEditText) findViewById(R.id.edit_repet_password);
    }

    public void onClickSignUp(View view) {
        String username = editUsername.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String repetPassword = editRepetPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            editUsername.setError(getString(R.string.error_empty_username));
        } else if (TextUtils.isEmpty(password)) {
            editPassword.setError(getString(R.string.error_empty_password));
        } else if (password.equals(repetPassword)) {
            editRepetPassword.setError(getString(R.string.error_equal_password));
        } else {
            User bu = new User();
            bu.setUsername(username);
            bu.setPassword(password);
            bu.save(new SaveListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    if (e == null) {
                        ToastUtil.show("注册成功");
                        finish();
                    } else {
                        editUsername.setError(e.getMessage());
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
}
