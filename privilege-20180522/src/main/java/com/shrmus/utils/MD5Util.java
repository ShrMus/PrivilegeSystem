package com.shrmus.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对密码进行MD5加密
 * @author Shr
 *
 */
public class MD5Util {
	
	public static String EncoderByMd5(String string) {
		String result = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte [] temp = md5.digest(string.getBytes());
			result = new String(temp, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
    }
}
