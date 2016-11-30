package com.cutv.ningbo.ui.activity.main.fragment.home;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cutv.ningbo.R;
import com.cutv.ningbo.data.entity.HomeDataEntity;
import com.cutv.ningbo.data.entity.HomeSlideEntity;
import com.cutv.ningbo.databinding.ItemPopOptionBinding;
import com.cutv.ningbo.ui.base.respond.Respond;
import com.cutv.ningbo.ui.base.viewModel.BaseViewModel;

import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:48
 * modify developer：  admin
 * modify time：13:48
 * modify remark：
 *
 * @version 2.0
 */
public class HomePagerHeaderModel extends BaseViewModel<Respond> {
    private HomeDataEntity dataEntity;
    private List<HomeSlideEntity> slide;

    public HomePagerHeaderModel(HomeDataEntity dataEntity) {
        this.dataEntity = dataEntity;
        slide = dataEntity.getSlide();
    }

    public HomePagerHeaderModel(List<HomeSlideEntity> list) {
        this.slide = list;
    }

    public List<HomeSlideEntity> getSlide() {
        return slide;
    }

    public List<HomeSlideEntity> getApplication() {return dataEntity.getApplication();}

    public String getActivityText0() {
        String text = dataEntity.getActivity().get(0).getTitle();
        return TextUtils.isEmpty(text) ? "" : text;
    }

    public String getActivityText1() {
        String text = dataEntity.getActivity().get(1).getTitle();
        return TextUtils.isEmpty(text) ? "" : text;
    }

    public String getActivityText2() {
        String text = dataEntity.getActivity().get(2).getTitle();
        return TextUtils.isEmpty(text) ? "" : text;
    }

    public View.OnClickListener getActivity0() {
        return dataEntity.getActivity().get(0).getOnClick();
    }

    public View.OnClickListener getActivity1() {
        return dataEntity.getActivity().get(1).getOnClick();
    }

    public View.OnClickListener getActivity2() {
        return dataEntity.getActivity().get(2).getOnClick();
    }

    public String getActivityImage0() {
        String text = dataEntity.getActivity().get(0).getImage();
        return TextUtils.isEmpty(text) ? "" : text;
    }



    public String getActivityImage1() {
        String text = dataEntity.getActivity().get(1).getImage();
        return TextUtils.isEmpty(text) ? "" : text;
    }

    public String getActivityImage2() {
        String text = dataEntity.getActivity().get(2).getImage();
        return TextUtils.isEmpty(text) ? "" : text;
    }

    public String getCommentImage() {
        String text = dataEntity.getBiaotai().get(0).getImage();
        return TextUtils.isEmpty(text) ? "" : text;
    }

    public View.OnClickListener  getComment() {
        return dataEntity.getBiaotai().get(0).getOnClick();
    }

    @BindingAdapter({"addItem"})
    public static void AddView(LinearLayout layout, List<HomeSlideEntity> dataEntity) {
        Context context = layout.getContext();
        layout.removeAllViews();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
//        dataEntity.getApplication()
        if (dataEntity != null)
            for (HomeSlideEntity entity : dataEntity) {
                ItemPopOptionBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_pop_option, null, false);
                binding.setTimeEntity(entity);
//                binding.setEntity(entity);
                binding.getRoot().setLayoutParams(new ViewGroup.LayoutParams(dm.widthPixels / 5, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.addView(binding.getRoot());
            }
    }

}
