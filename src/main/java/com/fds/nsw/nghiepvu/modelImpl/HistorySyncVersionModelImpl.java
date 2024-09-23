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
public class HistorySyncVersionModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a history sync version model instance should use the {@link com.fds.nsw.nghiepvu.model.HistorySyncVersion} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table history_sync_version (id LONG not null primary key,requesteddate DATE null,timeofpublish DATE null,categoryid VARCHAR(75) null,syncunit VARCHAR(75) null,syncuser VARCHAR(75) null,syncversion VARCHAR(75) null,latestvaluedate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table history_sync_version";
	public static final String ORDER_BY_JPQL = " ORDER BY historySyncVersion.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY history_sync_version.id ASC";
	
}