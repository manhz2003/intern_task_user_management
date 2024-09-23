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
public class DocumentModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document model instance should use the {@link vn.gt.dao.nhapcanh.model.Document} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table document (id LONG not null primary key,documentname LONG,usercreated VARCHAR(75) null,documenttypecode VARCHAR(75) null,callsign VARCHAR(75) null,predocumentname VARCHAR(75) null,createddate DATE null,lastmodifieddate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table document";
	
}