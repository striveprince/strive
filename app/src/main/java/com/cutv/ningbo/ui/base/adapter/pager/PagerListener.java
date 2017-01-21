package com.cutv.ningbo.ui.base.adapter.pager;

import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by apple on 17/1/18.
 */

public interface PagerListener<T> {
    void setList(List<? extends T> list);
}
