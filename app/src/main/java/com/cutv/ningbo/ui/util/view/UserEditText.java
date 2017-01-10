package com.cutv.ningbo.ui.util.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.cutv.ningbo.R;


/**
 * 带有删除按钮的EditText
 * Created by JC on 2016-06-30.
 */
public class UserEditText extends EditText
        implements EditText.OnFocusChangeListener, Handler.Callback {


    public static final String TAG = "ClearableEditText";
    private Handler handler = new Handler(this);
    private int mI;
    private Drawable mRightrawable;

    public UserEditText(Context context) {
        this(context, null);
    }

    public UserEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public UserEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UserEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private Drawable mClearDrawable;
    public OnDrawableClickListener onDrawClickListener = new OnDrawableClickListener() {
        @Override
        public boolean onDrawableClick(int location, String text) {
            return true;
        }
    };
    public OnKeyActionListener onKeyActionListener;


    public void setOnKeyActionListener(OnKeyActionListener onKeyActionListener) {
        this.onKeyActionListener = onKeyActionListener;
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        //软键盘对应的点击事件

        setOnKeyListener((v, keyCode, event) -> {

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                Toast.makeText(getContext(), "Action send", Toast.LENGTH_SHORT).show();
                mI = 1;
                mI++;
                Log.i("test1", String.valueOf(mI));
                if (onKeyActionListener != null && onKeyActionListener.onSend(getText().toString())) {


                }

                return false;
            }


            return false;
        });
        Drawable drawables[] = getCompoundDrawables();
        mClearDrawable = drawables[2]; // Right Drawable;

        final Resources.Theme theme = context.getTheme();
        setOnFocusChangeListener(this);

        // 添加TextChangedListener
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged " + s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // error drawable 不显示 && clear drawable 显示 && action up
        if (getError() == null && event.getAction() == MotionEvent.ACTION_UP) {
            if (onDrawClickListener == null) return super.onTouchEvent(event);
            boolean flag = false;
            float x = event.getX();
            if (x >= getWidth() - getTotalPaddingRight() && x <= getWidth() - getPaddingRight()) {//clearText();
                flag = onDrawClickListener.onDrawableClick(OnDrawableClickListener.RIGHT, getText().toString());

                if (this.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    System.out.println("checked");
                    mRightrawable = ContextCompat.getDrawable(getContext(), R.mipmap.user_edit_hide);
                    this.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else if (this.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    System.out.println("not checked");
                    mRightrawable = ContextCompat.getDrawable(getContext(),R.mipmap.user_edit_show);
                    this.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

                mRightrawable.setBounds(0, 0, mRightrawable.getMinimumWidth(), mRightrawable.getMinimumHeight()); //设置边界
                this.setCompoundDrawables(null, null, mRightrawable, null);//画在右边

            } else if (x >= getHeight() - getTotalPaddingTop() && x <= getHeight() - getPaddingTop()) {
                flag = onDrawClickListener.onDrawableClick(OnDrawableClickListener.TOP, getText().toString());
            } else if (x >= getWidth() - getTotalPaddingLeft() && x <= getWidth() - getPaddingLeft()) {
                flag = onDrawClickListener.onDrawableClick(OnDrawableClickListener.LEFT, getText().toString());
            } else if (x >= getHeight() - getTotalPaddingBottom() && x <= getHeight() - getPaddingBottom()) {
                flag = onDrawClickListener.onDrawableClick(OnDrawableClickListener.BOTTOM, getText().toString());
            }

        }

        return super.onTouchEvent(event);
    }


    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                setInputType(EditorInfo.TYPE_CLASS_TEXT);
                break;
        }
        return false;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.d(TAG, "getTotalPaddingTop = " + getTotalPaddingTop());
        Log.d(TAG, "getExtendedPaddingTop = " + getExtendedPaddingTop());


    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        if (error != null) {

        }
        super.setError(error, icon);
    }

    public interface OnDrawableClickListener {
        int TOP = 0;
        int BOTTOM = 1;
        int LEFT = 2;
        int RIGHT = 3;
        int Action = 4;

        boolean onDrawableClick(int location, String text);
    }

    public interface OnKeyActionListener {
        boolean onSend(String text);
    }



}
