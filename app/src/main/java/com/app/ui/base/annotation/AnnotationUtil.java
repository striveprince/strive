package com.app.ui.base.annotation;


/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：16:09
 * modify developer：  admin
 * modify time：16:09
 * modify remark：
 *
 * @version 2.0
 */


public class AnnotationUtil {
    public static ContentView findContentView(Class<?> thisCls) {
        if (thisCls == null ) return null;
        ContentView contentView = thisCls.getAnnotation(ContentView.class);
        if (contentView == null) return findContentView(thisCls.getSuperclass());
        return contentView;
    }
}
