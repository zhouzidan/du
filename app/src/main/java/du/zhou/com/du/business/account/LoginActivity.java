package du.zhou.com.du.business.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.elvishew.xlog.XLog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import du.zhou.com.du.R;
import du.zhou.com.du.model.db.User;

/**
 * 登录
 * Created by zhou on 2016/12/20.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickLogin(View view) {
        TextInputEditText usernameEditText = (TextInputEditText) findViewById(R.id.edit_username);
        TextInputEditText passwordEditText = (TextInputEditText) findViewById(R.id.edit_password);
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        XLog.e(username + password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        showDialog();
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                dismissDialog();
                finish();
            }
        });
    }

    public void onClickShowSignUp(View view) {
        Intent mIntent = new Intent(this, SignUpActivity.class);
        startActivity(mIntent);
        finish();
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
