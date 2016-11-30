package com.cutv.ningbo.ui.util.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * 带有删除按钮的EditText
 * Created by JC on 2016-06-30.
 */
public class ClickEditText extends EditText
        implements EditText.OnFocusChangeListener , Handler.Callback{

    public static final String TAG = "ClearableEditText";
    private Handler handler = new Handler(this);

    public ClickEditText(Context context) {
        this(context, null);
    }

    public ClickEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClickEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClickEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private Drawable mClearDrawable;
    public OnDrawableClickListener listener;

    /**
     * Right Drawable 是否可见
     */
    private boolean mIsClearVisible;

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {

        Drawable drawables[] = getCompoundDrawables();
        mClearDrawable = drawables[2]; // Right Drawable;

        final Resources.Theme theme = context.getTheme();

//        TypedArray a = theme.obtainStyledAttributes(attrs, R.styleable.ClearableEditText,
//                defStyleAttr, defStyleRes);
//        int rightDrawableColor = a.getColor(R.styleable.ClearableEditText_right_drawable_color,
//                Color.BLACK);
//        a.recycle();
        // 给mRightDrawable上色
//        DrawableCompat.setTint(mClearDrawable, rightDrawableColor);

        setOnFocusChangeListener(this);

        // 添加TextChangedListener
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged " + s);

                setClearDrawableVisible(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // 第一次隐藏
        setClearDrawableVisible(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // error drawable 不显示 && clear drawable 显示 && action up
        if (getError() == null && mIsClearVisible && event.getAction() == MotionEvent.ACTION_UP) {
            if(listener==null)return super.onTouchEvent(event);
            boolean flag = false;
            float x = event.getX();
            if (x >= getWidth() - getTotalPaddingRight() && x <= getWidth() - getPaddingRight()) {//clearText();
                flag = listener.onDrawableClick(OnDrawableClickListener.RIGHT,getText().toString());
            } else if (x >= getHeight() - getTotalPaddingTop() && x <= getHeight() - getPaddingTop()) {
                flag = listener.onDrawableClick(OnDrawableClickListener.TOP,getText().toString());
            } else if (x >= getWidth() - getTotalPaddingLeft() && x <= getWidth() - getPaddingLeft()) {
                flag = listener.onDrawableClick(OnDrawableClickListener.LEFT,getText().toString());
            } else if (x >= getHeight() - getTotalPaddingBottom() && x <= getHeight() - getPaddingBottom()) {
                flag = listener.onDrawableClick(OnDrawableClickListener.BOTTOM,getText().toString());
            }
            if(flag){
                InputMethodManager manager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(((Activity)getContext()).getWindow().getDecorView().getWindowToken(),0);
                setText("");
                setInputType(0);
                Message msg = Message.obtain();
                msg.what = 1;
                handler.sendMessageAtTime(msg,100);
            }
        }

        return super.onTouchEvent(event);
    }


    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case 1:setInputType(EditorInfo.TYPE_CLASS_TEXT);break;
        }
        return false;
    }

    /**
     * 清空输入框
     */
    private void clearText() {
        if (getText().length() > 0) {
            setText("");
        }
    }


    /**
     * 设置Right Drawable是否可见
     *
     * @param isVisible true for visible , false for invisible
     */
    public void setClearDrawableVisible(boolean isVisible) {

        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                isVisible ? mClearDrawable : null, getCompoundDrawables()[3]);

        mIsClearVisible = isVisible;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.d(TAG, "getTotalPaddingTop = " + getTotalPaddingTop());
        Log.d(TAG, "getExtendedPaddingTop = " + getExtendedPaddingTop());

        // error drawable 不显示的时候
        if (getError() == null) {
            if (hasFocus) {
                if (getText().length() > 0) {
                    setClearDrawableVisible(true);
                }
            } else {
                setClearDrawableVisible(false);
            }
        }
    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        if (error != null) {
            setClearDrawableVisible(true);
        }
        super.setError(error, icon);
    }

    public interface OnDrawableClickListener {
        int TOP = 0;
        int BOTTOM = 1;
        int LEFT = 2;
        int RIGHT = 3;

        boolean onDrawableClick(int location, String text);
    }

    public void setOnDrawableClickListener(OnDrawableClickListener listener) {
        this.listener = listener;
    }

}
