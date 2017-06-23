package com.read.group.data.entity;

import android.databinding.BaseObservable;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:23
 * modify developer：  admin
 * modify time：14:23
 * modify remark：
 *
 * @version 2.0
 */

public class BaseEntity extends BaseObservable {
    private static final String FORMAT_HOUR_MIN = "HH:mm";
    private static final String FORMAT_DATE_MONTH_MIN = "MM-dd HH:mm";
    private static final String FORMAT_DATE_DAY = "yyyy-MM-dd";
    private static final String FORMAT_TIME = "HH:mm:ss";
    private static final String FORMAT_DATE_MIN = "yyyy-MM-dd HH:mm";

    public String obtainCreateTime() {
        return obtainCreateTime(FORMAT_DATE_MIN);//SetInfo.FORMAT_DATE_MIN
    }

    public String obtainCreateTime(String format) {
        try {
            Field field = getClass().getDeclaredField("create_time");
            field.setAccessible(true);
            if (field.getType() == long.class) {
                return obtainTime(field.getLong(this), format);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String obtainTime(long time, String format) {
        Date d = new Date(time * 1000);
        SimpleDateFormat sf = new SimpleDateFormat(format, Locale.CHINA);
        return sf.format(d);
    }

//    public String obtainTime(long time, String format,Locale locale) {
//        Date d = new Date(time * 1000);
//        SimpleDateFormat sf = new SimpleDateFormat(format, locale);
//        sf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        return sf.format(d);
//    }

    public String obtainTime(long time) {
        Date d = new Date(time * 1000);
        SimpleDateFormat sf = new SimpleDateFormat(FORMAT_DATE_MIN, Locale.CHINA);
        return sf.format(d);
    }

    public boolean isSomeNull() {
        for (Field f : getClass().getDeclaredFields())
            if (isFieldNull(f))return true;
        return false;
    }

    public boolean isAnyNull() {
        for (Field f : getClass().getDeclaredFields())
            if (!isFieldNull(f))return false;
        return true;
    }

    private boolean isFieldNull(Field f) {
        f.setAccessible(true);
        try {
            Object o = f.get(this);
            if (o == null) {
                return true;
            } else if (f.getGenericType() == String.class) {
                return TextUtils.isEmpty(o.toString());
            } else if (f.getGenericType() == Collection.class) {
                return ((Collection) o).isEmpty();
            } else if (f.getType().isArray()) {
                return ((Object[]) o).length == 0;
            } else if (f.getType() == int.class) {
                return f.getInt(this) == 0;
            } else if (f.getType() == long.class) {
                return f.getLong(this) == 0L;
            } else if (f.getType() == boolean.class) {
                return !f.getBoolean(this);
            } else if (f.getType() == short.class) {
                return f.getShort(this) == 0;
            } else if (f.getType() == byte.class) {
                return f.getByte(this) == 0;
            } else if (f.getType() == double.class) {
                return f.getDouble(this) == 0.0d;
            } else if (f.getType() == float.class) {
                return f.getFloat(this) == 0.0f;
            } else if (f.getType() == char.class) {
                return f.getChar(this) == '\u0000';
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        Class c = this.getClass();
        StringBuilder errorStr = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder(c.getSimpleName());

        stringBuilder.append(":{");
        for (Field field : c.getDeclaredFields()) {
            String str = "null";
            field.setAccessible(true);
            try {
                Object obj = field.get(this);
                Class clazz = field.getType();
                if (clazz.isArray()) {
                    str = Arrays.toString((Object[]) obj);
                } else {
                    str = obj.toString();
                }
            } catch (Exception e) {
                errorStr.append(field.getName());
                errorStr.append(":");
                errorStr.append(e.getMessage());
                errorStr.append(" ");
            }
            stringBuilder.append(field.getName());
            stringBuilder.append("=");
            stringBuilder.append(str);
            stringBuilder.append(",");
        }
        stringBuilder.append("}");

//        Logger.i(Logger.TimeEntity, stringBuilder.toString());
//        Logger.i(Logger.DTOERROR, errorStr.toString());
        return stringBuilder.toString();
    }





}
