package com.cutv.ningbo.inject.converter;

import android.util.Base64;

import com.cutv.ningbo.data.util.Encrypt;
import com.cutv.ningbo.data.util.des.DES;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

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

    @Override public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.close();
//        RequestBody.create(MEDIA_TYPE,crypt.encrypt(buffer.readByteArray()));//des加密
//        RequestBody.create(MEDIA_TYPE,Base64.encode(buffer.readByteArray(), Base64.NO_WRAP));//Base64加密
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());

    }
}
