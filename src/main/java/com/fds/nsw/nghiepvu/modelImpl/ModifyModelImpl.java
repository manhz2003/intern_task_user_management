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
public class ModifyModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a modify model instance should use the {@link com.fds.nsw.nghiepvu.model.Modify} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table gt_modify (ID LONG not null primary key,modifycode VARCHAR(75) null,modifydesc VARCHAR(75) null,organization VARCHAR(75) null,division VARCHAR(75) null,name VARCHAR(75) null,modifydate DATE null,documentname LONG,documentyear LONG)";
	public static final String TABLE_SQL_DROP = "drop table gt_modify";
	public static final String ORDER_BY_JPQL = " ORDER BY modify.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY gt_modify.ID ASC";
	
}