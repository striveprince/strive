package com.cutv.ningbo.uim.base.model;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.view.View;

import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.cycle.CycleContainer;
import com.cutv.ningbo.uim.base.cycle.DataBindingActivity;
import com.cutv.ningbo.uim.base.model.inter.EntityModel;
import com.cutv.ningbo.uim.base.model.inter.Model;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:58
 * modify developer：  admin
 * modify time：15:58
 * modify remark：
 * it's use to bind activity fragment or layout
 *
 * @version 2.0
 */

public class ViewModel<T extends CycleContainer> extends ViewEntity implements EntityModel<T> {
    private transient WeakReference<T> weakReference;
    private transient Set<Model> set;

    @CallSuper
    @Override
    public void attachView(T t, int model_index) {
        weakReference = new WeakReference<>(t);
        t.getBinding().setVariable(getModelView().name()[model_index], this);
        registerEvent();
        set = addViewSet(t.getBinding().getRoot());
    }

    @Override
    public T getT() {
        T t = null;
        if (weakReference != null) t = weakReference.get();
        if (t == null)
            Timber.e(weakReference == null ? "weakReference ==null" : "cycleContainer object == null");
        return t;
    }

    @CallSuper
    @Override
    public void onResume() {
        if (set != null) for (Model model : set) model.onResume();
    }

    @CallSuper
    @Override
    public void onPause() {
        if (set != null) for (Model model : set) model.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (set != null) for (Model model : set) model.onSaveInstanceState(outState);
    }

    @CallSuper
    @Override
    public void onStop() {
        if (set != null) for (Model model : set) model.onStop();
    }

    @CallSuper
    @Override
    public void onStarted() {
        if (set != null) for (Model model : set) model.onStarted();
    }

    @CallSuper
    @Override
    public void detachView() {
        if (getT() != null) {
            unRegisterEvent();
            if (set != null) {
                for (Model model : set) model.detachView();
                set.clear();
            }
            weakReference.clear();
        }
    }

    public DataBindingActivity getDataActivity() {
        if (getT() != null)
            return getT().getDataActivity();
        return null;
    }


    public ViewDataBinding getBinding() {
        if (getT() != null)
            return getT().getBinding();
        return null;
    }

    private Set<Model> addViewSet(View view) {
        Set<Model> set = new HashSet<>();
        BaseUtil.addViewSet(set, view);
        return set.size() > 0 ? set : null;
    }
}
