package com.cutv.ningbo.uim.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:41
 * modify developer：  admin
 * modify time：10:41
 * modify remark：
 *
 * @version 2.0
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LifeCycle {
    boolean cycle();
}
