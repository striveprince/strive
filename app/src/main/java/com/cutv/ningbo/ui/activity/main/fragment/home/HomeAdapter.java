package com.cutv.ningbo.ui.activity.main.fragment.home;

import android.view.ViewGroup;

import com.cutv.ningbo.data.entity.HomeSlideEntity;
import com.cutv.ningbo.ui.base.adapter.RecyclerWrapper;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:42
 * modify developer：  admin
 * modify time：9:42
 * modify remark：
 *
 * @version 2.0
 */


public class HomeAdapter extends RecyclerWrapper<HomeSlideEntity, HomeHolder> {
    @Inject
    public HomeAdapter() {}
    @Override
    public HomeHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new HomeHolder(parent.getContext());
    }
}
