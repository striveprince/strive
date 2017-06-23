package com.read.group.base.model;

import android.view.View;
import android.view.ViewGroup;

import com.read.group.base.model.inter.Item;
import com.read.group.base.util.BaseUtil;

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


public abstract class ViewItemEntity extends ViewEvent implements Item {

    @Override
    public ViewGroup.LayoutParams params(View view, boolean parent) {
        return BaseUtil.params(view,parent);
    }
}
