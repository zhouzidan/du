package du.zhou.com.du.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import du.zhou.com.du.R;

/**
 * 注册
 * Created by zhou on 2016/12/20.
 */

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onClickSignUp(View view) {

    }

    public void onClickShowLogin(View view){
        Intent mIntent = new Intent(this,LoginActivity.class);
        startActivity(mIntent);
        finish();
    }
}
