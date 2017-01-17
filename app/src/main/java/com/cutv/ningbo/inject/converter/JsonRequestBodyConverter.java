package com.cutv.ningbo.inject.converter;

import android.util.Base64;

import com.cutv.ningbo.data.params.BaseParams;
import com.cutv.ningbo.data.params.DesParams;
import com.cutv.ningbo.data.util.des.DES;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Converter;
import timber.log.Timber;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：15:04
 * modify developer：  admin
 * modify time：15:04
 * modify remark：
 *
 * @version 2.0
 */


public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
//    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
//    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final DES crypt = new DES(DES.DKDBKEY);
    private final Gson gson;
//    private final TypeAdapter<T> adapter;
    private HashMap<String,String> stringHashMap = new HashMap<>();
    private HashMap<String,File> fileHashMap = new HashMap<>();




    JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
//        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
//        String clazz = value.getClass().getName();
//        Timber.i("clazz:%1s",clazz);
//        Buffer buffer = new Buffer();
//        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
//        adapter.toJson(writer, value);
//        writer.close();
        Timber.i("request value:%1s", gson.toJson(value));
//        return RequestBody.create(MEDIA_TYPE,"data="+crypt.encrypt(buffer.readByteArray()));//des加密
//        RequestBody.create(MEDIA_TYPE,Base64.encode(buffer.readByteArray(), Base64.NO_WRAP));//Base64加密
//        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        return getBuilder(value);
    }


    private RequestBody getBuilder(T value) {
        stringHashMap.clear();
        fileHashMap.clear();
        Class<T> c  = (Class<T>) value.getClass();
        for(Field f:c.getDeclaredFields()){
            try {
                boolean type = f.getType() == boolean.class;
                Method method = c.getDeclaredMethod(type?"is":"get"+f.getName());
                Object o = method.invoke(value);
                if(o!=null){
                    if(o instanceof String) stringHashMap.put(f.getName(),o.toString());
                    else if(o instanceof File)fileHashMap.put(f.getName(),(File)o);
                    else if(o instanceof File[]) {
                        File[] files = (File[])o;
                        for(int i = 0;i<files.length;i++)
                            fileHashMap.put(f.getName()+"["+i+"]",files[i]);

                    }
                }
            }catch (Exception e){}
        }
        String json = gson.toJson(stringHashMap);
        if(value instanceof DesParams){
            if(fileHashMap.size()==0){
                FormBody.Builder builder = new FormBody.Builder();
                builder.addEncoded("data",crypt.encrypt(json));
                return builder.build();
            }else {
                MultipartBody.Builder multiBuilder = new MultipartBody.Builder();
                multiBuilder.addFormDataPart("data",crypt.encrypt(json));
                for(String key:fileHashMap.keySet()){
                    File file = fileHashMap.get(key);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                    multiBuilder.addFormDataPart(key,file.getName(),requestBody);
                }
                return multiBuilder.build();
            }
        }else if(value instanceof BaseParams) {
            if(fileHashMap.size() ==0){
                FormBody.Builder builder = new FormBody.Builder();
                byte[] data = Base64.encode(json.getBytes(), Base64.NO_WRAP);
                builder.addEncoded("data", new String(data, Base64.NO_WRAP));
                return builder.build();
            }else{
                MultipartBody.Builder multiBuilder = new MultipartBody.Builder();
                byte[] data = Base64.encode(json.getBytes(), Base64.NO_WRAP);
                multiBuilder.addFormDataPart("data", new String(data, Base64.NO_WRAP));
                for(String key:fileHashMap.keySet()){
                    File file = fileHashMap.get(key);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                    multiBuilder.addFormDataPart(key,file.getName(),requestBody);
                }
                return multiBuilder.build();
            }
        }
        return null;
    }

}
