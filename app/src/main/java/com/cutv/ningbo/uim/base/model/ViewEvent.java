package com.cutv.ningbo.uim.base.model;

import android.view.MotionEvent;
import android.view.View;

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
    public void putEvent(int event) {
        eventSet.put(event,this);
    }

    public void removeEvent(int event) {
        eventSet.put(event,this);
    }

    public void event(int eventId, View view, MotionEvent event) {
        eventSet.get(eventId).onEvent(view,event);
    }

    @Override
    public void onEvent(View view, MotionEvent event) {

    }

}
