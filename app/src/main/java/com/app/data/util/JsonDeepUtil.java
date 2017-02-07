package com.app.data.util;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@SuppressWarnings("unchecked")
@SuppressLint("DefaultLocale")
public class JsonDeepUtil {
    private static final JsonDeepUtil jsonDeepUtil = new JsonDeepUtil();

    private JsonDeepUtil() {
    }

    public static JsonDeepUtil getInstance() {
        return jsonDeepUtil;
    }

    /**
     * 使用该方法可以将json字符串转为一个实体类
     *
     * @param json 解析的json
     * @param c    解析类
     * @return array数组，如果不是json字符串，在数组的第一个
     */
    public <T> T[] getEntity(String json, Class<T> c) {
        T[] array = (T[]) Array.newInstance(c, 1);
        if (TextUtils.isEmpty(json)) return array;
        try {
            if (json.charAt(0) == '[') {
                JSONArray jsonArray = new JSONArray(json);
                return getEntity(jsonArray, c);
            } else if (json.charAt(0) == '{') {
                JSONObject jsonObject = new JSONObject(json);
                array[0] = getEntity(jsonObject, c);
            }
        } catch (JSONException e) {
            throw new RuntimeException("please check the json character :" + json, e);
        }

        return array;
    }

    /**
     * @param jsonStr 需要解析的json
     * @param c       解析类
     * @return t实例
     */
    public <T> T getEntityJson(@NonNull String jsonStr, @NonNull Class<T> c) {
        try {
            if (!TextUtils.isEmpty(jsonStr)) return getEntity(new JSONObject(jsonStr), c);
            else return c.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("the json is a wrong format:", e);
        }
    }

    public <T> T getEntity(JSONObject jsonObject, @NonNull Class<T> c) {
        try {
            return getEntity(jsonObject, c.newInstance());
        } catch (Exception e) {
            throw new RuntimeException("can create this obj:" + c.getName() + " please check you constructor type!", e);
        }
    }


    /**
     * use this method can change json to be a object
     *
     * @param jsonObject jsonObject
     * @param t          object of can to be a no data
     * @return t return a object of has data;
     */
    public <T> T getEntity(JSONObject jsonObject, @NonNull T t) {
        Class c = t.getClass();
        Field[] fs = c.getDeclaredFields();
        for (Field f : fs) {
            Class clazz = f.getType();
            String fName = f.getName();
            if (TextUtils.isEmpty(fName)||jsonObject.isNull(fName)) continue;
            f.setAccessible(true);
            char[] cs = fName.toCharArray();
            if (cs[0] >= 97 && cs[0] <= 122) cs[0] -= 32;//匹配ascii码表，发现是小写字母的话，-32变为小写
            String keyU = String.valueOf(cs);
            Method declareMethod;
            try {
                declareMethod = c.getDeclaredMethod("set" + keyU, clazz);
            } catch (Exception e) {
//                Logger.w(Logger.JSONERROR, "can't find the method: set" + keyU + " ("
//                        + clazz.getName() + " " + keyU + "), please check the method in class");
                continue;
            }
            boolean lean = clazz.isArray();
            Object obj = getValue(fName, jsonObject, clazz);
            if (obj == null) continue;
            if (obj instanceof JSONArray && lean) {
                JSONArray jsonArray = (JSONArray) obj;
                Class<?> clazs = clazz.getComponentType();
                try {
                    Object[] array = (Object[]) Array.newInstance(clazs, jsonArray.length());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            array[i] = getEntity(jsonArray.getJSONObject(i), clazs);
                        } catch (Exception e1) {
//                            Logger.w(Logger.JSONERROR, "getEntity in key:" + keyU + " json:" + jsonObject);
                        }
                    }
                    invoke(declareMethod, t, new Object[]{array});
                } catch (ClassCastException e) {
                    if (clazs == int.class) {
                        int[] array = (int[]) Array.newInstance(clazs, jsonArray.length());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            array[i] = getIntValue(i,jsonArray);//jsonArray.getInt(i);
                        }
                        invoke(declareMethod, t, new Object[]{array});
                    } else if (clazs == long.class) {
                        long[] array = (long[]) Array.newInstance(clazs, jsonArray.length());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            array[i] = getLongValue(i,jsonArray);//jsonArray.getLong(i);
                        }
                        invoke(declareMethod, t, new Object[]{array});
                    } else if (clazs == boolean.class) {
                        boolean[] array = (boolean[]) Array.newInstance(clazs, jsonArray.length());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            array[i] = getBooleanValue(i,jsonArray);//jsonArray.getBoolean(i);
                        }
                        invoke(declareMethod, t, new Object[]{array});
                    } else if (clazs == double.class) {
                        double[] array = (double[]) Array.newInstance(clazs, jsonArray.length());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            array[i] = getDoubleValue(i,jsonArray);//jsonArray.getDouble(i);
                        }
                        invoke(declareMethod, t, new Object[]{array});
                    }
                }
                //give the type and json is wrong，when json is JSONObject，but field is array,set json content to array[0];
            } else if (obj instanceof JSONObject) {
                if (lean) {
                    Class<?> clazs = clazz.getComponentType();
                    Object[] array = (Object[]) Array.newInstance(clazs, 1);
                    array[0] = getEntity((JSONObject) obj, clazz);
                    invoke(declareMethod, t, new Object[]{array});
                } else {
                    invoke(declareMethod, t, getEntity((JSONObject) obj, clazz));
                }
            } else {
                invoke(declareMethod, t, obj);
            }
        }
        return t;
    }

    private int getIntValue(int i,JSONArray jsonArray){
        try {
            return jsonArray.getInt(i);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private long getLongValue(int i,JSONArray jsonArray){
        try {
            return jsonArray.getLong(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    private Double getDoubleValue(int i,JSONArray jsonArray){
        try {
            return jsonArray.getDouble(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0.0d;
    }
    private boolean getBooleanValue(int i,JSONArray jsonArray){
        try {
            return jsonArray.getBoolean(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void invoke(Method method, Object t, Object... args) {
        try {
            method.invoke(t, args);
        } catch (Exception e) {
//            Logger.e(Logger.JSONERROR, "method:" + method.getName() + "\t t:" + "\t args：" + args[0]);
        }
    }

    /**
     * use this method can change json to be a array object
     *
     * @param jsonArray jsonArray
     * @param c         generics type
     * @return array jsonArray object
     */
    public <T> T[] getEntity(JSONArray jsonArray, Class<T> c) {
        int len = jsonArray.length();
        T[] array = (T[]) Array.newInstance(c, len);
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObjectArray = jsonArray.getJSONObject(i);
                array[i] = getEntity(jsonObjectArray, c);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return array;
    }


    /**
     * 遍历json中的key值，让其与实体类属性名匹配,匹配成功则取出值
     *
     * @param key        类中的key，做为json的key取出
     * @param jsonObject 需要解析的json
     * @param clazz      value的类型
     * @return obj value的值
     */
    private Object getValue(String key, JSONObject jsonObject, Class clazz) {
//        jsonObject.keys();
        try {
            if (clazz == String.class) {
                return jsonObject.getString(key);
            } else if (clazz == int.class) {
                return jsonObject.getInt(key);
            } else if (clazz == long.class) {
                return jsonObject.getLong(key);
            } else if (clazz == boolean.class) {
                return jsonObject.getBoolean(key);
            } else if (clazz == double.class) {
                return jsonObject.getDouble(key);
            } else if (clazz == JSONObject.class) {
                return jsonObject.getJSONObject(key);
            } else if (clazz == JSONArray.class) {
                return jsonObject.getJSONArray(key);
            } else {
                return jsonObject.get(key);
            }
        } catch (Exception e) {
//            Logger.w(Logger.JSONERROR, " getValue:key:" + key + "  json:" + jsonObject.toString());
        }
        return null;
    }
}
