package com.read.group.bindingAdapter;

import android.databinding.BindingAdapter;
import android.widget.LinearLayout;

import java.util.Collection;

/**
 * Created by maomao on 2017/4/13.
 */

public class LinearLayoutBindingAdapter {
    @BindingAdapter({"images","params"})
    public static void addImageView(LinearLayout layout,Collection<String> list,LinearLayout.LayoutParams params){
//        layout.removeAllViews();
//        for(String imageUrl :list){
//            ImageView view = new ImageView(layout.getContext());
//            view.setLayoutParams(params);
//            layout.addView(view);
//            ImageUtil.defaultBind(view,imageUrl);
//        }
    }

//    @BindingAdapter({"images"})
//    public static void addImageView(LinearLayout layout, HeadLineItemEntity vm){
//        layout.removeAllViews();
//        if (vm.getType()==3||vm.getType()==7){
//            String[] strings= vm.getImage().split("[|]");
//            for (int i = 0; i < strings.length; i++) {
//                ImageView view = new ImageView(layout.getContext());
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT);
//                params.weight=1;
//                if (i==1)
//                params.setMargins(10,0,10,0);
//                view.setLayoutParams(params);
//                layout.addView(view);
//                ImageUtil.imageBind(view,strings[i]);
//            }
//
//        }
//    }
}
