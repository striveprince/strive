package com.cutv.ningbo.data.params;

import android.support.annotation.NonNull;

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
 * Created by apple on 17/1/20.
 */

public abstract class DknbEncryptParams implements EncryptParams {
    @Override
    public RequestBody transParams() {
        HashMap<String, Object> map = new HashMap<>();
        MultipartBody.Builder builder = null;
        for (Field f : getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                boolean type = f.getType() == boolean.class;
                char[] cs = f.getName().toCharArray();
                if (cs[0] >= 97 && cs[0] <= 122) cs[0] -= 32;
                Method method = getClass().getDeclaredMethod(type ? "is" : "get" + String.valueOf(cs));
                Object o = method.invoke(this);
                if(o == null)continue;
                if (o instanceof File) {
                    builder = getBuilder(builder, f.getName(), (File) o);
                } else if (o instanceof File[]){
                    File[] files = (File[])o;
                    for(int i =0;i<files.length;i++)builder = getBuilder(builder,f.getName()+"["+i+"]",files[i]);
                } else if (o instanceof Integer || o instanceof Boolean || o instanceof Float ||
                        o instanceof Long || o instanceof Double || o instanceof Character) {
                    map.put(f.getName(), o);
                } else map.put(f.getName(), String.valueOf(o));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String data = encrypt(new Gson().toJson(map));
        if(builder!=null){
            builder.addFormDataPart("data",data);
            return builder.build();
        }else{
            FormBody.Builder fb = new FormBody.Builder();
            fb.addEncoded("data",data);
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
