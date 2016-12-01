package com.cutv.ningbo.ui.activity.main.fragment.news;

import android.content.Context;

import com.cutv.ningbo.inject.qualifier.context.FragmentContext;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.base.viewModel.BaseViewModel;

import javax.inject.Inject;

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
public class NewsPagerViewModel extends BaseViewModel<Respond> {

    @Inject
    NewsPagerViewModel(@FragmentContext Context context) {
        super(context);
//        this.api = api;
    }

//    private NbtvApi api;

//    @Override
//    public Observable<InfoEntity<List<NewsDataEntity>>> initApi() {
//        return api.getTag();
//    }


}
