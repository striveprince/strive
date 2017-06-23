package com.read.group.base.model.inter;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：11:05
 * modify developer：  admin
 * modify time：11:05
 * modify remark：
 * Holder can be fragment,RecyclerView.ViewHolder,ListView.Holder and so on
 * this item is data contains the Holder ,the child item.
 * @version 2.0
 */

public interface Item<Holder,T> extends Params {
    Holder getHolder();
    T getHolderView();
    String getHolderTitle();
}
