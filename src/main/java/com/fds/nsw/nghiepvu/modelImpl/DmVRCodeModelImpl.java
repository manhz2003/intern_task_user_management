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
public class DmVRCodeModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dm v r code model instance should use the {@link com.fds.nsw.nghiepvu.model.DmVRCode} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table dm_vrcode (ID INTEGER not null primary key,VRCode VARCHAR(75) null,VRCodeName VARCHAR(75) null,IsDelete INTEGER,MarkedAsDelete VARCHAR(75) null,ModifiedDate DATE null,RequestedDate DATE null,SyncVersion VARCHAR(75) null,ShipBoat VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table dm_vrcode";
	
}