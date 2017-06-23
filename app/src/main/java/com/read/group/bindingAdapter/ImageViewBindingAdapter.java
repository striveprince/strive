package com.read.group.bindingAdapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
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



    @BindingAdapter({"image"})
    public static void setImage(ImageView imageView,String image){
        setImage(imageView,image,0,0);
    }

    @BindingAdapter({"image","radius"})
    public static void setImage(ImageView imageView,String image,int radius){
        setImage(imageView,image,6,radius);
    }

    @BindingAdapter({"image_circle"})
    public static void setImageCircle(ImageView imageView,String image){
        setImage(imageView,image,2,0);
    }

    public static void setImage(ImageView imageView,String image,int type,int radius){
//        switch (type){
//            case 0:ImageUtil.defaultBind(imageView,image);break;
//            case 1:ImageUtil.insideBind(imageView,image);break;
//            case 2:ImageUtil.circleBind(imageView,image);break;
//            case 3:ImageUtil.circleInBind(imageView,image);break;
//            case 4:ImageUtil.centerBind(imageView,image);break;
//            case 5:ImageUtil.circleShortBind(imageView,image,radius);break;
//            case 6:ImageUtil.circleRadius(imageView,image,radius);break;
////            case 6:ImageUtil.c(imageView,image,size);break;
//        }
    }


    @BindingAdapter({"type_background"})
    public static void setTypeBackground(ImageView imageView, int type) {
//        if (type == 8)
//            imageView.setBackgroundResource(R.mipmap.headline_video);
//        else if (type == 9)
//            imageView.setBackgroundResource(R.mipmap.headline_audio);

    }
    @BindingAdapter("android:src")
    public static void setImage(ImageView view, @DrawableRes int mipmapId){
        if(mipmapId!=0)view.setImageResource(mipmapId);
    }
}
