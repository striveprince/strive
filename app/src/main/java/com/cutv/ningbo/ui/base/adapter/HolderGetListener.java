package com.cutv.ningbo.ui.base.adapter;

import com.cutv.ningbo.data.entity.BaseEntity;
import com.cutv.ningbo.ui.base.holder.BaseHolder;


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
    void getBaseHolder(BaseHolder<Entity, ?, ?> holder);
}
