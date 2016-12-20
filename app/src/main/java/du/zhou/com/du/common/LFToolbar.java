package du.zhou.com.du.common;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;


/**
 * 通用型Toolbar
 * 实现统一样式，
 * Created by Ken on 16/7/16.
 */
public class LFToolbar extends Toolbar {
    public static final String TAG = LFToolbar.class.getSimpleName();

    private Context context;
    private Button rightBtn;

    //本地广播
    public static final String LB_WORK_STATE_CHANGE = "lb_work_state_change";

    public LFToolbar(Context context) {
        super(context);
        init(context);
    }

    public LFToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setAttributeFromSet(context, attrs);
    }

    public LFToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setAttributeFromSet(context, attrs);
    }

    /**
     * 根据属性值给view赋值
     */
    private void setAttributeFromSet(Context context, @Nullable AttributeSet attrs) {
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LFToolbar);
//
//        CharSequence rightText = typedArray.getText(R.styleable.LFToolbar_tb_right_text);
//        float rightTextSize = typedArray.getDimension(R.styleable.LFToolbar_tb_right_text_size, 18);
//        int rightTextColor = typedArray.getColor(R.styleable.LFToolbar_tb_right_text_color, getResources().getColor(R.color.tb_right_color_default));
//        int rightBackgroundSrc = typedArray.getResourceId(R.styleable.LFToolbar_tb_right_background_src, 0);
//        int rightBackgroundColor = typedArray.getColor(R.styleable.LFToolbar_tb_right_background_color, 0);
//        setRightBtn(rightText, rightTextSize, rightTextColor, rightBackgroundSrc, rightBackgroundColor);
//        typedArray.recycle();
    }

    private void init(final Context context) {
        this.context = context;
        setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof Activity) {
                    ((Activity) context).onBackPressed();
                }
            }
        });
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (context instanceof Activity) {
            ((Activity) context).setTitle(title);
        }
    }

    public void setRightBtn(CharSequence rightText, float rightTextSize, int rightTextColor, int rightBackgroundSrc, int rightBackgroundColor) {
        if ((rightText == null && rightBackgroundSrc <= 0) || rightText == null) {
            Log.d(TAG, "toolbar文本或者图片为空，跳过初始化");
            return;
        }
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT;
        if (rightBtn == null) {
            rightBtn = new Button(context);
            addView(rightBtn);
        }
        rightBtn.setLayoutParams(params);
        rightBtn.setText(rightText);
        rightBtn.setTextColor(getResources().getColor(android.R.color.white));
        rightBtn.setTextSize(rightTextSize);
        rightBtn.setTextColor(rightTextColor);
        if (rightBackgroundColor >= 0) {
            rightBtn.setBackgroundColor(rightBackgroundColor);
        }
        if (rightBackgroundSrc >= 0) {
            rightBtn.setBackgroundResource(rightBackgroundSrc);
        }
        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRightBtnClickListener != null) {
                    mRightBtnClickListener.onClick(v);
                }
            }
        });
    }

    private OnClickListener mRightBtnClickListener;

    public void setRightBtnClickListener(OnClickListener listener) {
        mRightBtnClickListener = listener;
    }

    public Button getRightBtn() {
        return rightBtn;
    }

    public void setRightBtn(Button rightBtn) {
        removeView(this.rightBtn);
        this.rightBtn = rightBtn;
        addView(this.rightBtn);
    }
}
