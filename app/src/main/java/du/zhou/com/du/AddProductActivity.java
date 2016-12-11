package du.zhou.com.du;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by zhou on 2016/12/11.
 */

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {
    EditText contentEditText;
    XRecyclerView recyclerView;
    View backView , submitView ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
    }
    private void initView(){
        backView = findViewById(R.id.img_back);
        submitView = findViewById(R.id.img_submit);
        contentEditText = (EditText) findViewById(R.id.edit_content);
        recyclerView = (XRecyclerView) findViewById(R.id.rv_img);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                break;
            case R.id.img_submit:
                break;
        }
    }

    private void onClickBack(){

    }

    private void onClickSubmit(){
        String content = contentEditText.getText().toString().trim();

    }
}
