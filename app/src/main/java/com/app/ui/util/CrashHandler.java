package com.app.ui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
 * 
 * @author user
 * 
 */
@SuppressLint("SdCardPath")
public class CrashHandler implements UncaughtExceptionHandler {

	public static final String TAG = "CRASH";

	// CrashHandler 实例
	private static CrashHandler INSTANCE = new CrashHandler();

	// 程序的 Context 对象
	private Context mContext;

	// 系统默认的 UncaughtException 处理类
	private UncaughtExceptionHandler mDefaultHandler;

	// 用来存储设备信息和异常信息
	private Map<String, String> infos = new HashMap<String, String>();

	// 用来显示Toast中的信息
	private String error = "应用程序错误，请稍后再试";

	private static final Map<String, String> regexMap = new HashMap<String, String>();

	// 用于格式化日期,作为日志文件名的一部分
	private DateFormat formatter = new SimpleDateFormat(SetInfo.FORMAT_DATE_SECOND,
			Locale.CHINA);

	// 讯飞语音
//	private Speaker crashSpeaker;
//	private boolean hasSpeaker = false;

	/** 保证只有一个 CrashHandler 实例 */
	private CrashHandler() {
		//
	}

	/** 获取 CrashHandler 实例 ,单例模式 */
	public static CrashHandler getInstance() {
		// initMap();
		return INSTANCE;
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 */
	public void init(Context context) {
//		hasSpeaker = true;
		mContext = context;
//		crashSpeaker = Speaker.getNingInstance(mContext.getApplicationContext());
		// 获取系统默认的 UncaughtException 处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

		// 设置该 CrashHandler 为程序的默认处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
		Logger.d("TEST", "Crash:init");
	}

	/**
	 * 当 UncaughtException 发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
			Logger.d("TEST", "defalut");
		} else {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				Logger.e(Logger.ERROR, e);
			}
			// 退出程序
			android.os.Process.killProcess(android.os.Process.myPid());
			// mDefaultHandler.uncaughtException(thread, ex);
			System.exit(1);
		}
	}

	/**
	 * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
	 * 
	 * @param ex
	 * @return true：如果处理了该异常信息；否则返回 false
	 */
	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}

		// 收集设备参数信息
		// collectDeviceInfo(mContext);
		// 保存日志文件
		saveCrashInfoInFile(ex);
		// 使用 Toast 来显示异常信息
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
//				Util.makeToast(mContext, error);
				Looper.loop();
			}
		}.start();
		return true;
	}

	/**
	 * 收集设备参数信息
	 * 
	 * @param ctx
	 */
	public String collectDeviceInfo(Context ctx, boolean flag) {
		String string_buf;
		try {
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
					PackageManager.GET_ACTIVITIES);

			if (pi != null) {
				String versionName = pi.versionName == null ? "null"
						: pi.versionName;
				String versionCode = pi.versionCode + "";
				infos.put("versionName", versionName);
				infos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			Logger.e("an error occured when collect package info", e);
			infos.put("versionName", "unknow");
			infos.put("versionCode", "unknow");
		}

		string_buf = "versionName:" + infos.get("versionName")
				+ ", versionCode:" + infos.get("versionCode") + '\n';

		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				infos.put(field.getName(), field.get(null).toString());
				Logger.e(TAG, field.getName() + " : " + field.get(null));
				if (flag)
					string_buf += field.getName() + ": "
							+ infos.get(field.getName()) + '\n';
			} catch (Exception e) {
				Logger.e( "an error occured when collect crash info", e);
			}
		}
		return string_buf;
	}

	/**
	 * 保存错误信息到文件中 *
	 * 
	 * @param ex
	 * @return 返回文件名称,便于将文件传送到服务器
	 */
	private String saveCrashInfoInFile(Throwable ex) {
//		StringBuffer sb = getTraceInfo(ex);
		StringBuffer sb = new StringBuffer();
		Util.makeToast(mContext,sb.toString());
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
//			long timestamp = System.currentTimeMillis();
			SimpleDateFormat sf = new SimpleDateFormat(SetInfo.FORMAT_CRASG_TIME, Locale.CHINA);
			String fileName = "crash_" +  sf.format(new Date())+ "_error.log";
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
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

	/**
	 * 整理异常信息
	 * 
	 * @param e
	 * @return
	 */
	public StringBuffer getTraceInfo(Throwable e) {
		StringBuffer sb = new StringBuffer();
		Throwable ex = e.getCause() == null ? e : e.getCause();
		StackTraceElement[] stacks = ex.getStackTrace();
		sb.append(collectDeviceInfo(mContext, false));
		for (int i = 0; i < stacks.length; i++) {
			if (i == 0) {
				setError(ex.toString());
			}
			sb
//					.append("class: ").append(stacks[i].getClassName())
//					.append("; method: ").append(stacks[i].getMethodName())
//					.append("; line: ").append(stacks[i].getLineNumber())
//					.append(";  Exception: ")
					.append(ex.toString())
					.append("\n");
		}
		Logger.e(TAG, sb.toString());
		return sb;
	}

	/**
	 * 设置错误的提示语
	 * 
	 * @param e
	 */
	public void setError(String e) {
		Pattern pattern;
		Matcher matcher;
		for (Entry<String, String> m : regexMap.entrySet()) {
			Logger.e(TAG, e + "key:" + m.getKey() + "; value:" + m.getValue());
			pattern = Pattern.compile(m.getKey());
			matcher = pattern.matcher(e);
			if (matcher.matches()) {
				error = m.getValue();
				break;
			}
		}
	}

	/**
	 * 初始化错误的提示语
	 */
	@SuppressWarnings("unused")
	private static void initMap() {
		// Java.lang.NullPointerException
		// java.lang.ClassNotFoundException
		// java.lang.ArithmeticException
		// java.lang.ArrayIndexOutOfBoundsException
		// java.lang.IllegalArgumentException
		// java.lang.IllegalAccessException
		// SecturityException
		// NumberFormatException
		// OutOfMemoryError 
		// StackOverflowError 
		// RuntimeException 
		regexMap.put(".*NullPointerException.*", "嘿，无中生有~Boom!");
		regexMap.put(".*ClassNotFoundException.*", "你确定你能找得到它？");
		regexMap.put(".*ArithmeticException.*", "我猜你的数学是体育老师教的，对吧？");
		regexMap.put(".*ArrayIndexOutOfBoundsException.*", "恩，无下限=无节操，请不要跟我搭话");
		regexMap.put(".*IllegalArgumentException.*", "你的出生就是一场错误。");
		regexMap.put(".*IllegalAccessException.*", "很遗憾，你的信用卡账号被冻结了，无权支付");
		regexMap.put(".*SecturityException.*", "死神马上降临");
		regexMap.put(".*NumberFormatException.*", "想要改变一下自己形象？去泰国吧，包你满意");
		regexMap.put(".*OutOfMemoryError.*", "或许你该减减肥了");
		regexMap.put(".*StackOverflowError.*", "啊，啊，憋不住了！");
		regexMap.put(".*RuntimeException.*", "你的人生走错了方向，重来吧");

	}

	public String getError() {
		return error;
	}

}