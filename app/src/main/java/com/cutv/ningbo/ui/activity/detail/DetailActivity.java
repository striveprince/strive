package com.cutv.ningbo.ui.activity.detail;

import android.os.Bundle;

import com.cutv.ningbo.R;
import com.cutv.ningbo.databinding.ActivityDetailBinding;
import com.cutv.ningbo.inject.scope.ActivityScope;
import com.cutv.ningbo.ui.base.activity.BaseActivity;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:39
 * modify developer：  admin
 * modify time：15:39
 * modify remark：
 *
 * @version 2.0
 */

@ActivityScope
public class DetailActivity extends BaseActivity<DetailViewModel,ActivityDetailBinding>{
    private static final int Type = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setBindingView(R.layout.activity_detail,savedInstanceState);
//        binding.flContent.
    }
}
