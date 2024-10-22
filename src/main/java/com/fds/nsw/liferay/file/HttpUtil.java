package com.fds.nsw.liferay.file;

import com.fds.flex.common.utility.string.CharPool;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.utility.string.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.BitSet;

@Slf4j
public class HttpUtil {

    public static String decodeURL(String encodedURLString) {
        return decodeURL(encodedURLString, StringPool.UTF8, false);
    }

    public static String decodeURL(
            String encodedURLString, boolean unescapeSpaces) {

        return decodeURL(encodedURLString, StringPool.UTF8, unescapeSpaces);
    }

    public static String decodeURL(
            String encodedURLString, String charsetName, boolean unescapeSpaces) {

        if (encodedURLString == null) {
            return null;
        }

        if (encodedURLString.length() == 0) {
            return StringPool.BLANK;
        }

		/*if (unescapeSpaces) {
			encodedURLString = StringUtil.replace(
				encodedURLString, "%20", StringPool.PLUS);
		}*/

        StringBuilder sb = null;

        CharsetDecoder charsetDecoder = null;

        for (int i = 0; i < encodedURLString.length(); i++) {
            char c = encodedURLString.charAt(i);

            switch (c) {
                case CharPool.PERCENT:
                    ByteBuffer byteBuffer = _getEncodedByteBuffer(
                            encodedURLString, i);

                    if (charsetDecoder == null) {
                        charsetDecoder = CharsetDecoderUtil.getCharsetDecoder(
                                charsetName);
                    }

                    CharBuffer charBuffer = null;

                    try {
                        charBuffer = charsetDecoder.decode(byteBuffer);
                    }
                    catch (CharacterCodingException cce) {
                        log.error(cce.getMessage());

                        return StringPool.BLANK;
                    }

                    if (sb == null) {
                        sb = new StringBuilder(encodedURLString.length());

                        if (i > 0) {
                            sb.append(encodedURLString, 0, i);
                        }
                    }

                    sb.append(charBuffer);

                    i += byteBuffer.capacity() * 3 - 1;

                    break;

                case CharPool.PLUS:
                    if (sb == null) {
                        sb = new StringBuilder(encodedURLString.length());

                        if (i > 0) {
                            sb.append(encodedURLString, 0, i);
                        }
                    }

                    sb.append(CharPool.SPACE);

                    break;

                default:
                    if (sb != null) {
                        sb.append(c);
                    }
            }
        }

        if (sb == null) {
            return encodedURLString;
        }
        else {
            return sb.toString();
        }
    }

    public static String encodeURL(String rawURLString) {
        return encodeURL(rawURLString, StringPool.UTF8, false);
    }

    public static String encodeURL(String rawURLString, boolean escapeSpaces) {
        return encodeURL(rawURLString, StringPool.UTF8, escapeSpaces);
    }

    public static String encodeURL(
            String rawURLString, String charsetName, boolean escapeSpaces) {

        if (rawURLString == null) {
            return null;
        }

        if (rawURLString.length() == 0) {
            return StringPool.BLANK;
        }

        StringBuilder sb = null;

        CharsetEncoder charsetEncoder = null;

        char[] hexes = new char[2];

        for (int i = 0; i < rawURLString.length(); i++) {
            char c = rawURLString.charAt(i);

            if (_validChars.get(c)) {
                if (sb != null) {
                    sb.append(c);
                }

                continue;
            }

            if (sb == null) {
                sb = new StringBuilder(rawURLString.length());

                sb.append(rawURLString.substring(0, i));
            }

            // The cases are ordered by frequency and not alphabetically

            switch (c) {
                case CharPool.SLASH :
                    sb.append("%2F");

                    continue;

                case CharPool.EQUAL :
                    sb.append("%3D");

                    continue;

                case CharPool.AMPERSAND :
                    sb.append("%26");

                    continue;

                case CharPool.PERCENT :
                    sb.append("%25");

                    continue;

                case CharPool.SPACE :
                    if (escapeSpaces) {
                        sb.append("%20");
                    }
                    else {
                        sb.append(CharPool.PLUS);
                    }

                    continue;

                case CharPool.COLON :
                    sb.append("%3A");

                    continue;

                case CharPool.QUESTION :
                    sb.append("%3F");

                    continue;
            }

            CharBuffer charBuffer = _getRawCharBuffer(rawURLString, i);

            if (charsetEncoder == null) {
                charsetEncoder = CharsetEncoderUtil.getCharsetEncoder(
                        charsetName);
            }

            i += charBuffer.length() - 1;

            ByteBuffer byteBuffer = null;

            try {
                byteBuffer = charsetEncoder.encode(charBuffer);
            }
            catch (CharacterCodingException cce) {
                log.error(cce.getMessage());

                return StringPool.BLANK;
            }

            for (int j = byteBuffer.position(); j < byteBuffer.limit(); j++) {
                sb.append(CharPool.PERCENT);

                sb.append(
                        UnicodeFormatter.byteToHex(byteBuffer.get(), hexes, true));
            }
        }

        if (sb == null) {
            return rawURLString;
        }
        else {
            return sb.toString();
        }
    }

    public static String unescape(String text) {
        if (text == null) {
            return null;
        }

        if (text.length() == 0) {
            return StringPool.BLANK;
        }

        // Optimize this

        text = StringUtil.replace(text, "&lt;", "<");
        text = StringUtil.replace(text, "&gt;", ">");
        text = StringUtil.replace(text, "&amp;", "&");
        text = StringUtil.replace(text, "&#034;", "\"");
        text = StringUtil.replace(text, "&#039;", "'");
        text = StringUtil.replace(text, "&#040;", "(");
        text = StringUtil.replace(text, "&#041;", ")");
        text = StringUtil.replace(text, "&#044;", ",");
        text = StringUtil.replace(text, "&#035;", "#");
        text = StringUtil.replace(text, "&#037;", "%");
        text = StringUtil.replace(text, "&#059;", ";");
        text = StringUtil.replace(text, "&#061;", "=");
        text = StringUtil.replace(text, "&#043;", "+");
        text = StringUtil.replace(text, "&#045;", "-");

        return text;
    }

    private static int _charToHex(char c) {
        if ((c >= CharPool.LOWER_CASE_A) && (c <= CharPool.LOWER_CASE_Z)) {
            return c - CharPool.LOWER_CASE_A + 10;
        }

        if ((c >= CharPool.UPPER_CASE_A) && (c <= CharPool.UPPER_CASE_Z)) {
            return c - CharPool.UPPER_CASE_A + 10;
        }

        if ((c >= CharPool.NUMBER_0) && (c <= CharPool.NUMBER_9)) {
            return c - CharPool.NUMBER_0;
        }

        throw new IllegalArgumentException(c + " is not a hex char");
    }

    private static ByteBuffer _getEncodedByteBuffer(
            String encodedString, int start) {

        int count = 1;

        for (int i = start + 3; i < encodedString.length(); i += 3) {
            if (encodedString.charAt(i) == CharPool.PERCENT) {
                count++;
            }
            else {
                break;
            }
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(count);

        for (int i = start; i < start + count * 3; i += 3) {
            int high = _charToHex(encodedString.charAt(i + 1));
            int low = _charToHex(encodedString.charAt(i + 2));

            byteBuffer.put((byte)((high << 4) + low));
        }

        byteBuffer.flip();

        return byteBuffer;
    }

    private static CharBuffer _getRawCharBuffer(String rawString, int start) {
        int count = 0;

        for (int i = start; i < rawString.length(); i++) {
            char rawChar = rawString.charAt(i);

            if (!_validChars.get(rawChar)) {
                count++;

                if (Character.isHighSurrogate(rawChar)) {
                    if (((i + 1) < rawString.length()) &&
                            Character.isLowSurrogate(rawString.charAt(i + 1))) {

                        count++;
                    }
                }
            }
            else {
                break;
            }
        }

        return CharBuffer.wrap(rawString, start, start + count);
    }


    private static BitSet _validChars = new BitSet(256);

    static {
        for (int i = 'a'; i <= 'z'; i++) {
            _validChars.set(i);
        }

        for (int i = 'A'; i <= 'Z'; i++) {
            _validChars.set(i);
        }

        for (int i = '0'; i <= '9'; i++) {
            _validChars.set(i);
        }

        _validChars.set('-');
        _validChars.set('_');
        _validChars.set('.');
        _validChars.set('*');
    }

}
