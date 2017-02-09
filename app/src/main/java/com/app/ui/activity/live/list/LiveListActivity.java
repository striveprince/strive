package com.app.ui.activity.live.list;

import com.app.R;
import com.app.databinding.ActivityLiveListBinding;
import com.app.inject.component.ActivityComponent;
import com.app.ui.base.activity.BaseActivity;
import com.app.ui.base.activity.ContentView;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:05
 * modify developer：  admin
 * modify time：17:05
 * modify remark：
 *
 * @version 2.0
 */
@ContentView(R.layout.activity_live_list)
public class LiveListActivity extends BaseActivity<LiveListViewModel,ActivityLiveListBinding> {
    @Override
    public void inject(ActivityComponent component) {
        component.inject(this);
    }
}
