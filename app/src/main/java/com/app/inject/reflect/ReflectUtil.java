package com.app.inject.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：9:37
 * modify developer：  admin
 * modify time：9:37
 * modify remark：
 *
 * @version 2.0
 */


public class ReflectUtil {

    public static List<Field> getAllFields(Class<?> aClass){
        return getAllFields(aClass,null);
    }

    private static List<Field> getAllFields(Class<?> aClass, List<Field> fields) {
        if (fields == null) fields = new ArrayList<>();
        if (aClass.getSuperclass() != null) {
            for (Field field : aClass.getDeclaredFields()) {
                if (!Modifier.isFinal(field.getModifiers())) fields.add(field);
            }
            getAllFields(aClass.getSuperclass(), fields);
        }
        return fields;
    }

    private static Method beanMethod(Field f,Class c,String prefix){
        f.setAccessible(true);
        char[] cs = f.getName().toCharArray();
        if (cs[0] >= 97 && cs[0] <= 122) cs[0] -= 32;
        Method method = null;
        try {
            method = c.getClass().getDeclaredMethod(prefix + String.valueOf(cs));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    public static Method beanSetMethod(Field f,Class c){
        return beanMethod(f,c,"set");
    }

    public static Method beanGetMethod(Field f,Class c){
        return beanMethod(f,c,f.getType() == boolean.class ? "is" : "get");
    }

    public static List<Class> getGenericType(Field field){
        List<Class> list = new ArrayList<>();
        Type fieldType = field.getGenericType();
        if (fieldType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) fieldType;
            Type[] fieldArgType = parameterizedType.getActualTypeArguments();
            for (Type type : fieldArgType) {
                list.add((Class<?>) type);
            }
            return list;
        }
        return list;
    }

}
