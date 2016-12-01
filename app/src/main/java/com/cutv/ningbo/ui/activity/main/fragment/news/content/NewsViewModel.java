package com.cutv.ningbo.ui.activity.main.fragment.news.content;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.R;
import com.cutv.ningbo.data.api.NbtvApi;
import com.cutv.ningbo.data.entity.HomeSlideEntity;
import com.cutv.ningbo.data.entity.InfoEntity;
import com.cutv.ningbo.data.http.RestfulSubscriber;
import com.cutv.ningbo.data.http.RestfulTransformer;
import com.cutv.ningbo.databinding.HeaderSliderPagerBinding;
import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.ui.activity.main.fragment.home.HomeAdapter;
import com.cutv.ningbo.ui.activity.main.fragment.home.HomeViewPageViewModel;
import com.cutv.ningbo.ui.base.recycler.RecyclerBindViewModel;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.util.rotary.TimeUtil;
import com.cutv.ningbo.ui.util.rotary.ViewPagerTimeEntity;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:18
 * modify developer：  admin
 * modify time：17:18
 * modify remark：
 *
 * @version 2.0
 */


public class NewsViewModel extends RecyclerBindViewModel<List<HomeSlideEntity>,HomeSlideEntity,HomeAdapter> {
    private NbtvApi api;
    private InfoListener listener;
    private HeaderSliderPagerBinding headerBinding;
    public void setListener(InfoListener listener) {
        this.listener = listener;
    }

    @Inject
    DisplayMetrics dm;
    @Inject
    LayoutInflater inflater;
    @Inject
    public NewsViewModel(@FragmentContext Context context, NbtvApi api) {
        super(context);
        this.api = api;
    }

    @Override
    public Observable<InfoEntity<List<HomeSlideEntity>>> httpApi(int offset) {
        return listener==null?null:api.getContentNews(listener.getChannelId(),20,0);
    }

    public interface InfoListener {
        int getChannelId();
    }

    @Override
    public RecyclerView.LayoutManager getManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    public void attachView(Respond.TransformRespond<List<HomeSlideEntity>, HomeSlideEntity> listHomeSlideEntityTransformRespond, Bundle savedInstanceState) {
        super.attachView(listHomeSlideEntityTransformRespond, savedInstanceState);
        if(listener==null)return;
        if(listener.getChannelId() == 11672){
            View pagerView = inflater.inflate(R.layout.header_slider_pager,null);
            pagerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,dm.widthPixels*9/16));
            headerBinding = DataBindingUtil.bind(pagerView);
            getAdapter().addHeaderView(headerBinding.getRoot());
            RestfulSubscriber<List<HomeSlideEntity>> subscriber = new RestfulSubscriber<>(getContext(),entities -> {
                ViewPagerTimeEntity<HomeSlideEntity> timeEntity = new ViewPagerTimeEntity<>(entities,  headerBinding.vpNewsFigure);
                timeEntity.setInjectImageListener((binding1, t) -> {
                    binding1.setVm(new HomeViewPageViewModel(getContext(),t));
                    binding1.executePendingBindings();
                }).addCarouselListener((homeSlideEntity, view1) -> headerBinding.setSlide(homeSlideEntity));
                timeEntity.init(headerBinding.interactTopLinNav);
                TimeUtil.getInstance().start(timeEntity);
            },Timber::e,(t, e) -> {});
            compositeSubscription.add(api.getContentNews(11684,3,0)
                    .compose(new RestfulTransformer<>()).subscribe(subscriber));
        }
    }

}