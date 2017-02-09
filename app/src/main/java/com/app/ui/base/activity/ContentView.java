package com.app.ui.base.activity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:46
 * modify developer：  admin
 * modify time：9:46
 * modify remark：
 *
 * @version 2.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContentView {
    int value();
}
