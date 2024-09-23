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
public class DmHistoryStateModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dm history state model instance should use the {@link com.fds.nsw.nghiepvu.model.DmHistoryState} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table dm_history_state (id INTEGER not null primary key,statecode VARCHAR(75) null,statename VARCHAR(75) null,description VARCHAR(75) null,isdelete INTEGER,markedasdelete INTEGER,modifieddate DATE null,requesteddate DATE null,syncversion VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table dm_history_state";
	public static final String ORDER_BY_JPQL = " ORDER BY dmHistoryState.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY dm_history_state.id ASC";
	
}