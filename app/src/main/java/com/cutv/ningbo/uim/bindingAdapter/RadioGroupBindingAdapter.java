package com.cutv.ningbo.uim.bindingAdapter;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.RadioGroup;

import com.cutv.ningbo.uim.base.model.inter.ItemEvent;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:47
 * modify developer：  admin
 * modify time：16:47
 * modify remark：
 *
 * @version 2.0
 */


public class RadioGroupBindingAdapter {
    @BindingAdapter("checked")
    public static void addCheckedListener(RadioGroup group, RadioGroup.OnCheckedChangeListener listener) {
        if(listener!=null)group.setOnCheckedChangeListener(listener);
    }

    @BindingAdapter("radios_mute")
    public static void addRadioButton(RadioGroup group, List<? extends ItemEvent<?,View>> list) {
        group.removeAllViews();
        int i = 0;
        if (list != null)
            for (ItemEvent<?,View> model : list) {
                View button = model.getHolderView();
                button.setSoundEffectsEnabled(false);
                group.addView(button);
                if (i == 0) button.performClick();
                i++;
            }
    }
//
//
//    @BindingAdapter("radios")
//    public static void addRadios(RadioGroup group, List<? extends LayoutModel> list) {
//        if (list != null && list.size() != 0) {
//            group.removeAllViews();
//            LayoutInflater inflater = LayoutInflater.from(group.getContext());
//            for (LayoutModel model : list) {
//                int layout = AnnotationUtil.findModelView(model.getClass()).value();
//                ViewDataBinding binding = DataBindingUtil.inflate(inflater, layout, null, false);
//                binding.getRoot().setLayoutParams(model.setLayoutParams(group, true));
//                group.addView(binding.getRoot());
//                binding.setVariable(BR.vm, model);
//            }
//            if (list.size() > 0) group.check(group.getChildAt(0).getId());
//        }
//    }

    @BindingAdapter("checkedPosition")
    public static void checkPosition(RadioGroup group,int position){
        if(position<group.getChildCount())group.check(group.getChildAt(position).getId());
    }

}
