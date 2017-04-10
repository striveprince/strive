package com.app.ui.activity.live.content;

import android.content.Context;
import android.os.Bundle;

import com.app.R;
import com.app.data.api.UserApi;
import com.app.inject.qualifier.context.ActivityContext;
import com.app.ui.base.annotation.ContentView;
import com.app.ui.base.respond.Respond;
import com.app.ui.base.viewModel.BaseViewModel;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:20
 * modify developer：  admin
 * modify time：15:20
 * modify remark：
 *
 * @version 2.0
 */

@ContentView(R.layout.activity_live_content)
public class LiveContentViewModel extends BaseViewModel<Respond> {

    @Inject
    public LiveContentViewModel(@ActivityContext Context context) {
        super(context);
    }

}
