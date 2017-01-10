package com.cutv.ningbo.inject.module;

import android.content.Context;

import com.cutv.ningbo.data.save.SharePreferenceUtil;
import com.cutv.ningbo.inject.qualifier.context.AppContext;
import com.cutv.ningbo.inject.qualifier.preference.BrokeSharePreference;
import com.cutv.ningbo.inject.qualifier.preference.NingSharePreference;
import com.cutv.ningbo.inject.qualifier.preference.OldSharePreference;
import com.cutv.ningbo.inject.qualifier.preference.SystemSharePreference;
import com.cutv.ningbo.inject.qualifier.preference.UserSharePreference;

import dagger.Module;
import dagger.Provides;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:36
 * modify developer：  admin
 * modify time：10:36
 * modify remark：
 *
 * @version 2.0
 */

@Module
public class DataModule {
    @Provides
    @UserSharePreference
    SharePreferenceUtil provideUserInfo(@AppContext Context context) {
        return SharePreferenceUtil.getUserInstance(context);
    }

    @Provides
    @NingSharePreference
    SharePreferenceUtil provideNingInfo(@AppContext Context context) {
        return SharePreferenceUtil.getNingInstance(context);
    }

    @Provides
    @OldSharePreference
    SharePreferenceUtil provideOldInfo(@AppContext Context context) {
        return SharePreferenceUtil.getOldInstance(context);
    }
    @Provides
    @BrokeSharePreference
    SharePreferenceUtil provideBrokeInfo(@AppContext Context context) {
        return SharePreferenceUtil.getBrokeInstance(context);
    }
    @Provides
    @SystemSharePreference
    SharePreferenceUtil provideSystemInfo(@AppContext Context context) {
        return SharePreferenceUtil.getSystemInstance(context);
    }
}
