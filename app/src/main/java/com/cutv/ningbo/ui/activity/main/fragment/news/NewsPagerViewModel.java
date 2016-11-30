package com.cutv.ningbo.ui.activity.main.fragment.news;

import android.content.Context;

import com.cutv.ningbo.data.api.NbtvApi;
import com.cutv.ningbo.data.entity.InfoEntity;
import com.cutv.ningbo.data.entity.NewsDataEntity;
import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.ui.base.viewModel.InitEntityViewModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:41
 * modify developer：  admin
 * modify time：9:41
 * modify remark：
 *
 * @version 2.0
 */
public class NewsPagerViewModel extends InitEntityViewModel<List<NewsDataEntity>> {

    @Inject
    public NewsPagerViewModel(@FragmentContext Context context, NbtvApi api) {
        super(context);
        this.api = api;
    }

    private NbtvApi api;

    @Override
    public Observable<InfoEntity<List<NewsDataEntity>>> initApi() {
        return api.getTag();
    }


}
