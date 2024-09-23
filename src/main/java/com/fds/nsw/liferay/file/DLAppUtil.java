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

import com.fds.flex.common.ultility.Validator;

import java.io.File;
import java.io.InputStream;

/**
 * @author Alexander Chow
 */
public class DLAppUtil {

	public static String getExtension(String title, String sourceFileName) {
		String extension = FileUtil.getExtension(sourceFileName);

		if (Validator.isNull(extension)) {
			extension = FileUtil.getExtension(title);
		}

		return extension;
	}

	public static String getMimeType(
		String sourceFileName, String mimeType, String title, File file,
		InputStream is) {

		if (Validator.isNull(mimeType) ||
			!mimeType.equals(ContentTypes.APPLICATION_OCTET_STREAM)) {

			return mimeType;
		}

		if (Validator.isNull(title)) {
			title = sourceFileName;
		}

		String extension = getExtension(title, sourceFileName);

		String titleWithExtension = DLUtil.getTitleWithExtension(
			title, extension);

		if (file != null) {
			mimeType = MimeTypesUtil.getContentType(file, titleWithExtension);
		}
		else {
			mimeType = MimeTypesUtil.getContentType(is, titleWithExtension);
		}

		return mimeType;
	}

}