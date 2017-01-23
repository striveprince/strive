package com.cutv.ningbo.ui.activity.live.record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.cutv.ningbo.R;
import com.cutv.ningbo.databinding.ActivityLiveRecordBinding;
import com.cutv.ningbo.inject.scope.ActivityScope;
import com.cutv.ningbo.ui.base.activity.BaseActivity;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:55
 * modify developer：  admin
 * modify time：9:55
 * modify remark：
 *
 * @version 2.0
 */


//@RuntimePermissions
@ActivityScope
public class RecordActivity extends BaseActivity<RecordViewModel,ActivityLiveRecordBinding>{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityComponent().inject(this);
        setBindingView(R.layout.activity_live_record,savedInstanceState);
        viewModel.setView(binding.directView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.destroy();
    }

}
