package com.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.apache.commons.io.Charsets;
//import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.base.RefData;
import com.base.StringEx;

public class Zip {
	public static String gzipCompress(Object data, RefData refData) {
		if (refData == null)
			return "RefData is null";

		RefData refInputData = new RefData();
		String msg = StringEx.getBytes(data, Charsets.US_ASCII, refInputData);
		if (msg != null)
			return msg;

		ByteArrayOutputStream outStream = null;
		GZIPOutputStream gzipOut = null;
		byte[] inputData = (byte[]) refInputData.data;
		try {
			outStream = new ByteArrayOutputStream();
			gzipOut = new GZIPOutputStream(outStream);
			gzipOut.write(inputData);
			gzipOut.flush();
			gzipOut.finish();

			refData.data = outStream.toByteArray();
		} catch (Exception ex) {
			msg = ex.toString();
		} finally {
			if (gzipOut != null)
				try {
					gzipOut.close();
					outStream.close();
				} catch (Exception ex) {
					msg = ex.toString();
				}
		}
		return msg;
	}

	public static String gzipDecompress(Object data, RefData refData) {
		if (refData == null)
			return "RefData is null";

		RefData refInputData = new RefData();
		String msg = StringEx.getBytes(data, Charsets.US_ASCII, refInputData);
		if (msg != null)
			return msg;

		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		GZIPInputStream gzipIn = null;
		byte[] inputData = (byte[]) refInputData.data;
		try {
			inputStream = new ByteArrayInputStream(inputData);
			gzipIn = new GZIPInputStream(inputStream);

			outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4098];
			int length = gzipIn.read(buffer);
			while (length > 0) {
				outputStream.write(buffer, 0, length);
				length = gzipIn.read(buffer);
			}

			outputStream.flush();
			refData.data = outputStream.toByteArray();

		} catch (Exception ex) {
			msg = ex.toString();
		} finally {
			if (gzipIn != null)
				try {
					gzipIn.close();
					inputStream.close();
					outputStream.close();
				} catch (Exception ex) {
					msg = ex.toString();
				}
		}
		return msg;
	}

}
