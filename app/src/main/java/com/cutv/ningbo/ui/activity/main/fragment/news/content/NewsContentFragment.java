package com.cutv.ningbo.ui.activity.main.fragment.news.content;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.data.entity.HomeSlideEntity;
import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.inject.scope.FragmentScope;
import com.cutv.ningbo.ui.activity.main.fragment.home.HomeAdapter;
import com.cutv.ningbo.ui.base.fragment.ListTransFragment;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:13
 * modify developer：  admin
 * modify time：17:13
 * modify remark：
 *
 * @version 2.0
 */
@FragmentScope
public class NewsContentFragment extends ListTransFragment<HomeSlideEntity, NewsViewModel, HomeAdapter> {

    @Inject
    @FragmentContext Context context;
    @Inject LayoutInflater inflater;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);//use the interface transmit data,and,be careful,use it before super method
        int channelId = getArguments().getInt("channelId");
        viewModel.setListener(() -> channelId);
        return super.onCreateView(inflater,container,savedInstanceState);
    }


}
