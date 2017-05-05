package com.cutv.ningbo.uim.base.adapter;

import com.cutv.ningbo.uim.base.model.inter.Event;
import com.cutv.ningbo.uim.base.model.inter.Params;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:30
 * modify developer：  admin
 * modify time：14:30
 * modify remark：
 *
 * @version 2.0
 */


public interface IModelAdapter<E extends Event> {
    void clear();


    /**
     * @param e the data of change
     * @param type 0x00: add the data to the position,
     *             0x01: add to the data to the last
     *             0x10: refresh the data
     *             0x20: remove all the data start with the position
     *             0x30: change the data
     * @return success or failed
     */
    boolean setList(int position,List<E> e,int type);
    boolean setEntity(int position,E e,int type);

//    void checkPosition(E i);
//    void checkLastPosition(E i);
    List<E> getList();
    int size();
}
