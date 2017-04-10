package com.app.ui.base.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：17:05
 * modify developer：  admin
 * modify time：17:05
 * modify remark：
 *
 * @version 2.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentView {
    int value();
}
