package com.app.ui.base.bindingAdapter;

import android.databinding.BindingAdapter;

import com.app.data.entity.BaseEntity;
import com.app.ui.base.recycler.ExpandRecyclerView;
import com.app.ui.base.viewModel.RecyclerModel;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:46
 * modify developer：  admin
 * modify time：16:46
 * modify remark：
 *
 * @version 2.0
 */


public class ExpandRecyclerViewBindingAdapter {
    @BindingAdapter({"start"})
    public static <T,Entity extends BaseEntity>void setStart(ExpandRecyclerView<T, Entity> recyclerView, RecyclerModel<T,Entity> vm){
        recyclerView.setRespond(vm);
        recyclerView.setObservable(vm.httpInit()).start();
    }
}
