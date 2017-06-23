package com.read.group.data.params;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by apple on 17/1/20.
 */

public abstract class DknbEncryptParams implements EncryptParams {
    @Override
    public RequestBody transParams() {
        HashMap<String, Object> map = new HashMap<>();
        MultipartBody.Builder builder = null;
        for (Field f : getAllFields(getClass(),new ArrayList<>())) {
            f.setAccessible(true);
            boolean type = f.getType() == boolean.class;
            char[] cs = f.getName().toCharArray();
            if (cs[0] >= 97 && cs[0] <= 122) cs[0] -= 32;
            Object o = invoke(getDeclaredMethod(type ? "is" : "get" + String.valueOf(cs)), this);
            if (o == null) continue;
            if (o instanceof File) {
                builder = getBuilder(builder, f.getName(), (File) o);
            } else if (o instanceof File[]) {
                File[] files = (File[]) o;
                for (int i = 0; i < files.length; i++)
                    builder = getBuilder(builder, f.getName() + "[" + i + "]", files[i]);
            } else if (o instanceof Integer || o instanceof Boolean || o instanceof Float ||
                    o instanceof Long || o instanceof Double || o instanceof Character) {
                map.put(f.getName(), o);
            } else map.put(f.getName(), String.valueOf(o));
        }
        String data = encrypt(new Gson().toJson(map));
        if (builder != null) {
            builder.addFormDataPart("data", data);
            return builder.build();
        } else {
            FormBody.Builder fb = new FormBody.Builder();
            fb.addEncoded("data", data);
            return fb.build();
        }
    }

    private Method getDeclaredMethod(String fieldName) {
        try {
            return getClass().getDeclaredMethod(fieldName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object invoke(Method method, Object o, Object... args) {
        Object obj = null;
        if (o != null)
            try {
                obj = method.invoke(o, args);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        return obj;
    }

    /**
     *
     * @param fields the list of
     */
    private List<Field> getAllFields(Class<?> aClass, List<Field> fields) {
        if (fields == null) fields = new ArrayList<>();
        if (aClass.getSuperclass() != null) {
            Field[] fieldSelf = aClass.getDeclaredFields();
            for (Field field : fieldSelf) {
                if (!Modifier.isFinal(field.getModifiers())) {
                    fields.add(field);
                }
            }
            getAllFields(aClass.getSuperclass(), fields);

        }
        return fields;
    }


    public abstract String encrypt(String json);

    @NonNull
    private static MultipartBody.Builder getBuilder(MultipartBody.Builder builder, String params, File file) {
        if (builder == null) builder = new MultipartBody.Builder();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart(params, file.getName(), requestBody);
        return builder;
    }
}
