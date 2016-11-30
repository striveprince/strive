package com.cutv.ningbo.ui.base.adapter;

import android.view.View;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：20:39
 * modify developer：  admin
 * modify time：20:39
 * modify remark：
 *
 * @version 2.0
 */

public interface RecyclerBindViewListener<Entity> {
    void onBindViewHolder(Entity entity, int position);
}
