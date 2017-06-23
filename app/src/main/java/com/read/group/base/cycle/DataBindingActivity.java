package com.read.group.base.cycle;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.read.group.ReadApplication;
import com.read.group.inject.component.DaggerActivityComponent;
import com.read.group.inject.component.ActivityComponent;
import com.read.group.inject.module.ActivityModule;
import com.read.group.base.util.ReflectUtil;
import com.read.group.base.annotation.ModelView;
import com.read.group.base.model.ViewModel;

import java.lang.reflect.Method;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：12:00
 * modify developer：  admin
 * modify time：12:00
 * modify remark：
 *
 * @version 2.0
 */


public abstract class DataBindingActivity<VM extends ViewModel, Binding extends ViewDataBinding> extends AppCompatActivity implements CycleContainer<Binding, ActivityComponent> {
    @Inject
    public VM vm;
    private Binding binding;
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        ModelView modelView = vm.getModelView();
        if (modelView != null) {
            int[] values = modelView.value();
            int index = getViewIndex();
            if (index >= values.length) index = 0;
            int viewId = values[index];
            if (viewId > 0) {
                binding = DataBindingUtil.setContentView(this, viewId);
                vm.attachView(this, index);
            } else {
                throw new RuntimeException("please use @ModelView at Model Item");
            }
        }
    }

    public void inject(){
        try {
            Method method = ActivityComponent.class.getDeclaredMethod("inject", getClass());
            ReflectUtil.invoke(method, getComponent(), this);
        } catch (NoSuchMethodException e) {
            Timber.e("name:%1s need to add @Method inject to ActivityComponent",getClass().getSimpleName());
        }
    }

    @Override
    public Binding getBinding() {
        return binding;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (vm != null) vm.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (vm != null) vm.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (vm != null) vm.onStop();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (vm != null) vm.onSaveInstanceState(outState);
    }

    @Override
    public void onStart(){
        super.onStart();
        if (vm != null) vm.onStarted();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (vm != null) vm.detachView();
    }

    @Override
    public ActivityComponent getComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .appComponent(ReadApplication.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    public int getViewIndex() {
        return 0;
    }

    @Override
    public ViewModel getVm() {
        return vm;
    }

    @Override
    public DataBindingActivity getDataActivity() {
        return this;
    }
}
