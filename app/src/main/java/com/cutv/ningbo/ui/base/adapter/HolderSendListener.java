package com.cutv.ningbo.ui.base.adapter;

import com.cutv.ningbo.data.entity.BaseEntity;
import com.cutv.ningbo.ui.base.holder.BaseHolder;


/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:53
 * modify developer：  admin
 * modify time：17:53
 * modify remark：
 *
 * @version 2.0
 */

public interface HolderSendListener<Entity extends BaseEntity> {
   void setBaseHolder(BaseHolder<Entity,?,?> holder);
}
