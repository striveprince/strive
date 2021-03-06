package com.cutv.ningbo.ui.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cutv.ningbo.BR;
import com.cutv.ningbo.DknbApplication;
import com.cutv.ningbo.inject.component.ActivityComponent;
import com.cutv.ningbo.inject.component.DaggerActivityComponent;
import com.cutv.ningbo.inject.module.ActivityModule;
import com.cutv.ningbo.ui.activity.detail.DetailActivity;
import com.cutv.ningbo.ui.activity.login.LoginActivity;
import com.cutv.ningbo.ui.activity.main.MainActivity;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.base.viewModel.BaseViewModel;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected final ActivityComponent activityComponent() {
        if(mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .appComponent(DknbApplication.getAppComponent())
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
