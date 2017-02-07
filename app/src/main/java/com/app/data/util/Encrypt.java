package com.app.data.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * project：cutv_ningbo
 * description：
 * create developer： Arvin
 * create time：2015/12/14 11:34.
 * modify developer：  Arvin
 * modify time：2015/12/14 11:34.
 * modify remark：
 *
 * @version 2.0
 */
public class Encrypt {

    private static final String NBTV2015 = "|hijkappnbtv2015abcd|";
    private static final String NBTVWEB = "zxycappnbtvh52015zxcv|";

    private static final String NBTVDATA = "dknbappnbtv2016data";
//    public static void main() {
//        String str = "nbtvapp2015";
//        String str1 = string2MD5(str);
//    }

    public static String MD5SHA1(String info) {
        return string2MD5(SHA1(info));
    }

    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest(); // Create Hex String
            StringBuffer hexString = new StringBuffer(); // 字节数组转换为 十六进制数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) hexString.append(0);
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    private String urlencode(String requestData) {
        try {
            return java.net.URLEncoder.encode(requestData, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;

    }

    private static final String TAG = "encrypt";

    public static String encryetWeb(String data, String device, String time) {
        return encrypt(data, device, time, NBTVWEB);
    }

    public static String encrypt(String data, String device, String time) {
        return encrypt(data, device, time, NBTV2015);
    }

    public static String encryptData(String data, String time) {
        return encrypt(new StringBuilder(NBTVDATA), data, time);
    }


    public static String encrypt(String data, String device, String time, String keystore) {
        StringBuilder keysb = new StringBuilder(device);
        keysb.append(keystore);
        keysb.append(time);
        return encrypt(keysb, data, time);
    }

    public static String encrypt(StringBuilder keysb, String data, String time) {
//        Logger.v(TAG, "key:" + keysb.toString());
        String key = string2MD5(keysb.toString());
        String key2 = key.substring(16, 32);
        String keyb = string2MD5(key2);
        String timemd5 = string2MD5(time);
        int keyclength = timemd5.length();
        String keyc = timemd5.substring(keyclength - 4, keyclength);
        Random r = new Random();
        String keyd = string2MD5(data + keyb);
        String keye = keyd.substring(0, 16);
        data = "0000000000" + keye + data;
        int[] is = new int[256];
        for (int i = 0; i < 256; i++) {
            is[i] = i;
        }
        byte[] encode = new byte[data.length()];
        for (int i = 0, a = 0, j = 0; i < data.length(); i++) {
            a = (a + 1) % 128;
            j = (j + is[a]) % 128;
            int temp = is[a];
            is[a] = is[j];
            is[j] = temp;
            char ch = data.charAt(i);
            int in = (int) ch;
            int ia = is[a];
            int ij = is[j];
            int iz = is[(ia + ij) % 128];
            int ie = iz ^ in;
//            char che = (char) ie;
            encode[i] = (byte) ie;
        }
        String sx = Base64.encodeToString(encode, Base64.NO_WRAP);
        String base = sx.replaceAll("=", "");
        String encryetkey = keyc + base;
//        Logger.v(TAG, "encryetvalue:" + encryetkey);
        return encryetkey;
    }


    /*public static String encrypt(String data) {
//        String data = "www.cutv.cn";

        String s = new String("nbtvapp2015");

        String key = string2MD5(s);
        String key1 = key.substring(0, 16);
        String key2 = key.substring(16, 32);

        String keya = string2MD5(key1);
        String keyb = string2MD5(key2);

        long time = System.currentTimeMillis() / 1000;
        String timemd5 = string2MD5(time + "");
        int keyclength = timemd5.length();
        String keyc = timemd5.substring(keyclength - 4, keyclength);
        String cryptkey = keya + string2MD5(keya + keyc);
        int crypLen = cryptkey.length();

        int dataLen = data.length();

        Random r = new Random();
        Logger..e("encrypt", "data + keyb:" + data + keyb);
        String keyd = string2MD5(data + keyb);


        String keye = keyd.substring(0, 16);
//        Logger..e("encrypt","data:"+data
//                +"   \tkeyd:"+keyd+"   \tkeye:"+keye
//                +"   \tkeya:"+keya+"   \tkeyb:"+keyb+
//                "  \tdata:"+data);

        data = "0000000000" + keye + data;

//        Logger..e("encrypt","data:"+data
//                +"   \tkeyd:"+keyd+"   \tkeye:"+keye
//                +"   \tkeya:"+keya+"   \tkeyb:"+keyb+
//                "  \tdata:"+data);
        int[] is = new int[256];
        for (int i = 0; i < 256; i++) {
            is[i] = i;
        }
        byte[] encodeBaseMd5 = new byte[data.length()];
        for (int i = 0, activity = 0, j = 0; i < data.length(); i++) {
            activity = (activity + 1) % 128;
            j = (j + is[activity]) % 128;
            int temp = is[activity];
            is[activity] = is[j];
            is[j] = temp;
            char ch = data.charAt(i);
            int in = (int) ch;
            int ia = is[activity];
            int ij = is[j];
            int iz = is[(ia + ij) % 128];
            int ie = iz ^ in;
            char che = (char) ie;
            encodeBaseMd5[i] = (byte) ie;
//            Logger..e("encrypt", "第" + i + "次：\t   ie:" + ie + "\t   che:" + che + "\t  ij:" + ij + "\t  ia:" + ia);
        }
        String sx = Base64.encodeToString(encodeBaseMd5, Base64.NO_WRAP);
        String base = sx.replaceAll("=", "");
        String encryetkey = keyc + base;
        Logger..e("encrypt:", "encryetkey:" + encryetkey);
        return encryetkey;
    }*/
}
