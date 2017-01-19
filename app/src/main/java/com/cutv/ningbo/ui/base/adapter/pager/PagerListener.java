package com.cutv.ningbo.ui.base.adapter.pager;

import java.util.List;

/**
 * Created by apple on 17/1/18.
 */

public interface PagerListener<T> {
    void setList(List<? extends T> list);
}
