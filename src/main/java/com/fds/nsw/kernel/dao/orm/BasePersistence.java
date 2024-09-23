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

import com.fds.flex.common.utility.string.StringBundler;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.kernel.util.OrderByComparator;

import lombok.extern.slf4j.Slf4j;

/**
 * The base implementation for all persistence classes. This class should never
 * need to be used directly.
 *
 * <p>
 * Caching information and settings can be found in
 * <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
@Slf4j
public class BasePersistence {

	public static final String COUNT_COLUMN_NAME = "COUNT_VALUE";

	public static final Object[] FINDER_ARGS_EMPTY = new Object[0];

	public static final String ORDER_BY_ASC = " ASC";

	public static final String ORDER_BY_ASC_HAS_NEXT = " ASC, ";

	public static final String ORDER_BY_CLAUSE = " ORDER BY ";

	public static final String ORDER_BY_DESC = " DESC";

	public static final String ORDER_BY_DESC_HAS_NEXT = " DESC, ";

	public static final String WHERE_AND = " AND ";

	public static final String WHERE_GREATER_THAN = " >= ? ";

	public static final String WHERE_GREATER_THAN_HAS_NEXT = " >= ? AND ";

	public static final String WHERE_LESSER_THAN = " <= ? ";

	public static final String WHERE_LESSER_THAN_HAS_NEXT = " <= ? AND ";

	public static final String WHERE_OR = " OR ";

	public SystemException processException(Exception e) {
		if (!(e instanceof ORMException)) {
			e.printStackTrace();
			log.error("Caught unexpected exception " + e.getClass().getName());
		}

		if (log.isDebugEnabled()) {
			log.debug(e.getMessage());
		}

		return new SystemException(e);
	}

	protected void appendOrderByComparator(StringBundler query, String entityAlias,
			OrderByComparator orderByComparator) {

		query.append(ORDER_BY_CLAUSE);

		String[] orderByFields = orderByComparator.getOrderByFields();

		for (int i = 0; i < orderByFields.length; i++) {
			query.append(entityAlias);
			query.append(orderByFields[i]);

			if ((i + 1) < orderByFields.length) {
				if (orderByComparator.isAscending(orderByFields[i])) {
					query.append(ORDER_BY_ASC_HAS_NEXT);
				} else {
					query.append(ORDER_BY_DESC_HAS_NEXT);
				}
			} else {
				if (orderByComparator.isAscending(orderByFields[i])) {
					query.append(ORDER_BY_ASC);
				} else {
					query.append(ORDER_BY_DESC);
				}
			}
		}
	}


}