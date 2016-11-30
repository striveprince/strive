package com.cutv.ningbo.ui.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.R;
import com.cutv.ningbo.data.entity.BaseEntity;
import com.cutv.ningbo.databinding.ViewRecyclerViewBinding;
import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.ui.base.adapter.RecyclerWrapper;
import com.cutv.ningbo.ui.base.recycler.RecyclerBindViewModel;
import com.cutv.ningbo.ui.base.respond.Respond;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:51
 * modify developer：  admin
 * modify time：13:51
 * modify remark：
 *
 * @version 2.0
 */
public abstract class ListFragment<
        T,
        Entity extends BaseEntity,
        VM extends RecyclerBindViewModel<T, Entity, Adapter>,
        Adapter extends RecyclerWrapper<Entity, ?>>
        extends BaseFragment<VM, ViewRecyclerViewBinding>
        implements Respond.RecyclerRespond<T> {


    @Inject
    @FragmentContext
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return setAndBindContentView(inflater, container, R.layout.view_recycler_view, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.onHttp(0);
    }

    @Override
    @CallSuper
    public void onCompleted(Throwable e, T t, int count) {
        if (e != null) {
            if(getErrorView()!=null){}
        } else if (count == 0) {
            if(getNoDataView()!=null)getAdapter().addFooterView(getNoDataView());
        }
    }

    public View getNoDataView(){
        return null;
    }

    public View getErrorView(){
        return null;
    }


    public Adapter getAdapter() {
        return viewModel.getAdapter();
    }
}


