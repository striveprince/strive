package com.cutv.ningbo.uim.base.model;

import android.view.View;
import android.view.ViewGroup;

import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.model.inter.ItemEntity;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:06
 * modify developer：  admin
 * modify time：13:06
 * modify remark：
 *
 * @version 2.0
 */


public abstract class ViewItemEntity extends ViewEntity implements ItemEntity {

    @Override
    public ViewGroup.LayoutParams params(View view, boolean parent) {
        return BaseUtil.params(view,parent);
    }
}
