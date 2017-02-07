package com.app.ui.base.adapter.recycler;

import com.app.data.entity.BaseEntity;
import com.app.ui.base.holder.BaseHolder;


/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:44
 * modify developer：  admin
 * modify time：17:44
 * modify remark：
 *
 * @version 2.0
 */

public interface HolderGetListener<Entity extends BaseEntity> {
    void getBaseHolder(BaseHolder<Entity,?,?> holder);
}
