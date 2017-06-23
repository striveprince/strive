package com.read.group.bindingAdapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;


/**
 * Created by maomao on 2017/4/13.
 */

public class TextViewBindingAdapter {
//    @BindingAdapter({"android:type"})
//    public static void setTextStyle(TextView textView, HeadLineItemEntity vm) {
//        textView.setText(vm.getTag());
//        textView.setTextColor(textView.getResources().getColor(R.color.text_gray));
//        textView.setBackgroundResource(R.color.white);
//
//        switch (vm.getType()) {
//            case 1:
//                textView.setTextColor(textView.getResources().getColor(R.color.text_gray));
//                break;
//            case 3:
//                textView.setTextColor(textView.getResources().getColor(R.color.white));
//                textView.setBackgroundResource(R.drawable.shape_headline_light_blue);
//                break;
//            case 5:
//                textView.setTextColor(textView.getResources().getColor(R.color.white));
//                textView.setBackgroundResource(R.drawable.shape_headline_light_blue);
//                break;
//            case 6:
//                textView.setTextColor(textView.getResources().getColor(R.color.text_gray));
//                break;
//            case 7:
//                textView.setTextColor(textView.getResources().getColor(R.color.white));
//                textView.setBackgroundResource(R.drawable.shape_headline_gray);
//                break;
//            case 8:
//                textView.setTextColor(textView.getResources().getColor(R.color.text_gray));
//                break;
//            case 9:
//                textView.setTextColor(textView.getResources().getColor(R.color.text_gray));
//                break;
//        }
//    }
//
//    @BindingAdapter({"android:time"})
//    public static void setTime(TextView textView, HeadLineItemEntity vm) {
//        textView.setText(vm.getTag());
//    }
//
//    @BindingAdapter({"choose_drawable_left"})
//    public static void chooseDrawableLeft(TextView textView, String host) {
//        if (host == null) return;
//        if (host.equals("")) return;
//        Drawable drawable = textView.getResources().getDrawable(host.contains(",") ? R.mipmap.recommend_host_double : R.mipmap.recommend_host_one);
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
//        textView.setCompoundDrawables(drawable, null, null, null);
//        textView.setCompoundDrawablePadding(ScreenUtil.dip2px(textView.getContext(), 1.4f));
//    }

    @BindingAdapter({"set_int_text"})
    public static void setIntText(TextView textView, int nub) {
        textView.setText(String.valueOf(nub));
    }

    @BindingAdapter({"android:drawableTop"})
    public static void setDrawableTop(TextView view, String image) {
        Context mContext = view.getContext();
        Glide.with(mContext).load(image).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                Drawable drawable = new BitmapDrawable(mContext.getResources(),resource);
                setIntrinsicBounds(drawable);
                Drawable[] drawables = view.getCompoundDrawables();
                view.setCompoundDrawables(drawables[0], drawable, drawables[2], drawables[3]);
            }
        });
    }

    private static void setIntrinsicBounds(Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }

}
