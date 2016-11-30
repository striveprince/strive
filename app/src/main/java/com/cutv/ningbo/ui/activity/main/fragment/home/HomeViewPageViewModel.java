package com.cutv.ningbo.ui.activity.main.fragment.home;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cutv.ningbo.data.entity.HomeSlideEntity;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:57
 * modify developer：  admin
 * modify time：14:57
 * modify remark：
 *
 * @version 2.0
 */


public class HomeViewPageViewModel extends ImageHeaderViewModel<HomeSlideEntity> {
    public HomeViewPageViewModel(HomeSlideEntity homeSlideEntity) {
        super(homeSlideEntity);
    }


    @Override
    public void onClick(View view, HomeSlideEntity homeSlideEntity) {
        Toast.makeText(view.getContext(), homeSlideEntity.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getImageUrl(HomeSlideEntity homeSlideEntity) {
        return TextUtils.isEmpty(homeSlideEntity.getIndex_img()) ? homeSlideEntity.getImage() : homeSlideEntity.getIndex_img();
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView view, String imageUrl, Drawable error) {
        if(error!=null) Glide.with(view.getContext()).load(imageUrl).error(error).into(view);
        else Glide.with(view.getContext()).load(imageUrl).into(view);
    }
}

