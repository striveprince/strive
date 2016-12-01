package com.cutv.ningbo.ui.activity.main.fragment;

import android.content.Context;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.cutv.ningbo.data.entity.HomeSlideEntity;
import com.cutv.ningbo.inject.qualifier.context.HolderContext;
import com.cutv.ningbo.ui.base.viewModel.HolderViewModel;
import com.cutv.ningbo.ui.base.respond.Respond;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:26
 * modify developer：  admin
 * modify time：17:26
 * modify remark：
 *
 * @version 2.0
 */

public class NewsHolderViewModel extends HolderViewModel<Respond,HomeSlideEntity> {
    private HomeSlideEntity entity;

    @Inject
    NewsHolderViewModel(@HolderContext Context context) {
        super(context);
    }

    @Override
    public void saveInstanceState(Bundle outState) {

    }

    @Override
    public void restoreInstanceState(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void bind(HomeSlideEntity entity, int position) {
        this.entity = entity;
    }

    @Bindable
    public boolean isSingleImage(){
        if(entity==null||entity.getImage()==null)return true;
        String[] images = entity.getImage().split("\\|");
        int len = images.length;
        return len!=3;
    }
}
