package com.read.group.bindingAdapter;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:49
 * modify developer：  admin
 * modify time：14:49
 * modify remark：
 *
 * @version 2.0
 */


public class TabLayoutBindingAdapter {

//    @BindingAdapter("title")
//    public static void setTabTitle(TabLayout tabLayout, List<ItemLayoutModel> titles) {
////        tabLayout.removeAllViews();
//        for (int i = 0; i < titles.size(); i++) {
//            TabLayout.Tab tab= tabLayout.getTabAt(i);
//            if(tab!=null) tab.setText(titles.get(i).getPagerTitle());
//        }
//        setIndicator(tabLayout.getContext(),tabLayout, 20, 20);
//    }
//
//    public static void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
//        try {
//            Class<?> tabLayout = tabs.getClass();
//            Field tabStrip = tabLayout.getDeclaredField("mTabStrip");
//            tabStrip.setAccessible(true);
//            LinearLayout ll_tab = (LinearLayout) tabStrip.get(tabs);
//            int left =  ScreenUtil.dip2px(context, leftDip);
//            int right = ScreenUtil.dip2px(context, rightDip);
//            for (int i = 0; i < ll_tab.getChildCount(); i++) {
//                View child = ll_tab.getChildAt(i);
//                child.setPadding(0, 0, 0, 0);
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//                params.leftMargin = left;
//                params.rightMargin = right;
//                child.setLayoutParams(params);
//                child.invalidate();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
