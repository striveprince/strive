package com.app.data.params;

import android.support.annotation.NonNull;

import com.app.inject.reflect.ReflectUtil;
import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * project：cutv_ningbo
 * description：
 * create developer： apple
 * create time：10:46
 * modify developer：  apple
 * modify time：10:46
 * modify remark：
 * @version 2.0
 */

public abstract class DknbEncryptParams implements EncryptParams {
    @Override
    public RequestBody transParams() {
        HashMap<String, Object> map = new HashMap<>();
        MultipartBody.Builder builder = null;
        for (Field f : ReflectUtil.getAllFields(getClass())) {
            Method method = ReflectUtil.beanGetMethod(f, getClass());
            if (method == null) continue;
            Object obj;
            try {
                obj = method.invoke(this);
            } catch (Exception e) {
                continue;
            }
            if (obj == null) continue;
            if (obj instanceof File) {
                builder = getBuilder(builder, f.getName(), (File) obj);
            } else if (obj instanceof File[]) {
                File[] files = (File[]) obj;
                for (int i = 0; i < files.length; i++)
                    builder = getBuilder(builder, f.getName() + "[" + i + "]", files[i]);
            } else if (obj instanceof Integer || obj instanceof Boolean || obj instanceof Float ||
                    obj instanceof Long || obj instanceof Double || obj instanceof Character) {
                map.put(f.getName(), obj);
            } else map.put(f.getName(), String.valueOf(obj));
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

    public abstract String encrypt(String json);

    @NonNull
    private static MultipartBody.Builder getBuilder(MultipartBody.Builder builder, String params, File file) {
        if (builder == null) builder = new MultipartBody.Builder();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        builder.addFormDataPart(params, file.getName(), requestBody);
        return builder;
    }
}
