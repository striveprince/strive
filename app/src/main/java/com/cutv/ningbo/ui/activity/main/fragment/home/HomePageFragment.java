package com.cutv.ningbo.ui.activity.main.fragment.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.R;
import com.cutv.ningbo.data.entity.HomeDataEntity;
import com.cutv.ningbo.data.entity.HomeSlideEntity;
import com.cutv.ningbo.databinding.HeaderHomePageBinding;
import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.inject.scope.FragmentScope;
import com.cutv.ningbo.ui.base.adapter.pager.ViewPagerAdapter;
import com.cutv.ningbo.ui.base.fragment.ListFragment;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.util.rotary.PagerTimeEntity;
import com.cutv.ningbo.ui.util.rotary.TimeUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:28
 * modify developer：  admin
 * modify time：11:28
 * modify remark：
 *
 * @version 2.0
 */

@FragmentScope
public class HomePageFragment
        extends ListFragment<HomeDataEntity, HomeSlideEntity, HomePageViewModel, HomeAdapter>
        implements Respond.TransformRespond<HomeDataEntity, HomeSlideEntity> {
    @Inject
    LayoutInflater inflater;

    @Inject
    @FragmentContext
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public List<HomeSlideEntity> transform(HomeDataEntity homeDataEntity) {
        HeaderHomePageBinding binding = DataBindingUtil.inflate(inflater, R.layout.header_home_page, null, false);
        binding.setVm(new HomePagerHeaderModel(context, homeDataEntity));
        getAdapter().removeAllHeader();
        getAdapter().addHeaderView(binding.getRoot());
        ViewPagerAdapter<HomeSlideEntity> adapter = new ViewPagerAdapter<>();
        PagerTimeEntity<HomeSlideEntity> timeEntity = new PagerTimeEntity<>(homeDataEntity.getSlide(), binding.headerHome.sliderVp, adapter);
//        timeEntity.setLimitLoop(true);
        timeEntity.initData(binding.headerHome.interactTopLinNav,binding);
        TimeUtil.getInstance().add(timeEntity);
        return homeDataEntity.getNews();
    }

}
