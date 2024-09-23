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

package com.fds.nsw.kernel.dao.orm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Brian Wing Shun Chan
 */

public class QueryUtil {

	public static final int ALL_POS = -1;

	public static List<String> extractText(String sql) {
		List<String> values = new ArrayList<>();
		Pattern pattern = Pattern.compile(":(\\w+)(?=[\\s\"]|$)");
		Matcher matcher = pattern.matcher(sql);
		while (matcher.find()) {
			values.add(matcher.group(1));
		}
		return values;
	}
	
	public static Map<String, Object> getNamedParameterMap(String sql, Object ...parameters) {
		List<String> names = extractText(sql);
		Map<String, Object> map = new HashMap<>();
		for(String name:names) {
			map.put(name, parameters[name.indexOf(name)]);
		}
		return map;
	}
}