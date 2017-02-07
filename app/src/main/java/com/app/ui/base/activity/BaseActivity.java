package com.app.ui.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.BR;
import com.app.App;
import com.app.inject.component.ActivityComponent;
import com.app.inject.component.DaggerActivityComponent;
import com.app.inject.module.ActivityModule;
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
public class BaseActivity<VM extends BaseViewModel,Binding extends ViewDataBinding> extends AppCompatActivity implements Respond{
    private ActivityComponent mActivityComponent;
    public Binding binding;
    @Inject public VM viewModel;

    protected final ActivityComponent activityComponent() {
        if(mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .appComponent(App.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return mActivityComponent;
    }

    protected final void setBindingView(@LayoutRes int layoutId, @Nullable Bundle savedInstanceState){
        binding = DataBindingUtil.setContentView(this,layoutId);
        binding.setVariable(BR.vm, viewModel);
        viewModel.attachView(this,savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.detachView();
    }
}
