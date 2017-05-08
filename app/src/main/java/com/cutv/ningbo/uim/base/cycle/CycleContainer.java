package com.cutv.ningbo.uim.base.cycle;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.cutv.ningbo.data.entity.InfoEntity;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.model.inter.Model;

import java.util.Set;

import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:08
 * modify developer：  admin
 * modify time：14:08
 * modify remark：
 *
 * @version 2.0
 */


public interface CycleContainer<Binding extends ViewDataBinding> {
    Set<Model> addViewSet(View v);
    DataBindingActivity getDataActivity();
    Binding getBinding();
}
