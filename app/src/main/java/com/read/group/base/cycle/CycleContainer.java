package com.read.group.base.cycle;

import android.databinding.ViewDataBinding;

import com.read.group.base.model.ViewModel;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:08
 * modify developer：  admin
 * modify time：14:08
 * modify remark：
 *
 * @version 2.0
 */


public interface CycleContainer<Binding extends ViewDataBinding,T> {
//    Set<Model> addViewSet(View v);
    DataBindingActivity getDataActivity();
    Binding getBinding();
    T getComponent();
    int getViewIndex();
    ViewModel getVm();
}
