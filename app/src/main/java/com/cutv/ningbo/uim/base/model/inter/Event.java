package com.cutv.ningbo.uim.base.model.inter;

import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import com.cutv.ningbo.uim.base.annotation.ModelView;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:20
 * modify developer：  admin
 * modify time：15:20
 * modify remark：
 *
 * @version 2.0
 */


public interface Event {
    SparseArray<Event> eventSet = new SparseArray<>();

    void onEvent(View view, MotionEvent event);

    ModelView getModelView();
}