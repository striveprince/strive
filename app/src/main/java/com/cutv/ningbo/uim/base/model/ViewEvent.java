package com.cutv.ningbo.uim.base.model;

import android.view.MotionEvent;
import android.view.View;

import com.cutv.ningbo.uim.base.BaseUtil;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.model.inter.Event;

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


public class ViewEvent implements Event {
    private transient ModelView modelView;

    @Override
    public void registerEvent() {
        if(getModelView().event()!=0)
            eventSet.put(getModelView().event(), this);
    }

    /**
     * @param ec the class of model
     * @param view v the view of be clicked
     * @param event event
     *              recommend use the method event(int eventId,View view,MotionEvent event)
     */
    @Deprecated
    public void event(Class<? extends Event> ec, View view, MotionEvent event) {
        int eventId = BaseUtil.findModelView(ec).event();
        event(eventId,view,event);
    }

    public void event(int eventId, View view, MotionEvent event) {
        Event event1 = eventSet.get(eventId);
        if (event1 != null) event1.onEvent(view, event,this);
    }

    @Override
    public void unRegisterEvent() {
        eventSet.remove(getModelView().event());
    }

    @Override
    public ModelView getModelView() {
        if (modelView == null) {
            modelView = BaseUtil.findModelView(getClass());
            if (modelView == null)
                throw new RuntimeException("should to add @ModelView to the class:" + getClass());
        }
        return modelView;
    }

    @Override
    public void onEvent(View view, MotionEvent motionEvent,Event event) {

    }

//    @Override
//    public LifeCycle getLifCycle() {
//        if (lifeCycle == null)
//            lifeCycle = BaseUtil.findLifeCycle(getClass());
//        return lifeCycle;
//    }
}
