package du.zhou.com.du.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import du.zhou.com.du.R;

/**
 * Created by zhou on 2016/12/20.
 */

public class TitleView extends RelativeLayout {

    private ImageView backImageView;
    private TextView titleTextView;
    private TextView rightTextView;

    public TitleView(Context context) {
        this(context, null, 0);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        if (attrs != null)
            initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TitleView);
        //title
        boolean backVisibility = array.getBoolean(R.styleable.TitleView_img_back_visibility, true);
        backImageView.setVisibility(backVisibility ? VISIBLE : GONE);
        boolean titleVisibility = array.getBoolean(R.styleable.TitleView_title_visibility, true);
        titleTextView.setVisibility(titleVisibility ? VISIBLE : GONE);
        String titleText = array.getString(R.styleable.TitleView_title_txt);
        titleTextView.setText(TextUtils.isEmpty(titleText) ? "" : titleText);
        boolean rightTextVisibility = array.getBoolean(R.styleable.TitleView_right_tv_visibility, true);
        rightTextView.setVisibility(rightTextVisibility ? VISIBLE : GONE);
        String rightText = array.getString(R.styleable.TitleView_right_tv_txt);
        rightTextView.setText(TextUtils.isEmpty(rightText) ? "" : rightText);
        array.recycle();
    }


    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_titile, this);
        backImageView = (ImageView) findViewById(R.id.img_back);
        titleTextView = (TextView) findViewById(R.id.tv_title);
        rightTextView = (TextView) findViewById(R.id.tv_right_txt);
        backImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).onBackPressed();
                }
            }
        });
    }

    public void setRightTextViewClickListener(OnClickListener onClickListener) {
        rightTextView.setOnClickListener(onClickListener);
    }

}
