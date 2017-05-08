package com.cutv.ningbo.uim.base.cycle;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cutv.ningbo.BR;
import com.cutv.ningbo.DknbApplication;
import com.cutv.ningbo.inject.component.ActivityComponent;
import com.cutv.ningbo.inject.component.DaggerActivityComponent;
import com.cutv.ningbo.inject.module.ActivityModule;
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
 * create time：12:00
 * modify developer：  admin
 * modify time：12:00
 * modify remark：
 *
 * @version 2.0
 */


public abstract class DataBindingActivity<VM extends ViewModel, Binding extends ViewDataBinding> extends AppCompatActivity implements CycleContainer<Binding> {
    @Inject
    public VM vm;
    private Binding binding;
    private Set<Model> set;
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int index = inject(activityComponent());
        ModelView modelView = vm.getModelView();
        if (modelView != null) {
            int[] values = modelView.value();
            if(index>=values.length)index = 0;
            int viewId = values[index];
            if (viewId > 0) {
                binding = DataBindingUtil.setContentView(this, viewId);
                if (modelView.cycle()) {
                    set = addViewSet(binding.getRoot());
                    DknbApplication.getMap().put(viewId,set);
                }
//                binding.setVariable(modelView.name()[index],vm);
                vm.attachView(this,index);
            } else {
                throw new RuntimeException("please use @ModelView at EventModel Item");
            }
        }
    }

    /**
     * @param activityComponent activityComponent
     * @return default return 0 it's mean get the first view layoutId from {@link ModelView}
     */
    public abstract int inject(ActivityComponent activityComponent);


    protected final ActivityComponent activityComponent() {
        if(mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .appComponent(DknbApplication.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return mActivityComponent;
    }


    public Binding getBinding() {
        return binding;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (vm != null) vm.onPause();
        if (set != null) for (Model model : set) model.onPause();
    }

    @Override
    protected void onResume() {
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
    public DataBindingActivity getDataActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vm != null) vm.detachView();
        if (set != null) {
            for (Model model : set) model.detachView();
            set.clear();
        }
    }

}
