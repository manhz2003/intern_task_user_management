package com.fds.nsw.kernel.util;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
public class AuthUtil {
    public static String[] extractAndDecodeHeader(String header) {
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded = Base64.getDecoder().decode(base64Token);
        String token = new String(decoded, StandardCharsets.UTF_8);

        String[] parts = token.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid Basic Authentication token");
        }
        return parts;
    }
}
