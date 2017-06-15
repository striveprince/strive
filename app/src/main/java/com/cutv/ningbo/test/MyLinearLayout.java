package com.cutv.ningbo.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：19:52
 * modify developer：  admin
 * modify time：19:52
 * modify remark：
 *
 * @version 2.0
 */


public class MyLinearLayout extends LinearLayout{
    public MyLinearLayout(Context context) {
        this(context,null);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Timber.i("MyLinearLayout: dispatchTouchEvent MotionEvent:%1s",Util.getAction(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.i("MyLinearLayout: onTouchEvent MotionEvent:%1s",Util.getAction(event));
        return super.onTouchEvent(event);
    }
}
