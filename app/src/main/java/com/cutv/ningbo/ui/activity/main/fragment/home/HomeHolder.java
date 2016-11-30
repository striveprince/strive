package com.cutv.ningbo.ui.activity.main.fragment.home;

import android.content.Context;

import com.cutv.ningbo.R;
import com.cutv.ningbo.data.entity.HomeSlideEntity;
import com.cutv.ningbo.databinding.ItemNewsBinding;
import com.cutv.ningbo.ui.base.holder.BaseHolder;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.activity.main.fragment.NewsHolderViewModel;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:42
 * modify developer：  admin
 * modify time：10:42
 * modify remark：
 *
 * @version 2.0
 */
public class HomeHolder extends BaseHolder<HomeSlideEntity,NewsHolderViewModel,ItemNewsBinding> implements Respond{

    public HomeHolder(Context context) {
        super(context,R.layout.item_news);
        viewHolderComponent().inject(this);
        bindContentView(itemView);
    }



}
