/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.fds.nsw.liferay.file;

import com.fds.flex.common.ultility.GetterUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
@Slf4j
public class StreamUtil {

	public static final int BUFFER_SIZE = GetterUtil.getInteger(
		System.getProperty(StreamUtil.class.getName() + ".buffer.size"), 8192);

	public static final boolean FORCE_TIO = GetterUtil.getBoolean(
		System.getProperty(StreamUtil.class.getName() + ".force.tio"));

	public static void cleanUp(Channel channel) {
		try {
			if (channel != null) {
				channel.close();
			}
		}
		catch (Exception e) {
			if (log.isWarnEnabled()) {
				log.warn(e.getMessage());
			}
		}
	}

	public static void cleanUp(Channel inputChannel, Channel outputChannel) {
		cleanUp(inputChannel);
		cleanUp(outputChannel);
	}

	public static void cleanUp(InputStream inputStream) {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		catch (Exception e) {
			if (log.isWarnEnabled()) {
				log.warn(e.getMessage());
			}
		}
	}

	public static void cleanUp(
		InputStream inputStream, OutputStream outputStream) {

		cleanUp(outputStream);
		cleanUp(inputStream);
	}

	public static void cleanUp(OutputStream outputStream) {
		try {
			if (outputStream != null) {
				outputStream.flush();
			}
		}
		catch (Exception e) {
			if (log.isWarnEnabled()) {
				log.warn(e.getMessage());
			}
		}

		try {
			if (outputStream != null) {
				outputStream.close();
			}
		}
		catch (Exception e) {
			if (log.isWarnEnabled()) {
				log.warn(e.getMessage());
			}
		}
	}

	public static void transfer(
			InputStream inputStream, OutputStream outputStream)
		throws IOException {

		transfer(inputStream, outputStream, BUFFER_SIZE, true);
	}

	public static void transfer(
			InputStream inputStream, OutputStream outputStream, boolean cleanUp)
		throws IOException {

		transfer(inputStream, outputStream, BUFFER_SIZE, cleanUp);
	}

	public static void transfer(
			InputStream inputStream, OutputStream outputStream, int bufferSize)
		throws IOException {

		transfer(inputStream, outputStream, bufferSize, true);
	}

	public static void transfer(
			InputStream inputStream, OutputStream outputStream, int bufferSize,
			boolean cleanUp)
		throws IOException {

		transfer(inputStream, outputStream, bufferSize, cleanUp, 0);
	}

	public static void transfer(
			InputStream inputStream, OutputStream outputStream, int bufferSize,
			boolean cleanUp, long length)
		throws IOException {

		if (inputStream == null) {
			throw new IllegalArgumentException("Input stream cannot be null");
		}

		if (outputStream == null) {
			throw new IllegalArgumentException("Output stream cannot be null");
		}

		if (bufferSize <= 0) {
			bufferSize = BUFFER_SIZE;
		}

		try {
			if (!FORCE_TIO && (inputStream instanceof FileInputStream) &&
				(outputStream instanceof FileOutputStream)) {

				FileInputStream fileInputStream = (FileInputStream)inputStream;
				FileOutputStream fileOutputStream =
					(FileOutputStream)outputStream;

				transferFileChannel(
					fileInputStream.getChannel(), fileOutputStream.getChannel(),
					length);
			}
			else {
				transferByteArray(
					inputStream, outputStream, bufferSize, length);
			}
		}
		finally {
			if (cleanUp) {
				cleanUp(inputStream, outputStream);
			}
		}
	}

	public static void transfer(
			InputStream inputStream, OutputStream outputStream, long length)
		throws IOException {

		transfer(inputStream, outputStream, BUFFER_SIZE, true, length);
	}

	protected static void transferByteArray(
			InputStream inputStream, OutputStream outputStream, int bufferSize,
			long length)
		throws IOException {

		byte[] bytes = new byte[bufferSize];

		long remainingLength = length;

		if (remainingLength > 0) {
			while (remainingLength > 0) {
				int readBytes = inputStream.read(
					bytes, 0, (int)Math.min(remainingLength, bufferSize));

				if (readBytes == -1) {
					break;
				}

				outputStream.write(bytes, 0, readBytes);

				remainingLength -= readBytes;
			}
		}
		else {
			int value = -1;

			while ((value = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, value);
			}
		}
	}

	protected static void transferFileChannel(
			FileChannel inputFileChannel, FileChannel outputFileChannel,
			long length)
		throws IOException {

		long size = 0;

		if (length > 0) {
			size = length;
		}
		else {
			size = inputFileChannel.size();
		}

		long position = 0;

		while (position < size) {
			position += inputFileChannel.transferTo(
				position, size - position, outputFileChannel);
		}
	}

}