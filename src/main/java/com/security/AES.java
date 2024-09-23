package com.security;

//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.Charsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.base.RefData;
import com.base.StringEx;
import com.fds.flex.common.utility.string.StringPool;

public class AES {
	public static final int KEY_SIZE_128 = 128;
	public static final int KEY_SIZE_192 = 192;
	public static final int KEY_SIZE_256 = 256;

	public static String getKeyIV(String token, RefData refKey, RefData refIV) throws UnsupportedEncodingException {
		// ref key, iv
		if (refKey == null || refIV == null)
			return "RefKey or RefIV is null";
		
		if (StringEx.isNullOrWhiteSpace(token))
			return "Token is empty";
		String[] keyIV = token.split(":");
		if (keyIV.length != 2)
			return "Token doesn't contains key and iv value";
		
		// Base64.Decoder decoder = Base64.getDecoder();
		// byte[] key = decoder.decode(keyIV[0].getBytes(StandardCharsets.US_ASCII));
		byte[] key = DatatypeConverter.parseBase64Binary(keyIV[0].getBytes("US-ASCII").toString());
		int keySize = key.length * 8;
		if (keySize != KEY_SIZE_128 && keySize != KEY_SIZE_192 && keySize != KEY_SIZE_256)
			return "KeySize is invalid " + keySize;

		// byte[] iv = decoder.decode(keyIV[1].getBytes(StandardCharsets.US_ASCII));
		byte[] iv = DatatypeConverter.parseBase64Binary(keyIV[1].getBytes("US-ASCII").toString());
		int ivSize = iv.length * 8;
		if (ivSize != KEY_SIZE_128)
			return "IVSize is invalid " + ivSize;

		refKey.data = key;
		refIV.data = iv;
		return null;
	}

	public static String encrypt(String token, Object data, RefData refData) throws UnsupportedEncodingException {
		// ref data
		if (refData == null)
			return "RefData is null";

		// token
		RefData refKey = new RefData();
		RefData refIV = new RefData();
		String msg = getKeyIV(token, refKey, refIV);
		if (msg != null)
			return msg;

		// data
		RefData refInputData = new RefData();
		//msg = StringEx.getBytes(data, StandardCharsets.US_ASCII, refInputData);
		msg = StringEx.getBytes(data, Charsets.US_ASCII, refInputData);
		if (msg != null)
			return msg;

		// encrypt
		try {
			IvParameterSpec ivSpec = new IvParameterSpec((byte[]) refIV.data);
			SecretKeySpec skeySpec = new SecretKeySpec((byte[]) refKey.data, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); // fix cbc mode
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);

			refData.data = cipher.doFinal((byte[]) refInputData.data);
		} catch (Exception ex) {
			msg = ex.toString();
		}
		return msg;
	}

	public static String encryptToBase64(String token, Object data, RefData refData) throws UnsupportedEncodingException {
		String msg = encrypt(token, data, refData);
		if (msg != null)
			return msg;
		if (refData.data instanceof String)
			return msg;
		//Base64.Encoder encoder = Base64.getEncoder();
		//refData.data = encoder.encodeToString((byte[]) refData.data);
		refData.data = DatatypeConverter.printBase64Binary((byte[]) refData.data);
		return msg;
	}

	public static String decrypt(String token, Object data, RefData refData) throws UnsupportedEncodingException {
		// ref data
		if (refData == null)
			return "RefData is null";

		// token
		RefData refKey = new RefData();
		RefData refIV = new RefData();
		String msg = getKeyIV(token, refKey, refIV);
		if (msg != null)
			return msg;

		// data
		RefData refInputData = new RefData();
		//msg = StringEx.getBytes(data, StandardCharsets.US_ASCII, refInputData);
		msg = StringEx.getBytes(data, Charsets.US_ASCII, refInputData);
		if (msg != null)
			return msg;

		// decrypt
		try {
			IvParameterSpec ivSpec = new IvParameterSpec((byte[]) refIV.data);
			SecretKeySpec skeySpec = new SecretKeySpec((byte[]) refKey.data, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); // fix cbc mode and padding pkcs5
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);

			refData.data = cipher.doFinal((byte[]) refInputData.data);
		} catch (Exception ex) {
			msg = ex.toString();
		}
		return msg;
	}

	public static String decryptFromBase64(String token, String encodedBase64Data, RefData refData) throws UnsupportedEncodingException {
		if (StringEx.isNullOrWhiteSpace(encodedBase64Data))
			return "Data is empty";

		//Base64.Decoder decoder = Base64.getDecoder();
		Object data;
		try {
			//data = decoder.decode(encodedBase64Data);
			data = DatatypeConverter.parseBase64Binary(encodedBase64Data);
		} catch (Exception ex) {
			return ex.toString();
		}

		return decrypt(token, data, refData);
	}

	public static String decryptToString(String token, Object data, RefData refData) throws UnsupportedEncodingException {
		String msg = decrypt(token, data, refData);
		if (msg != null)
			return msg;
		if (refData.data instanceof String)
			return msg;
		refData.data = new String((byte[]) refData.data);
		return msg;
	}
	
	public static String EncodingBase64(String refData) throws UnsupportedEncodingException {
		String encoded = StringPool.BLANK;
		
		byte[] message = refData.getBytes("UTF-8");
		encoded = DatatypeConverter.printBase64Binary(message);
		//System.out.println(encoded);
		
		return encoded;
	}

	public static String DecodingBase64(String refData) throws UnsupportedEncodingException {		
		String message = StringPool.BLANK;
		
		byte[] decoded = DatatypeConverter.parseBase64Binary(refData);
		message = new String(decoded, "UTF-8");		
		System.out.println(message);
		
		return message;
	}

}