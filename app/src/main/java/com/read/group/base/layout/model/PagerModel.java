package com.read.group.base.layout.model;

import android.databinding.ObservableInt;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.read.group.BR;
import com.read.group.R;
import com.read.group.base.adapter.ILayoutAdapter;
import com.read.group.base.annotation.ModelView;
import com.read.group.base.cycle.CycleContainer;
import com.read.group.base.layout.ViewArrayModel;
import com.read.group.base.layout.rotate.PagerRotateListener;
import com.read.group.base.layout.rotate.TimeEntity;
import com.read.group.base.layout.rotate.TimeUtil;
import com.read.group.base.model.inter.ItemEvent;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:30
 * modify developer：  admin
 * modify time：9:30
 * modify remark：
 *
 * @version 2.0
 */

@ModelView(value = {R.layout.layout_radio_pager},name = {BR.layout})
public class PagerModel<E extends ItemEvent>
        extends ViewArrayModel<E, ILayoutAdapter<E>>
        implements PagerRotateListener<E>, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    private int loop = -1;
    private TimeEntity<E> timeEntity;
    public ObservableInt currentItem = new ObservableInt(0);
    public ObservableInt position = new ObservableInt(0);
    private boolean rotate = false;

    public PagerModel() {
        timeEntity = new TimeEntity<>(getData(), this);
        timeEntity.addRotateListener(this);
    }

    @Override
    public void attachView(CycleContainer cycleContainer, int model_index) {
        super.attachView(cycleContainer,model_index);
        TimeUtil.getInstance().remove(timeEntity);
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    @Override
    public void nextRotate(E e) {
        if (rotate && (loop == -1 || --loop > 0)) setCurrentItem(getData().indexOf(e));
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem.set(currentItem);
    }

    @Override
    public void call(List<E> es) {
        super.call(es);
        if (rotate) TimeUtil.getInstance().add(timeEntity);
    }

    @Override
    public ILayoutAdapter<E> getAdapter() {
        return super.getAdapter();
    }

    public void setCount(int count) {
        getAdapter().setCount(count);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int indexOfChild = group.indexOfChild(group.findViewById(checkedId));
        setCurrentItem(indexOfChild);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.position.set(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (rotate) TimeUtil.getInstance().switching(timeEntity, state);
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }

}