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
public class UserSignModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user sign model instance should use the {@link com.fds.nsw.nghiepvu.model.UserSign} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table user_sign (id LONG not null primary key,userid LONG,portcode VARCHAR(75) null,title VARCHAR(75) null,representative VARCHAR(75) null,filechukyid LONG,filecondauid LONG,filechungthusoid LONG,fileChungThuSoHsm VARCHAR(75) null,modifyuser VARCHAR(75) null,modifydate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table user_sign";
	public static final String ORDER_BY_JPQL = " ORDER BY userSign.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY user_sign.id ASC";
	
}