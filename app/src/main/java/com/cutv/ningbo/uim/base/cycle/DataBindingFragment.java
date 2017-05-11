package com.cutv.ningbo.uim.base.cycle;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.DknbApplication;
import com.cutv.ningbo.inject.component.DaggerFragmentComponent;
import com.cutv.ningbo.inject.component.FragmentComponent;
import com.cutv.ningbo.inject.module.FragmentModule;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.model.ViewModel;

import javax.inject.Inject;

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


public abstract class DataBindingFragment<VM extends ViewModel, Binding extends ViewDataBinding> extends Fragment implements CycleContainer {
    @Inject
    public VM vm;
    private Binding binding;
    private FragmentComponent component;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = inject(fragmentComponent());
        ModelView modelView = vm.getModelView();
        if (modelView != null) {
            int[] values = modelView.value();
            if(index>=values.length)index = 0;
            int viewId = values[index];
            if (viewId > 0) {
                binding = DataBindingUtil.inflate(inflater,viewId,container,false);
                vm.attachView(this,index);
            } else {
                throw new RuntimeException("please use @ModelView at EntityModel Item");
            }
            return binding.getRoot();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * @param activityComponent
     * @return default return 0 it's mean
     */
    public abstract int inject(FragmentComponent activityComponent);


    protected final FragmentComponent fragmentComponent() {
        if(component == null) {
            component = DaggerFragmentComponent.builder()
                    .appComponent(DknbApplication.getAppComponent())
                    .fragmentModule(new FragmentModule(this))
                    .build();
        }
        return component;
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
    public DataBindingActivity getDataActivity() {
        if(getActivity() instanceof DataBindingActivity){
            return (DataBindingActivity)getActivity();
        };
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (vm != null) vm.detachView();
    }

}
