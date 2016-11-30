package com.cutv.ningbo.data.save;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

/**
 * project：cutv_ningbo
 * description：
 * create developer： Arvin
 * create time：2015/12/15 10:44.
 * modify developer：  Arvin
 * modify time：2015/12/15 10:44.
 * modify remark：
 *
 * @version 2.0
 */
public class SharePreferenceUtil {


    private static final String NINGBO = "ningbo";
    private static final String USER = "user";
    private static final String OLDER = "my.config";
    private static final String BROKE = "broke";
    private static final String SYSTEM = "system";

    private static SharePreferenceUtil ningShare;
    private static SharePreferenceUtil userShare;
    private static SharePreferenceUtil brokeShare;
    private static SharePreferenceUtil oldShare;
    private static SharePreferenceUtil systemShare;

    private SharedPreferences share;
    private SharedPreferences.Editor editor;
//    private static final String name = "user";



    private SharePreferenceUtil(Context context, String name) {
        share = context.getSharedPreferences(name, Activity.MODE_PRIVATE);
        editor = share.edit();
        editor.apply();
    }

    public static SharePreferenceUtil getUserInstance(Context context){
        SharePreferenceUtil util = userShare;
        if (util == null) {
            synchronized (SharePreferenceUtil.class) {
                util = userShare;
                if (util == null) {
                    util = new SharePreferenceUtil(context, USER);
                    userShare = util;
                }
            }
        }
        return util;
    }
    public static SharePreferenceUtil getOldInstance(Context context){
        SharePreferenceUtil util = oldShare;
        if (util == null) {
            synchronized (SharePreferenceUtil.class) {
                util = oldShare;
                if (util == null) {
                    util = new SharePreferenceUtil(context, OLDER);
                    oldShare = util;
                }
            }
        }
        return util;
    }

    public static SharePreferenceUtil getNingInstance(Context context) {
        SharePreferenceUtil util = ningShare;
        if (util == null) {
            synchronized (SharePreferenceUtil.class) {
                util = ningShare;
                if (util == null) {
                    util = new SharePreferenceUtil(context, NINGBO);
                    ningShare = util;
                }
            }
        }
        return util;
    }

    public static SharePreferenceUtil getBrokeInstance(Context context) {
        SharePreferenceUtil util = brokeShare;
        if (util == null) {
            synchronized (SharePreferenceUtil.class) {
                util = brokeShare;
                if (util == null) {
                    util = new SharePreferenceUtil(context, BROKE);
                    brokeShare = util;
                }
            }
        }
        return util;
    }
    public static SharePreferenceUtil getSystemInstance(Context context) {
        SharePreferenceUtil util = systemShare;
        if (util == null) {
            synchronized (SharePreferenceUtil.class) {
                util = systemShare;
                if (util == null) {
                    util = new SharePreferenceUtil(context, SYSTEM);
                    systemShare = util;
                }
            }
        }
        return util;
    }


//    public static SharePreferenceUtil getNingInstance(Context context, String name) {
//        SharePreferenceUtil util = ningShare;
//        if (util == null) {
//            synchronized (SharePreferenceUtil.class) {
//                util = ningShare;
//                if (util == null) {
//                    util = new SharePreferenceUtil(context, name);
//                    ningShare = util;
//                }
//            }
//        }
//        return util;
//    }

    public SharedPreferences getShare() {
        return share;
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    private void putValue(String key, @NonNull Object value){
        Class clazz = value.getClass().getComponentType();
        if (value instanceof String) {
            editor.putString(key, value.toString());
        } else if (clazz == boolean.class || value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (clazz == float.class || value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (clazz == int.class || value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (clazz == long.class || value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Set) {
//            Type type = value.getClass().getGenericSuperclass();
//            if(type instanceof ParameterizedType){
//                Type[] actualType1 = ((ParameterizedType)type).getActualTypeArguments();
//                if(actualType1.length==1&&actualType1[0] == String.class)
//            }
//            else
//                Logger.w(Logger.SHARE,"please ch");
            editor.putStringSet(key, (Set<String>) value);
        } else {
//            Logger.w(Logger.SHARE, "key:" + key + ",value:" + value);
        }
    }

    /**
     * put the information to the memory,please put the value class is instanceof String,boolean,float,long,int and Set<String>
     * can't put the other type to the memory,otherwise,the method will not set the value to the memory,and if the value is
     * instanceof Set<?> ,but It isn't instanceof Set<String>,this will throw activity ClassCastException;
     *
     * @param key   key
     * @param value value
     */
    public void setValue(String key, @NonNull Object value) {
        putValue(key,value);
        editor.commit();
    }

    public <T> void setAllDto(T t) {
        Class clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
               if(f.get(t)!=null)putValue(f.getName(),f.get(t));
            } catch (IllegalAccessException e) {
//                Logger.w(Logger.SHARE, "Field:" + f.getName() + " class:" + t.getClass().getName());
            }
//            String head = (f.getDeclaringClass() == boolean.class) ? "is" : "get";
//            String mn = f.getName();
//            String fristMn = (mn.charAt(0) + "").toUpperCase();
//            mn = fristMn + mn.substring(1);
//            mn = head + mn;
//            try {
//                Method method = clazz.getDeclaredMethod(mn);
//                Object obj = method.invoke(t);
//                setValue(f.getName(), obj);
//            } catch (Exception e) {
//                Logger.w(Logger.SHARE, "method:" + mn + " It's activity wrong method,please check the class of" +
//                        t.getClass().getName() + "have this method");
//            }
        }
        editor.commit();
    }

    public String getString(String key) {
        return share.getString(key, "");
    }

    public Map<String, ?> getAll() {
        return share.getAll();
    }


    public <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("please check the construct of the " + clazz.getName() +
                    ", \tthe class of construct must be have activity no parameter ", e);
        }
    }

    public <T> T getAllDto(Class<T> c, SharedPreferences share) {
        T t = newInstance(c);
        return getAllDto(t, c, share);
    }

    @SuppressWarnings("unchecked")
    public <T> T getAllDto(T t, SharedPreferences share) {
        return getAllDto(t, (Class<T>) t.getClass(), share);
    }

    private <T> T getAllDto(@NonNull T t, Class<T> clazz, SharedPreferences share) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            Object value = getValue(f.getName(), f.getType(), share);
            String mn = f.getName();
            String fristMn = (mn.charAt(0) + "").toUpperCase();
            String upmn = fristMn + mn.substring(1);
            String setupmn = "set" + upmn;
            try {
                Method method = clazz.getDeclaredMethod(setupmn, f.getType());
                method.invoke(t, value);
            } catch (Exception e) {
//                Logger.i(Logger.SHARE, "method:" + setupmn + "field:" + f.getName());
            }
        }
        return t;
    }

    public <T> T getAllDto(Class<T> clazz) {
        return getAllDto(clazz, share);
    }

    public <E> E getValue(String key, Class<E> clazz) {
        return getValue(key, clazz, share);
    }

    /**
     * 遍历Share中的key值，让其与实体类属性名匹配,匹配成功则取出值
     */
    @SuppressWarnings("unchecked")
    private <E> E getValue(String key, Class<E> clazz, SharedPreferences share) {
        if (clazz == String.class) {
            return (E) share.getString(key, "");
        } else if (clazz == int.class) {
            Integer result = share.getInt(key, 0);
            return (E) result;
        } else if (clazz == long.class) {
            Long result = share.getLong(key, 0);
            return (E) result;
        } else if (clazz == boolean.class) {
            Boolean result = share.getBoolean(key, false);
            return (E) result;
        } else if (clazz == float.class) {
            Float result = share.getFloat(key, 0);
            return (E) result;
        } else if (clazz == Set.class) {
            Set<String> result = share.getStringSet(key, null);
            return (E) result;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <E> E getValue(String key, E e, SharedPreferences share) {
        if (e instanceof String) {
            return (E) share.getString(key, "");
        } else if (e.getClass() == int.class) {
            Integer result = share.getInt(key, 0);
            return (E) result;
        } else if (e.getClass() == long.class) {
            Long result = share.getLong(key, 0);
            return (E) result;
        } else if (e.getClass() == boolean.class) {
            Boolean result = share.getBoolean(key, false);
            return (E) result;
        } else if (e.getClass() == float.class) {
            Float result = share.getFloat(key, 0);
            return (E) result;
        } else if (e instanceof Set) {
            Set<String> result = share.getStringSet(key, null);
            return (E) result;
        }
        return null;
    }
}
