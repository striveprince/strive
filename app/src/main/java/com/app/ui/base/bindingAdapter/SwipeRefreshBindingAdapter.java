package com.app.ui.base.bindingAdapter;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.databinding.adapters.ListenerUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.app.R;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:25
 * modify developer：  admin
 * modify time：14:25
 * modify remark：
 *
 * @version 2.0
 */

@InverseBindingMethods({
        @InverseBindingMethod(type = SwipeRefreshLayout.class, attribute = "refreshing", event = "refreshingAttrChanged", method = "isRefreshing")
})
public class SwipeRefreshBindingAdapter  {


    @BindingAdapter({"refreshing"})
    public static void setRefreshing(SwipeRefreshLayout view, boolean refreshing) {
        if (refreshing != view.isRefreshing()) {
            view.setRefreshing(refreshing);
        }
    }

    @BindingAdapter(value = {"onRefreshListener", "refreshingAttrChanged"}, requireAll = false)
    public static void setOnRefreshListener(SwipeRefreshLayout view,SwipeRefreshLayout.OnRefreshListener listener,InverseBindingListener refreshingAttrChanged) {
        SwipeRefreshLayout.OnRefreshListener newValue = () -> {
            if (refreshingAttrChanged != null) refreshingAttrChanged.onChange();
            if (listener != null) listener.onRefresh();
        };
        SwipeRefreshLayout.OnRefreshListener oldValue = ListenerUtil.trackListener(view, newValue, R.id.swipe_refresh_layout);
        if (oldValue != null) view.setOnRefreshListener(null);
        view.setOnRefreshListener(newValue);
        view.setColorSchemeResources(
                R.color.swipe_color1,
                R.color.swipe_color2,
                R.color.swipe_color3,
                R.color.swipe_color4);
    }

    @BindingAdapter({"scrollListener"})
    public static void setOnScroll(RecyclerView view, RecyclerView.OnScrollListener listener) {
        view.addOnScrollListener(listener);
    }


//@InverseBindingMethods({
//    @InverseBindingMethod(
//            type = SwipeRefreshLayout.class,
//            attribute = "refreshing",
//            event = "refreshingAttrChanged",
//            method = "isRefreshing")
//})
//    public boolean isEmpty(){
//        return adapter.getRealItemCount() == 0;
//    }
//    @BindingAdapter({"refreshing"})
//    public static void setRefreshing(SwipeRefreshLayout view, boolean refreshing) {
//        if (refreshing != view.isRefreshing()) {
//            view.setRefreshing(refreshing);
//        }
//    }
//
//    @BindingAdapter(value = {"onRefreshListener", "refreshingAttrChanged"}, requireAll = false)
//    public static void setOnRefreshListener(SwipeRefreshLayout view,SwipeRefreshLayout.OnRefreshListener listener,InverseBindingListener refreshingAttrChanged) {
//        SwipeRefreshLayout.OnRefreshListener newValue = () -> {
//            if (refreshingAttrChanged != null) refreshingAttrChanged.onChange();
//            if (listener != null) listener.onRefresh();
//        };
//        SwipeRefreshLayout.OnRefreshListener oldValue = ListenerUtil.trackListener(view, newValue, R.id.swipe_refresh_layout);
//        if (oldValue != null) view.setOnRefreshListener(null);
//        view.setOnRefreshListener(newValue);
//        view.setColorSchemeResources(
//                R.color.swipe_color1,
//                R.color.swipe_color2,
//                R.color.swipe_color3,
//                R.color.swipe_color4);
//    }
}
