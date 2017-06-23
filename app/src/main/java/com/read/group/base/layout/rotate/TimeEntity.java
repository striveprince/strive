package com.read.group.base.layout.rotate;

import com.read.group.base.layout.ViewArrayModel;
import com.read.group.base.model.inter.Item;

import java.util.ArrayList;
import java.util.List;


/**
 * @param <Model> model is the view page Model
 */
public class TimeEntity<Model extends Item> {
    private int totalTime;
    private int time = 0;
    private int index = 0;
    private int lastIndex = 0;
    private int loop = -1;
    private int count = 0;
    private List<Model> list = new ArrayList<>();
    private ViewArrayModel<Model,?> model;
    private List<PagerRotateListener<Model>> pagerRotateListeners = new ArrayList<>();

    public TimeEntity(List<Model> list, ViewArrayModel<Model,?> model) {
        this(3, list, model);
    }


    public TimeEntity(int totalTime, List<Model> list, ViewArrayModel<Model,?> model) {
        this.model = model;
        this.totalTime = totalTime;
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            this.index = list.size() - 1;
            count = list.size();
        }
    }


    public ViewArrayModel getModel() {
        return model;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    public int getCheckIndex(Model model) {
        return list.indexOf(model);
    }

    public TimeEntity<Model> addRotateListener(PagerRotateListener<Model> listener) {
        pagerRotateListeners.add(listener);
        return this;
    }

    void getTurn() {
        if (totalTime == getTime()) {
            Model model = getType();
            if (model != null)
                if (loop == -1 || --loop > 0) {
                    for (PagerRotateListener<Model> pagerRotateListener : pagerRotateListeners)
                        pagerRotateListener.nextRotate(model);
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

    public Model getType() {
        if (count != 0) return list.get(getCheckIndex() % count);
        return null;
    }


    public int getTotalTime() {
        return totalTime;
    }

    public int getTime() {
        return time = time == 0 ? totalTime : --time;
    }

    public List<Model> getList() {
        return list;
    }

    public void setList(List<Model> list) {
        this.list.clear();
        this.list.addAll(list);
        if (count == 0) count = list.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntity<?> that = (TimeEntity<?>) o;
        return model.equals(that.model);

    }

    @Override
    public int hashCode() {
        return model.hashCode();
    }
}