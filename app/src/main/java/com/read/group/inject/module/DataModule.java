package com.read.group.inject.module;

import android.content.Context;

import com.read.group.data.save.SharePreferenceUtil;
import com.read.group.inject.qualifier.context.AppContext;
import com.read.group.inject.qualifier.preference.BrokeSharePreference;
import com.read.group.inject.qualifier.preference.NingSharePreference;
import com.read.group.inject.qualifier.preference.OldSharePreference;
import com.read.group.inject.qualifier.preference.SystemSharePreference;
import com.read.group.inject.qualifier.preference.UserSharePreference;

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
