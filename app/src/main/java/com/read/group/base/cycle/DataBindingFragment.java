package com.read.group.base.cycle;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.read.group.ui.ReadApplication;
import com.read.group.inject.component.DaggerFragmentComponent;
import com.read.group.inject.component.FragmentComponent;
import com.read.group.inject.module.FragmentModule;
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
 * create time：14:00
 * modify developer：  admin
 * modify time：14:00
 * modify remark：
 *
 * @version 2.0
 */


public abstract class DataBindingFragment<VM extends ViewModel, Binding extends ViewDataBinding> extends Fragment implements CycleContainer<Binding,FragmentComponent> {
    @Inject
    public VM vm;
    private Binding binding;
    private FragmentComponent component;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inject();
        int index = getViewIndex();
        ModelView modelView = vm.getModelView();
        if (modelView != null) {
            int[] values = modelView.value();
            if(index>=values.length)index = 0;
            int viewId = values[index];
            if (viewId > 0) {
                binding = DataBindingUtil.inflate(inflater,viewId,container,false);
                vm.attachView(this,index);
            } else {
                throw new RuntimeException("please use @ModelView at EventModel Item");
            }
            return binding.getRoot();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void inject(){
        try {
            Method method = FragmentComponent.class.getDeclaredMethod("inject", getClass());
            ReflectUtil.invoke(method, getComponent(), this);
        } catch (NoSuchMethodException e) {
            Timber.e("name:"+getClass().getSimpleName()+"need to add @Method inject to ActivityComponent");
        }
    }


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
    public DataBindingActivity getDataActivity() {
        if(getActivity() instanceof DataBindingActivity){
            return (DataBindingActivity)getActivity();
        }
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (vm != null) vm.detachView();
    }


    @Override
    public FragmentComponent getComponent() {
        if(component == null) {
            component = DaggerFragmentComponent.builder()
                    .appComponent(ReadApplication.getAppComponent())
                    .fragmentModule(new FragmentModule(this))
                    .build();
        }
        return component;
    }

    @Override
    public int getViewIndex() {
        return 0;
    }

    @Override
    public ViewModel getVm() {
        return vm;
    }
}
