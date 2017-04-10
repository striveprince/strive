package com.app.ui.base.viewModel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.IdRes;
import android.widget.RadioGroup;

import com.app.data.portlet.PagerModel;
import com.app.ui.base.respond.Respond;

import java.util.Collection;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:33
 * modify developer：  admin
 * modify time：14:33
 * modify remark：
 *
 * @version 2.0
 */

public abstract class RadioViewModel<T extends Respond> extends UserViewModel<T> implements RadioGroup.OnCheckedChangeListener {
    public final ObservableInt checkedId = new ObservableInt(0);

    public RadioViewModel(Context context) {
        super(context);
    }


    public abstract Collection<? extends PagerModel> getPagerModels();

    public void setCheckedId(@IdRes int checkedId) {
        this.checkedId.set(checkedId);
    }

    public
    @IdRes
    int getCheckedId() {
        return checkedId.get();
    }

//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        int position = group.indexOfChild(group.findViewById(checkedId));
//        getRespond().onCheckedChanged(position);
//        onCheckedChanged(position);
//    }

//    public abstract void onCheckedChanged(int position);
}
