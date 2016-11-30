package com.cutv.ningbo.ui.base.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cutv.ningbo.ui.base.adapter.RecyclerWrapper;
import com.cutv.ningbo.ui.base.respond.Respond;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:47
 * modify developer：  admin
 * modify time：15:47
 * modify remark：
 *
 * @version 2.0
 */


public abstract class BaseViewModel<RD extends Respond> extends BaseObservable implements ViewModel<RD> {

    private RD rd;

    @Override
    @CallSuper
    public void attachView(RD rd, Bundle savedInstanceState) {
        this.rd = rd;
        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
        }
    }

    @Override
    @CallSuper
    public void detachView() {
        rd = null;
    }

    @BindingAdapter({"android:src"})
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Context context = imageView.getContext();
//        Target<GlideDrawable> target = Glide.with(context).load(url).into(imageView);
//        target.onLoadStarted(ContextCompat.getDrawable(context, R.mipmap.loading));
//        target.onLoadFailed(new Exception("url:" + url), ContextCompat.getDrawable(context, R.mipmap.icon_failure));
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
//                .onLoadStarted(ContextCompat.getDrawable(context, R.mipmap.loading))
    }

    //    private RecyclerWrapper adapter;
//    public RecyclerWrapper getAdapter(){
//        return adapter;
//    }
//    @BindingAdapter({"setAdapter"})
//    public static void setAdapter(RecyclerView view, RecyclerWrapper adapter){
//        view.setAdapter(adapter);
//    }
    public RD getRespond() {
        return rd;
    }

    @Override
    public void saveInstanceState(Bundle outState) {

    }

    @Override
    public void restoreInstanceState(@NonNull Bundle savedInstanceState) {

    }
}
