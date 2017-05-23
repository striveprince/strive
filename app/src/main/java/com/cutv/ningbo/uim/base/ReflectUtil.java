package com.cutv.ningbo.uim.base;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

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



    public static List<Field> getAllFields(Class<?> aClass) {
        return getAllFields(aClass, null);
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

    private static Method beanMethod(Field f, Class c, String prefix) {
        f.setAccessible(true);
        char[] cs = f.getName().toCharArray();
        if (cs[0] >= 97 && cs[0] <= 122) cs[0] -= 32;
        Method method = null;
        try {
            method = c.getClass().getDeclaredMethod(prefix + String.valueOf(cs));
        } catch (NoSuchMethodException e) {
//            Logger.JsonError("can't find this method(" + prefix + String.valueOf(cs) + ")");
            e.printStackTrace();
        }
        return method;
    }


    public static Type[] getFieldGenericType(Field field) {
        field.setAccessible(true);
        Type fieldType = field.getGenericType();
        if (fieldType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) fieldType;
            return parameterizedType.getActualTypeArguments();
        }
        return new Type[0];
    }

    public static Method beanSetMethod(Field f, Class c) {
        return beanMethod(f, c, "set");
    }

    public static Method beanGetMethod(Field f, Class c) {
        return beanMethod(f, c, f.getType() == boolean.class ? "is" : "get");
    }


    public static Type[] getReturnGenericType(Method method) {
        Type classType = method.getGenericReturnType();
        if (classType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) classType;
            return parameterizedType.getActualTypeArguments();
        }
        return new Type[0];
    }

//    public static Type[] getSuperGenericType(Class clazz) {
//        Type classType = clazz.getGenericSuperclass();
//        if (classType instanceof ParameterizedType) {
//            ParameterizedType parameterizedType = (ParameterizedType) classType;
//            return parameterizedType.getActualTypeArguments();
//        }
//        return new Type[0];
//    }


    public static Class getSuperGenericType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) return Object.class;
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) index = params.length - 1;
        if (!(params[index] instanceof Class) || index == -1) return Object.class;
        return (Class) params[index];
    }

    public static Class getSuperGenericType(Class clazz) {
        return getSuperGenericType(clazz, 0);
    }


    public static Type[] getSuperGenericTypes(Class clazz) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) return new Type[0];
        return ((ParameterizedType) genType).getActualTypeArguments();
    }


    public static List<Class> getGenericType(Field field) {
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

    public static void invoke(Method method, Object t, Object... args) {
        try {
            method.invoke(t, args);
        } catch (Exception e) {
//            Logger.e(Logger.JSONERROR, "method:" + method.getName() + "\t t:" + "\t args：" + args[0]);
        }
    }

    /**
     * @param clazz the current class
     * @param c     the class of the interface
     * @return the generic types of interface
     * for example :
     * interface I<T>{}
     * interface I1<T>{}
     * class Parent implements I<String>{}
     * class C extends Parent implements I1<Integer>{}
     * Type[] type = getInterfacesGenericTypes(C.class,I.class)
     * type = {String.class}
     */
    public static Type[] getInterfacesGenericTypes(Class clazz, Class<?> c) {
        if (clazz == null) return new Type[0];
        Class<?>[] cls = clazz.getInterfaces();
        for (int index = 0; index < cls.length; index++) {
            if (c.isAssignableFrom(cls[index])) {
                Type genType = clazz.getGenericInterfaces()[index];
                return genType instanceof ParameterizedType ? ((ParameterizedType) genType).getActualTypeArguments() : new Type[0];
            }
        }
        return getInterfacesGenericTypes(clazz.getSuperclass(), c);
    }

    /**
     * @param clazz the current class
     * @param c     the class of the interface
     * @return the generic types of interface or class
     * for example :
     * interface I<T>{}
     * interface I1<T>{}
     * class Parent<T></> implements I<T>{}
     * class C extends Parent<String> implements I1<Integer>{}
     * Type[] type = getGenericTypes(C.class,I.class)
     * type = {T.class}
     */
    public static Class getGenericTypes(Class clazz, Class<?> c, int index) {
        if (clazz == null) return null;
        Class<?> superClass = clazz.getSuperclass();

        Class<?>[] cls = clazz.getInterfaces();
        for (int i = 0; i < cls.length; i++) {
            if (c.isAssignableFrom(cls[i])) {
                Type genType = clazz.getGenericInterfaces()[i];
                if (genType instanceof ParameterizedType) {
                    Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
                    if (types[index] instanceof Class) {
                        return (Class) types[index];
                    } else if (types[index] instanceof TypeVariable) {
                        return getClass(((TypeVariable) types[index]).getBounds()[0], 0);
                    }
                } else {
                    return null;
                }
            }
        }
        return getGenericTypes(clazz.getSuperclass(), c, index);
    }

    private static Class getGenericClass(ParameterizedType parameterizedType, int i) {
        Object genericClass = parameterizedType.getActualTypeArguments()[i];
        if (genericClass instanceof ParameterizedType) { // 处理多级泛型
            return (Class) ((ParameterizedType) genericClass).getRawType();
        } else if (genericClass instanceof GenericArrayType) { // 处理数组泛型
            return (Class) ((GenericArrayType) genericClass).getGenericComponentType();
        } else if (genericClass instanceof TypeVariable) { // 处理泛型擦拭对象
            return getClass(((TypeVariable) genericClass).getBounds()[0], 0);
        } else {
            return (Class) genericClass;
        }
    }
    private static Class getClass(Type type, int i) {
        if (type instanceof ParameterizedType) { // 处理泛型类型
            return getGenericClass((ParameterizedType) type, i);
        } else if (type instanceof TypeVariable) {
            return getClass(((TypeVariable) type).getBounds()[0], 0); // 处理泛型擦拭对象
        } else {// class本身也是type，强制转型
            return (Class) type;
        }
    }
}
