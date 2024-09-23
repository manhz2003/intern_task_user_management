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

package com.fds.nsw.kernel.util;


import java.io.Serializable;
import java.util.Comparator;

import com.fds.flex.common.utility.string.CharPool;
import com.fds.flex.common.utility.string.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
@SuppressWarnings("rawtypes")
@Slf4j
public abstract class OrderByComparator implements Comparator, Serializable {

	public abstract int compare(Object obj1, Object obj2);

	public String getOrderBy() {
		return null;
	}

	public String[] getOrderByConditionFields() {
		return getOrderByFields();
	}
	
	public Object[] getOrderByConditionValues(Object obj){
		String[] fields = getOrderByConditionFields();

		Object[] values = new Object[fields.length];
		
		Class<?> clazz = obj.getClass();

		for (int i = 0; i < fields.length; i++) {
			
			//values[i] = BeanPropertiesUtil.getObject(obj, fields[i]);
			try {
				values[i] = clazz.getField(fields[i]).get(obj);
			} catch (Exception e) {
				log.error(e.getMessage());
			} 
		}

		return values;
	}

	public String[] getOrderByFields() {
		String orderBy = getOrderBy();

		if (orderBy == null) {
			return null;
		}

		String[] parts = StringUtil.split(orderBy);

		String[] fields = new String[parts.length];

		for (int i = 0; i < parts.length; i++) {
			String part = parts[i];

			int x = part.indexOf(CharPool.PERIOD);
			int y = part.indexOf(CharPool.SPACE, x);

			if (y == -1) {
				y = part.length();
			}

			fields[i] = part.substring(x + 1, y);
		}

		return fields;
	}

	public boolean isAscending() {
		String orderBy = getOrderBy();

		if ((orderBy == null) ||
			orderBy.toUpperCase().endsWith(_ORDER_BY_DESC)) {

			return false;
		}
		else {
			return true;
		}
	}

	public boolean isAscending(String field) {
		return isAscending();
	}

	@Override
	public String toString() {
		return getOrderBy();
	}

	private static final String _ORDER_BY_DESC = " DESC";

}