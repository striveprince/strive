package com.app.ui.activity.live.content;

import com.app.R;
import com.app.databinding.ActivityLiveContentBinding;
import com.app.inject.component.ActivityComponent;
import com.app.ui.base.activity.BaseActivity;
import com.app.ui.base.annotation.ContentView;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:03
 * modify developer：  admin
 * modify time：17:03
 * modify remark：
 *
 * @version 2.0
 */
@ContentView(R.layout.activity_live_content)
public class LiveContentActivity extends BaseActivity<LiveContentViewModel,ActivityLiveContentBinding> {
    @Override
    public void inject(ActivityComponent component) {
        component.inject(this);

//        TranslateAnimation
    }


}
