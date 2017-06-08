package com.cutv.ningbo.uim.base.model.inter;

import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import com.cutv.ningbo.uim.base.annotation.ModelView;

/**
 * project：cutv_ningbo
 * description：
 * the event is simple and directly,
 * you must use the event to execute ,but the view isn't here.so you must distribute the
 * event to another model.here you can use this Event
 * for example:
 * Event1 and Event2. now,the click event at Event1,but I need to transfer event to Event2.
 * just do:
 *  class Event1 implements Event{
 *      public Event1(){
 *          registerEvent();
 *      }
 *  }
 *
 *
 * create developer： admin
 * create time：15:20
 * modify developer：  admin
 * modify time：15:20
 * modify remark:
 *
 * @version 2.0
 */


public interface Event extends Parse{
    SparseArray<Event> eventSet = new SparseArray<>();
    void registerEvent();
    void unRegisterEvent();
    void onEvent(View view, Event event);
}
