/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.fds.nsw.nghiepvu.modelImpl;
public class ThamSoHeThongModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a tham so he thong model instance should use the {@link com.fds.nsw.nghiepvu.model.ThamSoHeThong} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table user_system_init (id LONG not null primary key,valuedata VARCHAR(75) null,keydata VARCHAR(75) null,description VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table user_system_init";
	public static final String ORDER_BY_JPQL = " ORDER BY thamSoHeThong.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY user_system_init.id ASC";
	
}