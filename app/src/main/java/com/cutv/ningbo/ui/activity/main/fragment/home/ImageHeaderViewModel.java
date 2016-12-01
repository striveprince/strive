package com.cutv.ningbo.ui.activity.main.fragment.home;

import android.content.Context;
import android.view.View;

import com.cutv.ningbo.data.entity.HomeSlideEntity;
import com.cutv.ningbo.ui.base.viewModel.BaseViewModel;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:02
 * modify developer：  admin
 * modify time：14:02
 * modify remark：
 *
 * @version 2.0
 */


public abstract class ImageHeaderViewModel<Type> extends BaseViewModel {
    private Type type;
    ImageHeaderViewModel(Context context,Type type) {
        super(context);
        this.type = type;
    }

    public View.OnClickListener getOnClick(){
        if(type instanceof HomeSlideEntity)((HomeSlideEntity) type).getOnClick();
        return view -> onClick(view,type);
    }

    public String getImageUrl(){
        return getImageUrl(type);
    }

    public abstract void onClick(View view,Type type);

    public abstract String getImageUrl(Type type);

    public Type getType() {
        return type;
    }
}
