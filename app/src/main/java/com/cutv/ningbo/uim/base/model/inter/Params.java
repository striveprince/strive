package com.cutv.ningbo.uim.base.model.inter;

import android.view.View;
import android.view.ViewGroup;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:23
 * modify developer：  admin
 * modify time：14:23
 * modify remark：
 *
 * @version 2.0
 */

public interface Params extends Event{
    /**
     * @param view view
     * @param parent true the view is parent or ,view is current;
     * @return params
     */
    ViewGroup.LayoutParams params(View view, boolean parent);
}
