package com.cutv.ningbo.ui.activity.live.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Toast;

import com.cutv.ningbo.data.api.LiveApi;
import com.cutv.ningbo.data.save.SharePreferenceUtil;
import com.cutv.ningbo.inject.qualifier.context.ActivityContext;
import com.cutv.ningbo.inject.qualifier.preference.NingSharePreference;
import com.cutv.ningbo.ui.activity.live.record.RecordActivity;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.base.viewModel.BaseViewModel;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:58
 * modify developer：  admin
 * modify time：9:58
 * modify remark：
 *
 * @version 2.0
 */


public class LoginViewModel extends BaseViewModel<Respond> {
    private LiveApi api;

    public String cellPhone = "";
    public String password = "";
    public SpannableStringBuilder mSp;

//    public String getmSp() {
//        return mSp.toString();
//    }



    private SharePreferenceUtil util;

    @Inject
    LoginViewModel(LiveApi api, @ActivityContext Context context,@NingSharePreference SharePreferenceUtil util) {
        super(context);
        this.api = api;
        mSp = new SpannableStringBuilder("登录点看直播");
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(000, 000, 000));
        mSp.setSpan(fcs, 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        mSp.setSpan(bss, 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        util.getValue("cellphone",String.class);
        util.getValue("password",String.class);
        this.util = util;

    }


    public void onLoginClick(View view) {
        Toast.makeText(view.getContext(), "cellPhone:" + cellPhone + "\npassword:" + password, Toast.LENGTH_SHORT).show();

//        getContext().startActivity(new Intent(getContext(), RecordActivity.class));
        http(api.login(cellPhone, password), entity -> {
            util.setAllDto(entity);
            util.setValue("password", password);
            getContext().startActivity(new Intent(getContext(), RecordActivity.class));

        });
    }


}
