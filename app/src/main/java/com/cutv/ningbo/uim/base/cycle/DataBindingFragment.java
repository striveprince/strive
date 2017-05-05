package com.cutv.ningbo.uim.base.cycle;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.BR;
import com.cutv.ningbo.DknbApplication;
import com.cutv.ningbo.inject.component.DaggerFragmentComponent;
import com.cutv.ningbo.inject.component.FragmentComponent;
import com.cutv.ningbo.inject.module.FragmentModule;
import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.model.ViewModel;
import com.cutv.ningbo.uim.base.model.inter.Model;

import java.util.HashSet;
import java.util.Set;

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
    private Set<Model> set;
    private ModelView modelView;
    private FragmentComponent component;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = inject(fragmentComponent());
        modelView = BaseUtil.findModelView(vm.getClass());
        if (modelView != null) {
            int[] values = modelView.value();
            if(index>=values.length)index = 0;
            int viewId = values[index];
            if (viewId > 0) {
                binding = DataBindingUtil.inflate(inflater,viewId,container,false);
                if (modelView.cycle()) {
                    set = addViewSet(binding.getRoot());
                    DknbApplication.getMap().put(viewId,set);
                }
                binding.setVariable(BR.vm,vm);
                vm.attachView(this);
            } else {
                throw new RuntimeException("please use @ModelView at EventModel Item");
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
        if (set != null) for (Model model : set) model.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (vm != null) vm.onResume();
        if (set != null) for (Model model : set) model.onResume();
    }

    @Override
    public Set<Model> addViewSet(View view) {
        Set<Model> set = new HashSet<>();
        BaseUtil.addViewSet(set,view);
        return set;
    }

    @Override
    public ModelView getModelView() {
        return modelView;
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
        if (set != null) {
            for (Model model : set) model.detachView();
            set.clear();
        }
    }

}
