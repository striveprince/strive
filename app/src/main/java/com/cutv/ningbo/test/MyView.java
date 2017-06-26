package com.cutv.ningbo.test;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：19:48
 * modify developer：  admin
 * modify time：19:48
 * modify remark：
 *
 * @version 2.0
 */


public class MyView extends View  {

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean b = super.dispatchTouchEvent(event);
        Timber.i("MyView: dispatchTouchEvent MotionEvent:%1s",Util.getAction(event));
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.i("MyView: onTouchEvent MotionEvent:%1s",Util.getAction(event));
        return super.onTouchEvent(event);
    }


}
