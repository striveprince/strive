package com.read.group.base.model.inter;

import android.os.Bundle;

import com.read.group.base.cycle.CycleContainer;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:05
 * modify developer：  admin
 * modify time：14:05
 * modify remark：
 *
 * @version 2.0
 */


public interface Model<T extends CycleContainer> extends Parse {
    void attachView(T t, int model_index);
    T getT();
    void onStarted();
    void onResume();
    void onPause();
    void detachView();
    void onStop();
    void onSaveInstanceState(Bundle outState);
}
