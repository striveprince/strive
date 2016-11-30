package com.cutv.ningbo.ui.base.viewModel;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.cutv.ningbo.data.entity.BaseEntity;
import com.cutv.ningbo.ui.base.respond.Respond;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:59
 * modify developer：  admin
 * modify time：13:59
 * modify remark：
 *
 * @version 2.0
 */


public abstract class HolderViewModel<RD extends Respond,Entity extends BaseEntity> extends BaseViewModel<RD> {
    @Override
    public void saveInstanceState(Bundle outState) {

    }

    @Override
    public void restoreInstanceState(@NonNull Bundle savedInstanceState) {

    }

    public abstract void bind(Entity entity,int position);
}
