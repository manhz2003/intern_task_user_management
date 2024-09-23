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
public class DmDataItemModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dm data item model instance should use the {@link com.fds.nsw.nghiepvu.model.DmDataitem} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table dm_dataitem (id LONG not null primary key,datagroupid LONG,code VARCHAR(75) null,node_1 VARCHAR(75) null,node_2 VARCHAR(75) null,node_3 VARCHAR(75) null,node_4 VARCHAR(75) null,level INTEGER,name VARCHAR(75) null,short_name VARCHAR(75) null,description VARCHAR(75) null,validatedfrom DATE null,validatedto DATE null,order_ INTEGER,status INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table dm_dataitem";
	public static final String ORDER_BY_JPQL = " ORDER BY dmDataItem.Id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY dm_dataitem.id ASC";
	
}