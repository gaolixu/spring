package com.intervalintl.aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

public class AESBase {
	 static final String KEY_ALGORITHM = "AES";  
	static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";

	public static String encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			//byte[] enCodeFormat = secretKey.getEncoded();
			// System.out.println("Key length is : " + secretKey.getEncoded().length); 
			//SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] result = cipher.doFinal(byteContent);
			return Base64.encodeBase64String(result); 
						
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			//byte[] enCodeFormat = secretKey.getEncoded();
			//SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] result = cipher.doFinal(Base64.decodeBase64(content));
			return new String(result); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	public static void main(String[] args) {
		//String content = "\"AgentLoginInfo\": {\"LoginID\": \"agent.name@intervalworld.com\",\"PassWord\": \"AgentPassw0rd!\"},\"RequestInfo\": {\"OrderID\": \"12345\",\"Operation\": \"FETCH_ORDER\"}";
		
		String content = "{'RequestInfo': {'order_id':123}}";

		String password = "Lj?75V9}2nN!!PyW";
		// 加密
		System.out.println("加密前：" + content);
		String encryptResult = encrypt(content, password);		
	
		System.out.println("加密后：" + encryptResult);  
		//解密   
		
		String decryptResult = decrypt(encryptResult,password);  
		System.out.println("解密后：" + decryptResult);  
	}
}
