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
public class DmGtVersionModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dm gt version model instance should use the {@link com.fds.nsw.nghiepvu.model.DmGtVersion} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table DM_GT_VERSION (id INTEGER not null primary key,VersionName VARCHAR(75) null,VersionDate DATE null,VersionDeployer VARCHAR(75) null,Contents VARCHAR(75) null,Description VARCHAR(75) null,DBFileName VARCHAR(75) null,DBFileSize VARCHAR(75) null,DBDescription VARCHAR(75) null,APPFileName VARCHAR(75) null,APPFileSize VARCHAR(75) null,APPDescription VARCHAR(75) null,SpecFileName VARCHAR(75) null,SpecFileSize VARCHAR(75) null,SpecDescription VARCHAR(75) null,OrganizationCode VARCHAR(75) null,IsDelete INTEGER,MarkedAsDelete INTEGER,ModifiedDate DATE null,RequestedDate DATE null,SyncVersion VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table DM_GT_VERSION";
	public static final String ORDER_BY_JPQL = " ORDER BY dmGtVersion.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY DM_GT_VERSION.id ASC";
	
}