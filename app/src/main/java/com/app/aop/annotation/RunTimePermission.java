package com.app.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:15
 * modify developer：  admin
 * modify time：10:15
 * modify remark：
 *
 * @version 2.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RunTimePermission {
    String[] value();
//    Object context();
}
