package com.cutv.ningbo.uim.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：13:59
 * modify developer：  admin
 * modify time：13:59
 * modify remark：
 *
 * @version 2.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelView {
    int[] value();
    int event() default 0;
    boolean cycle() default false;
}
