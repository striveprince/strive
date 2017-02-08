package com.app.ui.base.recycler;

import com.app.data.entity.BaseEntity;
import com.app.ui.base.holder.BaseHolder;


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
   void setBaseHolder(BaseHolder<Entity> holder);
}
