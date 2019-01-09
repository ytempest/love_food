package com.ytempest.common;

import java.security.MessageDigest;

public class SecurityUtils {

	public static void main(String[] args) {
		System.out.println(encrypt("123"));
	}

	/**
	 * 将 str进行加密
	 * 
	 * @param str
	 *            要进行加密的字符串
	 * @return
	 */
	public static String encrypt(String str) {
		str = encodeTOMD5(str);

		str = secondEncrypt(str);
		return str;
	}

	/**
	 * 二次加密，加入自己的加密逻辑；将所有的a替换成b，b替换成c，依次类推，z不变，同时数字除外
	 * 
	 * @param str
	 *            要进行二次加密的字符串
	 * @return
	 */
	private static String secondEncrypt(String str) {
		StringBuffer sb = new StringBuffer(str);
		for (int i = 0; i < sb.length(); i++) {
			char ch = sb.charAt(i);
			if (ch >= 'a' && ch < 'z') {
				sb.replace(i, i + 1, (char) (ch + 1) + "");
			}
		}
		return sb.toString();

	}

	/**
	 * 利用MD5进行加密
	 *
	 * @param str
	 *            待加密的字符串
	 * @return 加密后的字符串
	 */
	public static String encodeTOMD5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuilder hexValue = new StringBuilder();
		for (byte md5Byte : md5Bytes) {
			int val = ((int) md5Byte) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
}
