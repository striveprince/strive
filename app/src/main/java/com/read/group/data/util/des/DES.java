package com.read.group.data.util.des;


import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {

    private byte[] desKey;  
//    public static final String mykey = "cutv$^#$%#@$^&ZZ&*";
    public static final String key = "T@V";

    public static final String DKDBKEY = "dkdb6102";
    public DES(String desKey) {
        this.desKey = desKey.getBytes();  
    }  
  
    public byte[] desEncrypt(byte[] plainText) throws Exception {
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = desKey;  
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        byte data[] = plainText;  
        byte encryptedData[] = cipher.doFinal(data);  
        return encryptedData;  
    }  
  
    public byte[] desDecrypt(byte[] encryptText) throws Exception {
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] = desKey;  
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        byte encryptedData[] = encryptText;  
        byte decryptedData[] = cipher.doFinal(encryptedData);  
        return decryptedData;  
    }  
    public static byte[] desEncrypt(byte[] plainText,String keytime) throws Exception {
        SecureRandom sr = new SecureRandom();
        byte rawKeyData[] =(DES.key+keytime).getBytes();
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        byte data[] = plainText;  
        byte encryptedData[] = cipher.doFinal(data);  
        return encryptedData;  
    }  



    public String encrypt(String input){
        return encrypt(input.getBytes());
    }

    public String encrypt(byte[] input){
        try{
            return base64Encode(desEncrypt(input));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static String encrypt(String input, String keytime) throws Exception {
        return base64Encode(desEncrypt(input.getBytes(),keytime));  
    }  
  
    public String decrypt(String input) throws Exception {
        byte[] result = base64Decode(input);  
        return new String(desDecrypt(result));
    }  
      
    public static String base64Encode(byte[] s) {
        if (s == null)  
            return null;  
        BASE64Encoder b = new BASE64Encoder();
        return b.encode(s);  
    }  
  
    public static byte[] base64Decode(String s) throws IOException {
        if (s == null)  
            return null;  
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(s);  
        return b;  
    } 
    public static byte[] desDecrypt(byte[] encryptText,String keytime) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(keytime.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        byte encryptedData[] = encryptText;  
        byte decryptedData[] = cipher.doFinal(encryptedData);  
        return decryptedData;  
    }  
	/*public static byte[] desCrypto(byte[] datasource, String key) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(key.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getNingInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getNingInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}*/

  /*
    public static void main(String[] args) throws Exception {  
        String key = "abcdefgh";  
        String input = "a";  
        DES crypt = new DES(key);  
        System.out.println("Encode:" + crypt.encrypt(input));  
        System.out.println("Decode:" + crypt.decrypt(crypt.encrypt(input)));  
    }  */


}
