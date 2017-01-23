package com.cutv.ningbo.ui.base.viewModel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.widget.RadioGroup;

import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.data.portlet.PagerModel;

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

public abstract class RadioViewModel<T extends Respond> extends UserViewModel<T> implements RadioGroup.OnCheckedChangeListener{
    public final ObservableInt checkedId = new ObservableInt(0);
    public RadioViewModel(Context context) {
        super(context);
    }

    @BindingAdapter("addRadio")
    public static void addRadioButton(RadioGroup group, Collection<? extends PagerModel> collection){
        group.removeAllViews();
        int i = -1;
        for(PagerModel model : collection) group.addView(model.getView(++i,group.getContext()));
    }

    @BindingAdapter("checked")
    public static void addRadioButton(RadioGroup group,RadioGroup.OnCheckedChangeListener listener){
        group.setOnCheckedChangeListener(listener);
    }

    public abstract Collection<? extends PagerModel> getPagerModels();

    public void setCheckedId(@IdRes int checkedId) {
        this.checkedId.set(checkedId);
    }

    public @IdRes int getCheckedId() {
        return checkedId.get();
    }

}