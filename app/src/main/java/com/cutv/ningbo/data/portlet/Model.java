package com.cutv.ningbo.data.portlet;

import android.content.Context;
import android.view.View;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:49
 * modify developer：  admin
 * modify time：15:49
 * modify remark：
 *
 * @version 2.0
 */


public interface Model<Item,V extends View> {
    Item getItem(int position,Context context);
    V getView(int position,Context context);
}
