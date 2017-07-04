package com.read.group.base.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


/**
 * 带有删除按钮的EditText
 * Created by JC on 2016-06-30.
 */
public class ClickEditText extends EditText
        implements EditText.OnFocusChangeListener, Handler.Callback {
    public static final String TAG = "ClearableEditText";
    private Handler handler = new Handler(this);

    public int inputType = 0;
//    private int mI;
//    private ClickEditText syncEditText;
//    private int color = 0;


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
    public OnDrawableClickListener onDrawClickListener;
    private onTextChangeListener onTextChangeListener;
    /**
     * Right Drawable 是否可见
     */
    private boolean mIsClearVisible;

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        //软键盘对应的点击事件
        setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER&&onDrawClickListener != null&&event.getAction()==KeyEvent.ACTION_UP
                    &&onDrawClickListener.onDrawableClick(this,OnDrawableClickListener.ACTION, getText().toString())) {
                setInput();
            }
            return false;
        });
        Drawable drawables[] = getCompoundDrawables();
        mClearDrawable = drawables[2]; // Right Drawable;
//        final Resources.Theme theme = context.getTheme();
//        TypedArray a = theme.obtainStyledAttributes(attrs, R.styleable.ClearableEditText,
//                defStyleAttr, defStyleRes);
//        int rightDrawableColor = a.getColor(R.styleable.ClearableEditText_right_drawable_color,
//                Color.BLACK);
//        a.recycle();
        // 给mRightDrawable上色
//        DrawableCompat.setTint(mClearDrawable, rightDrawableColor);
        setOnFocusChangeListener(this);

        addTextChangedListener(new TextWatcher() { // 添加TextChangedListener
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {setClearDrawableVisible(s);}
            @Override public void afterTextChanged(Editable s) {}
        });
        setClearDrawableVisible("");  // 第一次隐藏
    }

//    public void setSyncEditText(ClickEditText syncEditText) {
//        this.syncEditText = syncEditText;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // error drawable 不显示 && clear drawable 显示 && action up
        if (getError() == null && mIsClearVisible && event.getAction() == MotionEvent.ACTION_UP) {
            if (onDrawClickListener == null) return super.onTouchEvent(event);
            boolean flag = false;
            float x = event.getX();
            if (x >= getWidth() - getTotalPaddingRight() && x <= getWidth() - getPaddingRight()) {//clearText();
                flag = onDrawClickListener.onDrawableClick(this,OnDrawableClickListener.RIGHT, getText().toString());
            } else if (x >= getHeight() - getTotalPaddingTop() && x <= getHeight() - getPaddingTop()) {
                flag = onDrawClickListener.onDrawableClick(this,OnDrawableClickListener.TOP, getText().toString());
            } else if (x >= getWidth() - getTotalPaddingLeft() && x <= getWidth() - getPaddingLeft()) {
                flag = onDrawClickListener.onDrawableClick(this,OnDrawableClickListener.LEFT, getText().toString());
            } else if (x >= getHeight() - getTotalPaddingBottom() && x <= getHeight() - getPaddingBottom()) {
                flag = onDrawClickListener.onDrawableClick(this,OnDrawableClickListener.BOTTOM, getText().toString());
            }
            if (flag) { //点击发送的时候不在弹出
                setInput();
            }
        }
        return super.onTouchEvent(event);
    }

    public void setInput(){
        inputType = getInputType();
        Message msg = Message.obtain();
        msg.what = 1;
        InputMethodManager manager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(((Activity) getContext()).getWindow().getDecorView().getWindowToken(), 0);
        setInputType(0);
        setText("");
        handler.sendMessageAtTime(msg, 100);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                setInputType(inputType);
                inputType = 0;
                break;
        }
        return false;
    }

    /**
     * 清空输入框
     */
    public void clearText() {
        if (getText().length() > 0) {
            setText("");
        }
    }


    /**
     * 设置Right Drawable是否可见
     *
     * @param sequence true for visible , false for invisible
     */
    public void setClearDrawableVisible(CharSequence sequence) {
//        if(syncEditText!=null)syncEditText.setText(sequence);
        boolean isVisible = sequence.length() > 0;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                isVisible ? mClearDrawable : null, getCompoundDrawables()[3]);
        if (onTextChangeListener != null) onTextChangeListener.onTextChange(isVisible);
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
                    setClearDrawableVisible(getText());
                }
            } else {
                setClearDrawableVisible("");
            }
        }
    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        if (error != null) {
            setClearDrawableVisible(error);
        }
        super.setError(error, icon);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        Log.i("ClickEditText", " change width:" + width + "\theight:" + height + "\toldWidth:" + oldWidth + "\toldHeight:" + oldHeight);
    }

    public interface OnDrawableClickListener {
        int TOP = 0;
        int BOTTOM = 1;
        int LEFT = 2;
        int RIGHT = 3;
        int ACTION = 4;

        boolean onDrawableClick(View v, int location, String text);
    }

    public void setOnDrawableClickListener(OnDrawableClickListener listener) {
        this.onDrawClickListener = listener;
    }

    public void setOnTextChangeListener(ClickEditText.onTextChangeListener onTextChangeListener) {
        this.onTextChangeListener = onTextChangeListener;

    }

    public interface onTextChangeListener {
        void onTextChange(boolean isVisible);
    }

    @Override
    public void setVisibility(int visibility) {
        setText(getText());
        super.setVisibility(visibility);
    }
}
