package com.cutv.ningbo.ui.activity.main.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.R;
import com.cutv.ningbo.data.api.UserApi;
import com.cutv.ningbo.data.entity.HomeDataEntity;
import com.cutv.ningbo.data.entity.HomeSlideEntity;
import com.cutv.ningbo.data.entity.InfoEntity;
import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.ui.base.recycler.RecyclerBindViewModel;

import javax.inject.Inject;

import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:17
 * modify developer：  admin
 * modify time：14:17
 * modify remark：
 *
 * @version 2.0
 */
public class HomePageViewModel extends RecyclerBindViewModel<HomeDataEntity, HomeSlideEntity, HomeAdapter> {

    private UserApi api;
    private HomeDataEntity entity;
    private static final String KEY = "homePageView";
    @Inject
    LayoutInflater inflater;

    @Inject
    public HomePageViewModel(@FragmentContext Context context, UserApi api) {
        super(context);
        this.api = api;
    }

    @Override
    public void saveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(KEY, entity);
    }

    @Override
    public void restoreInstanceState(@NonNull Bundle savedInstanceState) {
        entity = savedInstanceState.getParcelable(KEY);
    }

    @Override
    public Observable<InfoEntity<HomeDataEntity>> httpApi(int offset) {
        return api.getHomePager();
    }

    @Override
    protected boolean getPageFlag() {
        return false;
    }

    @Override
    protected void onCompleted(HomeDataEntity t,Throwable e) {
        super.onCompleted(t,e);
        if (e == null) {
            getAdapter().setCount(3);
            getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.LayoutManager getManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected void onScrollBottom() {
        super.onScrollBottom();
        if(getAdapter().getItemCount()==3){
            getAdapter().setCount(6);
            View v = inflater.inflate(R.layout.footer_home_pager, null);
            v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            getAdapter().addFooterView(v);
        }
    }
}
