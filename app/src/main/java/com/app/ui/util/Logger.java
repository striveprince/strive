package com.app.ui.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Logger {
    public static boolean LOGCAT = true;
    public static final String DTO = "dknb dto toString";
    public static final String DTOERROR = "dknb dto error msg";

    public static final String USER="dknb user";
    public static final String CLICK = "dknb click";
    public static final String HTTPIMAGE="dknb http image url";
    public static final String HTTPURL = "dknb http url";
    public static final String HTTPRESULT = "dknb http result";
    public static final String ANIMATION = "dknb http animation";
    public static final String HTTPCACHE = "dknb http cache";
    public static final String HTTPERROR = "dknb http error";
    public static final String WEBVIEW = "dknb web view";
    public static final String WEBVIEWURL = "dknb web view url";
    public static final String SHARE = "dknb share";
    public static final String MEDIA = "dknb media";
    public static final String MEDIAURL = "dknb media url";
    public static final String MEDIAERROR = "dknb media error";
    public static final String ADAPTER = "dknb adapter";
    public static final String SENSOR = "dknb sensor";
    public static final String JSON = "dknb json";
    public static final String JSONERROR = "dknb json error";
    public static final String CLASS = "dknb class";
    public static final String ERROR = "dknb error";
    public static final String UTIL = "dknb util";
    public static final String VIEW = "dknb view";
    public static final String HANDLER = "dknb handler";
    public static final String FILE = "dknb file";
    public static final String FILEERROR = "dknb file error";
    public static final String JavascriptInterface = "dknb js interface";
    public static final String SHARELOGIN = "dknb share login";
    public static final String ACTIVITY  = "dknb activity";
    public static final String DB = "dknb db";
    public static final String JPUSH = "dknb jpush";
    public static final String SINGLE = "dknb single";
    public static final String TIME = "dknb http time";
    public static final String TEXT = "dknb_text";

//    public static final String[] TAGS = new String[]{HTTPURL, HTTPRESULT, HTTPERROR, WEBVIEW,
//            WEBVIEWURL, SHARE, MEDIA,MEDIAERROR, ADAPTER, SENSOR, JSON,JSONERROR, CLASS, ERROR};

    public static void text(String text){
        i(TEXT,text);
    }

    public static void e(String tag, Object msg) {
        if(LOGCAT) Log.e(tag, msg.toString());
    }

    public static void d(String tag, Object msg) {
        if(LOGCAT) Log.d(tag, msg.toString());
    }

    public static void v(String tag, Object msg) {
        if(LOGCAT) Log.v(tag, msg.toString());
    }

    public static void w(String tag, Object msg) {
        if(LOGCAT) Log.w(tag, msg.toString());
    }

    public static void i(String tag, Object msg) {
        if(LOGCAT) Log.i(tag, msg.toString());
    }

    public static void time(Object msg) {
        if(LOGCAT) Log.i(TIME,msg.toString()+" time:"+formatter.format(new Date()));
    }

    private static DateFormat formatter = new SimpleDateFormat(SetInfo.FORMAT_DATE_ALL, Locale.CHINA);

    /**
     * 保存错误信息到文件中 *
     * @param ex
     * @return 返回文件名称,便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {
//        StringBuffer sb = getTraceInfo(ex);
        StringBuffer sb = new StringBuffer();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();

        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";

            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStorageDirectory()
                        + "/ningbo/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }

            return fileName;
        } catch (Exception e) {
            Logger.e( "an error occured while writing file...", e);
        }

        return null;
    }
}