package com.app.ui.base.recycler;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：18:59
 * modify developer：  admin
 * modify time：18:59
 * modify remark：
 *
 * @version 2.0
 */
//
//public interface OnItemClickListener<TimeEntity, T extends BaseHolder> {
//    void onItemClick(TimeEntity TimeEntity,T t);
//    boolean onLongItemClick(TimeEntity TimeEntity,T t);
//}
public interface OnItemClickListener<Entity> {
    void onItemClick(Entity entity, int position);
    boolean onLongItemClick(Entity entity, int position);
}
