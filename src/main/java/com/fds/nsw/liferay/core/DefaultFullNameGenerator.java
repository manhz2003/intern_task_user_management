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

package com.fds.nsw.liferay.core;

import com.fds.flex.common.ultility.Validator;
import com.fds.flex.common.utility.string.CharPool;
import com.fds.flex.common.utility.string.StringBundler;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.utility.string.StringUtil;
import org.springframework.stereotype.Service;


/**
 * @author Michael C. Han
 */
@Service
public class DefaultFullNameGenerator  {

	public static  String getFullName(
		String firstName, String middleName, String lastName) {

		String fullName = buildFullName(firstName, middleName, lastName, false);

		if (fullName.length() <= UserConstants.FULL_NAME_MAX_LENGTH) {
			return fullName;
		}

		fullName = buildFullName(firstName, middleName, lastName, true);

		if (fullName.length() <= UserConstants.FULL_NAME_MAX_LENGTH) {
			return fullName;
		}

		return fullName.substring(0, UserConstants.FULL_NAME_MAX_LENGTH);
	}


	protected static String buildFullName(
		String firstName, String middleName, String lastName,
		boolean useInitials) {

		StringBundler sb = new StringBundler(5);

		if (useInitials) {
			firstName = firstName.substring(0, 1);
		}

		sb.append(firstName);

		if (Validator.isNotNull(middleName)) {
			if (useInitials) {
				middleName = middleName.substring(0, 1);
			}

			sb.append(StringPool.SPACE);
			sb.append(middleName);
		}

		if (Validator.isNotNull(lastName)) {
			sb.append(StringPool.SPACE);
			sb.append(lastName);
		}

		return sb.toString();
	}

}