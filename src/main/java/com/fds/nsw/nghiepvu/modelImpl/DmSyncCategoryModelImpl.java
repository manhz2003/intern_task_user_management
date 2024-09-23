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
public class DmSyncCategoryModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dm sync category model instance should use the {@link com.fds.nsw.nghiepvu.model.DmSyncCategory} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table dm_sync_category (id LONG not null primary key,CategoryID VARCHAR(75) null,categorydescription VARCHAR(75) null,modifieduser VARCHAR(75) null,modifieddate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table dm_sync_category";
	public static final String ORDER_BY_JPQL = " ORDER BY dmSyncCategory.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY dm_sync_category.id ASC";
	
}