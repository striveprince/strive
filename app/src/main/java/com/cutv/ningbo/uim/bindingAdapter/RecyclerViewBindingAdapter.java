package com.cutv.ningbo.uim.bindingAdapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.cutv.ningbo.uim.base.adapter.IModelAdapter;
import com.cutv.ningbo.uim.base.adapter.IRecyclerAdapter;


/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:41
 * modify developer：  admin
 * modify time：11:41
 * modify remark：
 *
 * @version 2.0
 */


public class RecyclerViewBindingAdapter {

    @BindingAdapter({"adapter"})
    public static void setAdapter(RecyclerView view, IModelAdapter adapter) {
        if(adapter!=null && adapter instanceof RecyclerView.Adapter){
            view.setAdapter((RecyclerView.Adapter)adapter);
        }
    }

    @BindingAdapter({"item_anim"})
    public static void setItemAnimator(RecyclerView view, DefaultItemAnimator animator) {
        if(animator!=null)view.setItemAnimator(animator);
    }


    @BindingAdapter({"scroll_listener"})
    public static void setOnScroll(RecyclerView view, RecyclerView.OnScrollListener listener) {
        if(listener!=null)view.addOnScrollListener(listener);
    }

    @BindingAdapter({"layout_manager"})
    public static void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager layoutManager){
        if(layoutManager!=null)view.setLayoutManager(layoutManager);
    }
}
