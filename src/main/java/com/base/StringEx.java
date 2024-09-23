package com.base;

import java.nio.charset.Charset;

public class StringEx {
	public static boolean isNullOrEmpty(String content) {
		return content == null || content.length() < 1;
	}

	public static boolean isNullOrWhiteSpace(String content) {
		boolean isEmpty = isNullOrEmpty(content);
		if (isEmpty)
			return isEmpty;

		int i = 0, k = content.length() - 1, n = k;
		while (i < k) {
			if (content.charAt(i) <= AsciiTable.SPACE)
				i++;
			if (content.charAt(k) <= AsciiTable.SPACE)
				k--;
			n--;
			if (n < k - i)
				break;
		}
		return i >= k;
	}

	public static String getBytes(Object data, Charset charset, RefData refData) {
		if (refData == null)
			return "RefData is null";
		// data
		if (!(data instanceof String) && !(data instanceof byte[]))
			return "Data is not instance of string or byte array";
		String dataInStr = null;
		byte[] inputData = null;
		if (data instanceof String) {
			dataInStr = (String) data;
			inputData = dataInStr.getBytes(charset);
		} else
			inputData = (byte[]) data;
		if (inputData == null || inputData.length < 1)
			return "Data is empty";
		refData.data = inputData;
		return null;
	}
}
