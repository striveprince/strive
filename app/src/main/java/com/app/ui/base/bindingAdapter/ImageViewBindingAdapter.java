package com.app.ui.base.bindingAdapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:45
 * modify developer：  admin
 * modify time：16:45
 * modify remark：
 *
 * @version 2.0
 */


public class ImageViewBindingAdapter {
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

}
