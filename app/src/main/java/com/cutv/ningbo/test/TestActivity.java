package com.cutv.ningbo.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.cutv.ningbo.R;

import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：19:46
 * modify developer：  admin
 * modify time：19:46
 * modify remark：
 *
 * @version 2.0
 */


public class TestActivity extends AppCompatActivity implements View.OnClickListener ,View.OnTouchListener{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.myLinearLayout).setOnTouchListener(this);
        findViewById(R.id.myLinearLayout).setOnClickListener(this);
        findViewById(R.id.myView).setOnTouchListener(this);
        findViewById(R.id.myView).setOnClickListener(this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.i("TestActivity: onTouchEvent--MotionEvent:%1s", Util.getAction(event));
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean b = super.dispatchTouchEvent(event);
        Timber.i("TestActivity: dispatchTouchEvent--MotionEvent:%1s --return:%1b", Util.getAction(event),b);
        return b;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Timber.i("%1s: onTouch--MotionEvent:%2s",v.getClass().getSimpleName(), Util.getAction(event));
        return false;
    }

    @Override
    public void onClick(View v) {
        Timber.i("%1s: onClick",v.getClass().getSimpleName());
    }
}
