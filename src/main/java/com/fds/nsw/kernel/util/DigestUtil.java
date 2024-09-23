package com.fds.nsw.kernel.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DigestUtil {

    public static String digestBase64(String algorithm, String... text) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance(algorithm);

            // Sử dụng StringBuilder để kết hợp các chuỗi
            StringBuilder sb = new StringBuilder(text.length * 2 - 1);

            // Sử dụng dấu phân cách ":"
            for (String t : text) {
                if (sb.length() > 0) {
                    sb.append(":");
                }
                sb.append(t);
            }

            String concatenatedText = sb.toString();

            // Sử dụng encoding UTF-8
            messageDigest.update(concatenatedText.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        // Băm chuỗi và mã hóa Base64
        byte[] hashedBytes = messageDigest.digest();

        // Chuyển kết quả băm thành Base64
        return Base64.getEncoder().encodeToString(hashedBytes);
    }
}
