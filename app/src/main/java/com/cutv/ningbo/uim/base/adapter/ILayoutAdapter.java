package com.cutv.ningbo.uim.base.adapter;

import com.cutv.ningbo.uim.base.model.inter.ItemEvent;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:34
 * modify developer：  admin
 * modify time：17:34
 * modify remark：
 *
 * @version 2.0
 */


public interface ILayoutAdapter<E extends ItemEvent> extends IModelAdapter<E> {
    void setCount(int count);
}
