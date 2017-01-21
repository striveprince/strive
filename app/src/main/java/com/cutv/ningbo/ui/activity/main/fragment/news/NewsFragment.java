package com.cutv.ningbo.ui.activity.main.fragment.news;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cutv.ningbo.R;
import com.cutv.ningbo.data.api.NbtvApi;
import com.cutv.ningbo.data.entity.NewsDataEntity;
import com.cutv.ningbo.databinding.ViewPagerBinding;
import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.inject.qualifier.manager.ChildFragmentManager;
import com.cutv.ningbo.inject.scope.FragmentScope;
import com.cutv.ningbo.ui.activity.main.MainActivity;
import com.cutv.ningbo.ui.activity.main.fragment.news.content.NewsContentFragment;
import com.cutv.ningbo.ui.base.adapter.pager.FragmentViewPagerAdapter;
import com.cutv.ningbo.ui.base.fragment.BaseFragment;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.util.rotary.PagerChangeUtil;

import java.util.ArrayList;
import java.util.List;

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
public class NewsFragment extends BaseFragment<NewsPagerViewModel,ViewPagerBinding>
        implements Respond{// ,PagerChangeUtil.Listener<NewsDataEntity> {
    @Inject @FragmentContext Context context;
    @Inject NbtvApi api;
    @ChildFragmentManager
    @Inject FragmentManager fragmentManager;
    private MainActivity activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);
        activity = (MainActivity)context;
        return setAndBindContentView(inflater,container, R.layout.view_pager,savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.http(api.getTag(),this::successRespond);
    }

    private void successRespond(List<NewsDataEntity> newsDataEntities){
        FragmentViewPagerAdapter<NewsDataEntity> adapter = new FragmentViewPagerAdapter<>(fragmentManager,context);
        PagerChangeUtil<NewsDataEntity> util = new PagerChangeUtil<>();
        util.setAdapter(adapter);
//        util.setListener(this);
        // TODO: 17/1/19 need to add achieve the radio group and anim view;
        //util.setAnimView();
        util.setData(newsDataEntities,binding.viewpager,activity.getGroup());
        activity.getScrollView().scrollTo(0,0);
    }

//    @Override
//    public void checkIndexPager(int index, int lastIndex, RadioGroup group) {
//
//    }
//
//    @Override
//    public void initPager(NewsDataEntity newsDataEntity, RadioButton radioButton) {
//
//    }
}
