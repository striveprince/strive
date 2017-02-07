package com.app.ui.base.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.BR;
import com.app.App;
import com.app.inject.component.DaggerFragmentComponent;
import com.app.inject.component.FragmentComponent;
import com.app.inject.module.FragmentModule;
import com.app.inject.qualifier.context.FragmentContext;
import com.app.ui.base.respond.Respond;
import com.app.ui.base.viewModel.ViewModel;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:56
 * modify developer：  admin
 * modify tiodify remark：
 *
 * @version 2.0
 */

public class BaseFragment<VM extends ViewModel, Binding extends ViewDataBinding> extends Fragment implements Respond {
    private FragmentComponent component;
    @Inject
    public VM viewModel;
    protected Binding binding;
    @Inject
    @FragmentContext
    Context context;

    protected final FragmentComponent fragmentComponent() {
        if (component == null) {
            component = DaggerFragmentComponent.builder()
                    .appComponent(App.getAppComponent())
                    .fragmentModule(new FragmentModule(this))
                    .build();
        }
        return component;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isInit = true;
//        viewModel.onHttpTouch();
    }

    protected final View setAndBindContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @LayoutRes int layoutResId, Bundle savedInstanceState) {
        if (viewModel == null) throw new IllegalStateException("viewModel must not be null and should be injected via fragmentComponent().inject(this)");
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false);
        binding.setVariable(BR.vm, viewModel);
        viewModel.attachView(this, savedInstanceState);
        return binding.getRoot();
    }


    @Override
    @CallSuper
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (viewModel != null) {
            viewModel.saveInstanceState(outState);
        }
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        super.onDestroyView();
        if (viewModel != null) {
            viewModel.detachView();
        }
        binding = null;
        viewModel = null;
    }

    @Override
    @CallSuper
    public void onDestroy() {
        component = null;
        super.onDestroy();
        isInit = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        resume();
        if(isInit){
            init();
        }
        isInit = false;
    }
    private boolean isInit = true;

    public void init(){}

    protected void resume() {
    }
}
