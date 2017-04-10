package com.app.ui.base.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.databinding.library.baseAdapters.BR;
import com.app.App;
import com.app.inject.component.ActivityComponent;
import com.app.inject.component.DaggerActivityComponent;
import com.app.inject.module.ActivityModule;
import com.app.ui.base.annotation.AnnotationUtil;
import com.app.ui.base.annotation.ContentView;
import com.app.ui.base.respond.Respond;
import com.app.ui.base.viewModel.BaseViewModel;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:00
 * modify developer：  admin
 * modify time：17:00
 * modify remark：
 * @version 2.0
 */
public abstract class BaseActivity<VM extends BaseViewModel<Respond>,Binding extends ViewDataBinding> extends AppCompatActivity implements Respond{
    private ActivityComponent mActivityComponent;
    public Binding binding;
    @Inject public VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(savedInstanceState);
    }

    public abstract void inject(ActivityComponent component);

    public void inject(@Nullable Bundle savedInstanceState) {
        Class<?> handlerType = viewModel.getClass();
        try {
            ContentView contentView = AnnotationUtil.findContentView(handlerType);
            if (contentView != null) {
                int viewId = contentView.value();
                if (viewId > 0) {
                    inject(activityComponent());
                    binding = DataBindingUtil.setContentView(this,viewId);
                    binding.setVariable(BR.vm, viewModel);
                    viewModel.attachView(this,savedInstanceState);
                }
            }
        } catch (Throwable ex) {}


    }



    private ActivityComponent activityComponent() {
        if(mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .appComponent(App.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return mActivityComponent;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.detachView();
    }
}
