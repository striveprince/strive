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

    public void event(View view, MotionEvent event) {
        if (getModelView()!=null&&getModelView().event()!=0){
            eventSet.put(getModelView().event(), this);
            eventSet.get(getModelView().event()).onEvent(view, event);
        }
    }
    public void removeEvent(int event) {
        eventSet.remove(event);
    }

    @Override
    public ModelView getModelView() {
        if(modelView == null) modelView = BaseUtil.findModelView(getClass());
        return modelView;
    }

    @Override
    public void onEvent(View view, MotionEvent event) {

    }

}
