package com.app.data.params;

import android.support.annotation.NonNull;

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

public class DefaultEncryptParams implements EncryptParams {
    @Override
    public RequestBody transParams() {
        return getRequestBody(this);
    }

    @NonNull
    private RequestBody getRequestBody(Object value) {
        HashMap<String, Object> map = new HashMap<>();
        MultipartBody.Builder builder=null;
        for (Field f : value.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                boolean type = f.getType() == boolean.class;
                char[] cs = f.getName().toCharArray();
                if (cs[0] >= 97 && cs[0] <= 122) cs[0] -= 32;
                Method method = value.getClass().getDeclaredMethod(type ? "is" : "get" + String.valueOf(cs));
                Object o = method.invoke(value);
                if(o == null)continue;
                if (o instanceof File) {
                    builder = getBuilder(builder, f.getName(), (File) o);
                } else if (o instanceof File[]){
                    File[] files = (File[])o;
                    for(int i =0;i<files.length;i++)
                        builder = getBuilder(builder,f.getName()+"["+i+"]",files[i]);
                } else if (o instanceof Integer || o instanceof Boolean || o instanceof Float ||
                        o instanceof Long || o instanceof Double || o instanceof Character) {
                    map.put(f.getName(), o);
                } else map.put(f.getName(), String.valueOf(o));
            } catch (Exception e) {}
        }
        if(builder!=null){
            for(String s:map.keySet()) builder.addFormDataPart(s,String.valueOf(map.get(s)));
            return builder.build();
        }else{
            FormBody.Builder fb = new FormBody.Builder();
            for(String s:map.keySet()) fb.addEncoded(s,String.valueOf(map.get(s)));
            return fb.build();
        }
    }

    @NonNull
    private static MultipartBody.Builder getBuilder(MultipartBody.Builder builder, String params, File o) {
        if (builder == null) builder = new MultipartBody.Builder();
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), o);
        builder.addFormDataPart(params, o.getName(), requestBody);
        return builder;
    }
}
