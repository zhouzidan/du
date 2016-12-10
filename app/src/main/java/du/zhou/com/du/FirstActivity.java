package du.zhou.com.du;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by zhou on 2016/12/9.
 */

public class FirstActivity extends AppCompatActivity {
    TextView textView;
    private int count = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ImageView imageView = (ImageView) findViewById(R.id.img);
        Glide.with(this).load("http://img.hb.aicdn.com/2e992241a0f96f8c866a7466b813a952b27c3ead4f340-tTh5rs_fw658").into(imageView);
        textView = (TextView) findViewById(R.id.text);
        textView.setText(count + "秒后关闭");
        mHandler.sendEmptyMessageDelayed(0,1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            count--;
            textView.setText(count + "秒后关闭");
            if (count > 0) {
                mHandler.sendEmptyMessageDelayed(0, 1000);
            } else {
                startActivity(new Intent(FirstActivity.this, SlideActivity.class));
                finish();
            }

        }
    };
}
