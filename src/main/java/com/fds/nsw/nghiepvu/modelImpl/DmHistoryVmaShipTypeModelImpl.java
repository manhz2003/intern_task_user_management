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
public class DmHistoryVmaShipTypeModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dm history vma ship type model instance should use the {@link com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table dm_history_vmaship_type (ID LONG not null primary key,ShipTypeCode VARCHAR(75) null,ShipTypeName VARCHAR(75) null,ApplyShip INTEGER,ApplyBoat INTEGER,ShipTypeRef VARCHAR(75) null,Remarks VARCHAR(75) null,IsDelete INTEGER,MarkedAsDelete INTEGER,ModifiedDate DATE null,RequestedDate DATE null,SyncVersion VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table dm_history_vmaship_type";
	public static final String ORDER_BY_JPQL = " ORDER BY dmHistoryVmaShipType.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY dm_history_vmaship_type.ID ASC";
	
}