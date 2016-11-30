package com.cutv.ningbo.inject.module;

import android.content.Context;
import android.view.View;

import com.cutv.ningbo.inject.qualifier.context.HolderContext;
import com.cutv.ningbo.inject.scope.HolderScope;

import dagger.Module;
import dagger.Provides;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:07
 * modify developer：  admin
 * modify time：10:07
 * modify remark：
 *
 * @version 2.0
 */
@HolderScope
@Module
public class ViewHolderModule {
    private final Context context;

    public ViewHolderModule(View view) {
        this.context =  view.getContext();
    }

    @Provides
    @HolderScope
    @HolderContext
    Context provideActivity(){
        return context;
    }



}
