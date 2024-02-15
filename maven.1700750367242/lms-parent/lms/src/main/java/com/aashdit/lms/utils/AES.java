package com.aashdit.lms.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	private static final String ALGORITHM = "AES";

//    public static String decrypt(String encryptedData, String key) throws Exception {
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//        String base64String = encryptedData.replace('-', '+').replace('_', '/');
//        int padding = 4 - base64String.length() % 4;
//        for (int i = 0; i < padding; i++) {
//            base64String += "=";
//        }
//        base64String = base64String.replaceAll("\\s", "");
//        
//        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
//        return new String(decryptedBytes);
//    }
	
	public static String decrypt(String encryptedData, String key) throws Exception {
	    Cipher cipher = Cipher.getInstance(ALGORITHM);
	    SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

//        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData.getBytes(StandardCharsets.UTF_8));
//	    byte[] decodedBytes = Base64.getUrlDecoder().decode(encryptedData.trim());
	    System.out.println("Encrypted Data (before URL decoding): " + encryptedData);
	    String urlDecodedData = URLDecoder.decode(encryptedData, StandardCharsets.UTF_8.toString());
	    System.out.println("Encrypted Data (after URL decoding): " + urlDecodedData);
	    byte[] decodedBytes = Base64.getDecoder().decode(urlDecodedData.getBytes(StandardCharsets.UTF_8));

	    byte[] decryptedBytes = cipher.doFinal(decodedBytes);
	    return new String(decryptedBytes);
	}
	
	public String decryptData(String encryptedData, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
}
