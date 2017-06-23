package com.read.group.bindingAdapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:34
 * modify developer：  admin
 * modify time：14:34
 * modify remark：
 *
 * @version 2.0
 */
public class ViewBindingAdapter {
    @BindingAdapter("android:slide_list")
    public static void setVisibility(View view, int visibility) {
        if(visibility!=view.getVisibility()){
            view.setVisibility(visibility);
        }
    }
    @BindingAdapter("params")
    public static void setLayoutParams(View view, ViewGroup.LayoutParams params){
        if(params!=null){
            view.setLayoutParams(params);
        }
    }

    @BindingAdapter({"android:background"})
    public static void setBackground(View view, String imageUrl) {
        Context mContext = view.getContext();
        Glide.with(mContext).load(imageUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(mContext.getResources(),resource);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.setBackground(drawable);
                }else{
                    view.setBackgroundDrawable(drawable);
                }
            }
        });
    }
}
