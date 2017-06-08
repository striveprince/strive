package com.cutv.ningbo.uim.base;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.cutv.ningbo.uim.base.annotation.AdapterEntity;
import com.cutv.ningbo.uim.base.annotation.ModelView;
import com.cutv.ningbo.uim.base.model.inter.Model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：14:10
 * modify developer：  admin
 * modify time：14:10
 * modify remark：
 *
 * @version 2.0
 */


public class BaseUtil {

    public static Class getSuperGenericType(Class clazz) {
        return getSuperGenericType(clazz, 0);
    }

    public static Class getSuperGenericType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) return Object.class;
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) index = params.length - 1;
        if (!(params[index] instanceof Class) || index == -1) return Object.class;
        return (Class) params[index];
    }


    public static ModelView findModelView(Class<?> thisCls) {
        if (thisCls == null) return null;
        ModelView contentView = thisCls.getAnnotation(ModelView.class);
        if (contentView == null) return findModelView(thisCls.getSuperclass());
        return contentView;
    }

    public static AdapterEntity findAdapterEntity(Class<?> thisCls) {
        if (thisCls == null) return null;
        AdapterEntity contentView = thisCls.getAnnotation(AdapterEntity.class);
        if (contentView == null) return findAdapterEntity(thisCls.getSuperclass());
        return contentView;
    }

    public static ViewGroup.LayoutParams params(View view, boolean parent) {
        ViewGroup.LayoutParams params;
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        if (view != null) {
            if (!parent) view = (View) view.getParent();
            if (view == null) {
                params = new ViewGroup.LayoutParams(width, height);
            } else if (view instanceof RadioGroup) {
                params = new RadioGroup.LayoutParams(width, height);
            } else if (view instanceof LinearLayout) {
                params = new LinearLayout.LayoutParams(width, height);
            } else if (view instanceof RelativeLayout) {
                params = new RelativeLayout.LayoutParams(width, height);
            } else if (view instanceof ViewPager) {
                params = new ViewPager.LayoutParams(view.getContext(), null);
            } else if (view instanceof DrawerLayout) {
                params = new DrawerLayout.LayoutParams(width, height);
            } else if (view instanceof Toolbar) {
                if (Build.VERSION.SDK_INT > 20) {
                    params = new Toolbar.LayoutParams(width, height);
                } else params = new ViewGroup.LayoutParams(width, height);
            } else if (view instanceof RecyclerView) {
                params = new RecyclerView.LayoutParams(width, height);
            } else if (view instanceof SwipeRefreshLayout) {
                params = new SwipeRefreshLayout.LayoutParams(width, height);
            } else if (view instanceof AbsListView) {
                params = new AbsListView.LayoutParams(width, height);
            } else {
                params = new ViewGroup.LayoutParams(width, height);
            }
        } else {
            params = new ViewGroup.LayoutParams(width, height);
        }
        params.width = width;
        params.height = height;
        return params;
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
     * @return the generic types of interface
     * for example :
     * interface I<T>{}
     * interface I1<T>{}
     * class Parent implements I<String>{}
     * class C extends Parent implements I1<Integer>{}
     * Type[] type = getInterfacesGenericTypes(C.class,I.class)
     * type = {String.class}
     */
    public static Class getInterfacesGenericType(Class clazz, Class<?> c) {
        return (Class) getInterfacesGenericTypes(clazz, c)[0];
    }

    public static int getLayoutId(int layoutIndex, Class clazz) {
        ModelView modelView = BaseUtil.findModelView(clazz);
        if (modelView == null)
            throw new RuntimeException(clazz != null ?
                    "this class :" + clazz.getName() + " need to add @ModelView"
                    : "the clazz == null,please use the method setAdapter(Class clazz) before setAdapter");
        int index = layoutIndex;
        if (layoutIndex >= modelView.value().length) index = 0;
        return modelView.value()[index];
    }

    public static <T> T newInstance(Class<T> t, Object... args) {
        T adapter = null;
        if (t != null) {
            if (args.length == 0) {
                try {
                    adapter = t.newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Class[] clazs = new Class[args.length];
                    for (int i = 0; i < args.length; i++)
                        clazs[i] = args[i].getClass();
                    Constructor<T> con = t.getConstructor(clazs);
                    con.newInstance(args);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return adapter;
    }

}
