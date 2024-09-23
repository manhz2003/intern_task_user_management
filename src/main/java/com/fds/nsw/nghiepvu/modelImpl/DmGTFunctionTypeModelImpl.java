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
public class DmGTFunctionTypeModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dm g t function type model instance should use the {@link com.fds.nsw.nghiepvu.model.DmGTFunctionType} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table DM_GT_FUNCTION_TYPE (id LONG not null primary key,FunctionTypeCode VARCHAR(75) null,FunctionTypeName VARCHAR(75) null,FunctionTypeNameVN VARCHAR(75) null,FunctionTypeOrder INTEGER,IsDelete INTEGER,MarkedAsDelete INTEGER,ModifiedDate DATE null,RequestedDate DATE null,SyncVersion VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table DM_GT_FUNCTION_TYPE";
	public static final String ORDER_BY_JPQL = " ORDER BY dmGTFunctionType.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY DM_GT_FUNCTION_TYPE.id ASC";
	
}