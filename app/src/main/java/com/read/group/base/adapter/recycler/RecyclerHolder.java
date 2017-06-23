package com.read.group.base.adapter.recycler;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:45
 * modify developer：  admin
 * modify time：14:45
 * modify remark：
 *
 * @version 2.0
 */


public class RecyclerHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;
    private RecyclerHolder(View itemView) {
        super(itemView);
    }

    public RecyclerHolder(ViewDataBinding binding) {
        this(binding.getRoot());
        this.binding = binding;
    }


    public ViewDataBinding getBinding() {
        return binding;
    }

    public static RecyclerView.ViewHolder createRecyclerHolder(View view) {
        return new RecyclerHolder(view);
    }
}
