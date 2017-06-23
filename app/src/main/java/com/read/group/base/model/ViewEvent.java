package com.read.group.base.model;

import android.view.View;

import com.read.group.base.util.BaseUtil;
import com.read.group.base.model.inter.Event;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：12:59
 * modify developer：  admin
 * modify time：12:59
 * modify remark：
 *
 * @version 2.0
 */


public class ViewEvent extends ViewParse implements Event {

    @Override
    public void registerEvent() {
        for(int eventId :getModelView().event()){
            eventSet.put(eventId, this);
        }
    }

    /**
     * @param view v the view of be clicked
     *              recommend use the method event(int eventId,View view)
     */
    @Deprecated
    public void event(Class<? extends Event> ec, View view) {
        int[] eventIds = BaseUtil.findModelView(ec).event();
        for(int eventId:eventIds){
            event(eventId,view);
        }
    }

    public void event(int eventId, View view,Object... args) {
        Event event = eventSet.get(eventId);
        if (event != null) event.onEvent(view, this,args);
    }

    @Override
    public void unRegisterEvent() {
        for(int eventId :getModelView().event()) {
            eventSet.remove(eventId);
        }
    }

    @Override
    public void onEvent(View view, Event event,Object... args) {

    }
}
