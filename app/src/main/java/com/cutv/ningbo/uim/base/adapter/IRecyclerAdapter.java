package com.cutv.ningbo.uim.base.adapter;

import android.support.annotation.LayoutRes;

import com.cutv.ningbo.uim.base.model.inter.Event;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:12
 * modify developer：  admin
 * modify time：15:12
 * modify remark：
 *
 * @version 2.0
 */

public interface IRecyclerAdapter<E extends Event> extends IModelAdapter<E> {
    void modelView(int index , Event layoutModel, int type);

    void setLayoutId(@LayoutRes int layoutId);
}
