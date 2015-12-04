package com.haier.neusoft.o2o.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>Description: [AES-128-ECB加密模式，key需要为16位]</p>
 * Created on 2013-4-18
 * @author  <a href="mailto: zh-fan@neusoft.com">zh-fan</a>
 * @version 1.0
 */
public class AESUtil {
	private static final Log LOG = LogFactory.getLog(AESUtil.class);

	// 加密
	public static String encrypt(String sSrc, String sKey) throws Exception {
		if (sKey == null) {
			LOG.info("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			LOG.info("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes("utf-8");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用
	}

	// 解密
	public static String decrypt(String sSrc, String sKey) throws Exception {
			// 判断Key是否正确
			if (sKey == null) {
				LOG.info("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				LOG.info("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
	}
}
