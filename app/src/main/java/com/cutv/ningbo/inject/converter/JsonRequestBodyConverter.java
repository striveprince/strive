package com.cutv.ningbo.inject.converter;

import android.util.Base64;

import com.cutv.ningbo.data.params.BaseParams;
import com.cutv.ningbo.data.params.DesParams;
import com.cutv.ningbo.data.util.des.DES;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
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
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final DES crypt = new DES(DES.DKDBKEY);
    private final Gson gson;
    private final TypeAdapter<T> adapter;



    JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        String clazz = value.getClass().getName();
        Timber.i("clazz:%1s",clazz);
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        adapter.toJson(writer, value);
        writer.close();
        Timber.i("request value:%1s", gson.toJson(value));
//        return RequestBody.create(MEDIA_TYPE,"data="+crypt.encrypt(buffer.readByteArray()));//des加密
//        RequestBody.create(MEDIA_TYPE,Base64.encode(buffer.readByteArray(), Base64.NO_WRAP));//Base64加密
//        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        FormBody.Builder builder = new FormBody.Builder();
        if(value instanceof DesParams){
            builder.addEncoded("data",crypt.encrypt(buffer.readByteArray()));
        }else if(value instanceof BaseParams) {
            byte[] data = Base64.encode(buffer.readByteArray(), Base64.NO_WRAP);
            builder.addEncoded("data", new String(data, Base64.NO_WRAP));
//        }else if(value instanceof File){
        }else if(value instanceof File[]){
        }
        return builder.build();
    }
}
