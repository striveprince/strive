package com.cutv.ningbo.ui.activity.main.fragment.news;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.cutv.ningbo.R;
import com.cutv.ningbo.data.api.NbtvApi;
import com.cutv.ningbo.data.entity.NewsDataEntity;
import com.cutv.ningbo.databinding.ViewPagerBinding;
import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.inject.scope.FragmentScope;
import com.cutv.ningbo.ui.activity.main.MainActivity;
import com.cutv.ningbo.ui.activity.main.fragment.news.content.NewsContentFragment;
import com.cutv.ningbo.ui.base.adapter.ViewPagerAdapter;
import com.cutv.ningbo.ui.base.fragment.BaseFragment;
import com.cutv.ningbo.ui.base.respond.Respond;

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
public class NewsFragment extends BaseFragment<NewsPagerViewModel,ViewPagerBinding> implements Respond{
    private List<NewsContentFragment> list = new ArrayList<>();
    @Inject ViewPagerAdapter<NewsContentFragment> adapter;
    @Inject @FragmentContext Context context;
    @Inject NbtvApi api;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentComponent().inject(this);
        return setAndBindContentView(inflater,container, R.layout.view_pager,savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.http(api.getTag(),this::successRespond);
    }

    private void successRespond(List<NewsDataEntity> newsDataEntities){
        list.clear();
        for(NewsDataEntity entity:newsDataEntities){
            NewsContentFragment fragment = new NewsContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("channelId",entity.getId());
            fragment.setArguments(bundle);
            list.add(fragment);
        }
        adapter.setList(list);
        binding.viewpager.setAdapter(adapter);

        RadioGroup newsRgNavContent = (RadioGroup)((MainActivity)context).getMainBinding().toolbar.findViewById(R.id.tool_rg_tag);
    }

}
