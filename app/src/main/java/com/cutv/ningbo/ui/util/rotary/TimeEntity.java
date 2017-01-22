package com.cutv.ningbo.ui.util.rotary;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TimeEntity<Type> {
    private int totalTime;
    private int time = 0;
    private int index = 0;
    private int lastIndex = 0;
    private int loop = -1;
    private int count = 0;
    protected List<Type> list;
    private View view;
    private List<PagerRotateListener<Type>> pagerRotateListeners = new ArrayList<>();

    public TimeEntity(List<Type> list, View view) {
        this(3, list, view);
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    public TimeEntity(int totalTime, List<Type> list, View view) {
        this.totalTime = totalTime;
        this.view = view;
        this.list = list;
        this.index = list.size() - 1;
        count = list.size();
    }

    public View getView() {
        return view;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCheckIndex(Type type) {
        return list.indexOf(type);
    }

    public TimeEntity<Type> addRotateListener(PagerRotateListener<Type> listener) {
        pagerRotateListeners.add(listener);
        return this;
    }

    void getTurn() {
        if (totalTime == getTime()) {
            Type type = getType();
            if (loop == -1 || --loop > 0) {
                for (PagerRotateListener<Type> pagerRotateListener : pagerRotateListeners)
                    pagerRotateListener.nextRotate(type, view);
                //            pagerRotateListeners.forEach(typeCarouselListener -> typeCarouselListener.nextRotate(type,view));
            }
        }
    }

    private int getCheckIndex() {
        lastIndex = index;
        return index = index == count - 1 ? 0 : ++index;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setIndex(int index) {
        lastIndex = this.index;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Type getType() {
        return list.get(getCheckIndex()%count);
    }


    public int getTotalTime() {
        return totalTime;
    }

    public int getTime() {
        return time = time == 0 ? totalTime : --time;
    }

    public List<Type> getList() {
        return list;
    }

    public void setList(List<Type> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntity<?> timeEntity = (TimeEntity<?>) o;
        return view != null ? view.equals(timeEntity.view) : timeEntity.view == null;

    }

    @Override
    public int hashCode() {
        return view != null ? view.hashCode() : 0;
    }

}