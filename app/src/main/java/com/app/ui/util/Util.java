package com.app.ui.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.app.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * project：cutv_ningbo
 * description：
 * create developer： Arvin
 * create time：2016/5/11 14:47.
 * modify developer：  Arvin
 * modify time：2016/5/11 14:47.
 * modify remark：
 *
 * @version 2.0
 */
public class Util {
//    private static boolean flag = true;



    public static void setText(TextView view, CharSequence text) {
        boolean empty = TextUtils.isEmpty(text);
        if (view != null) view.setText(empty ? view.getText().toString() : text);
    }

//    public static void setText(TextView view,@StringRes int id) {
//        boolean empty = TextUtils.isEmpty(text) || TextUtils.isEmpty(text.trim());
//        if (view != null) view.setText(empty ? view.getText().toString() : text);
//    }

    public static boolean makeToast(Activity context, Object text, int duration) {
        if (text != null) {
            boolean flag = context != null && !TextUtils.isEmpty(text.toString());
            if (flag) Toast.makeText(context, text.toString(), duration).show();
            return flag;
        }
        return false;
    }

    public static boolean makeToast(Context context, Object text) {
        if (text != null) {
            boolean flag = context != null && !TextUtils.isEmpty(text.toString());
            if (flag) Toast.makeText(context, text.toString(), Toast.LENGTH_SHORT).show();
            return flag;
        }
        return false;
    }

    public static boolean makeToast(Activity context, Object text) {
        return makeToast(context, text, Toast.LENGTH_SHORT);
    }

    public static boolean makeToastLong(Activity context, Object text) {
        return makeToast(context, text, Toast.LENGTH_LONG);
    }

    public static boolean makeToast(Activity context, int id, int duration) {
        boolean flag = context != null;
        if (flag) Toast.makeText(context, context.getResources().getString(id), duration).show();
        return flag;
    }

    public static boolean makeToast(Activity context, int id) {
        boolean flag = context != null;
        if (flag) makeToast(context, id, Toast.LENGTH_SHORT);
        return flag;
    }

    // 跳转方法
    public static void setIntent(Context context, Class<?> targetActivity) {
        setIntent(context, targetActivity, null, null);
    }


    public static void setIntent(Context context, Class<?> targetActivity, Bundle b) {
        setIntent(context, targetActivity, b, null);
    }

    public static void setIntent(Context context, Class<?> targetActivity, String title) {
        setIntent(context, targetActivity, new Bundle(), title);
    }

    public static void setRequestIntent(Context context, Class<?> targetActivity, Bundle b, int requestCode, String title) {
        Intent intent = new Intent(context, targetActivity);
        if (b != null) {
            if (!TextUtils.isEmpty(title)) b.putString(SetInfo.TITLE, title);
            intent.putExtras(b);
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            activity.startActivityForResult(intent, requestCode);
            activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
        }
    }



    public static void setIntent(Context context, Class<?> targetActivity, Bundle b, String title) {
        Intent intent = new Intent(context, targetActivity);
        if (b == null) b = new Bundle();
        if (!TextUtils.isEmpty(title)) b.putString(SetInfo.TITLE, title);
        intent.putExtras(b);
        if (context instanceof Activity) {
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }

    }


    public static String format(Activity activity, int i) {
        return format(activity, R.string.integer, i);
    }

    public static String format(Activity activity, @StringRes int id, Object str) {
        if (activity == null) return "";
        String integer = activity.getString(id);
        return String.format(integer, str);
    }

    @TargetApi(19)
    public static String getImageAbsolutePath(Activity context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        if (cursor != null) cursor.close();
        return res;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }


    /**
     * 把Uri转化成文件路径
     */
    public static String uri2filePath(Context context, Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
        String picturePath = "";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                int columnIndex = cursor.getColumnIndexOrThrow(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
            }
            cursor.close();
        } else {
            if (uri != null) {
                String tmpPath = uri.getPath();
                picturePath = tmpPath;
            }
        }
        return picturePath;
    }

    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }


}
