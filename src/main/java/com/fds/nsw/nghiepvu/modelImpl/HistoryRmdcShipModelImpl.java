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
public class HistoryRmdcShipModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a history rmdc ship model instance should use the {@link com.fds.nsw.nghiepvu.model.HistoryRmdcShip} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table history_dmdc_ship (id LONG not null primary key,shipid INTEGER,shipname VARCHAR(75) null,shiptypeid INTEGER,shiptypecode VARCHAR(75) null,imo VARCHAR(75) null,callsign VARCHAR(75) null,stateid VARCHAR(75) null,statecode VARCHAR(75) null,registrynumber VARCHAR(75) null,registrydate DATE null,registryportid INTEGER,registryportcode VARCHAR(75) null,gt DOUBLE,nrt DOUBLE,dwt DOUBLE,ship_length DOUBLE,ship_width DOUBLE,ship_height DOUBLE,sailingspeed DOUBLE,color VARCHAR(75) null,bonecode VARCHAR(75) null,machinecode VARCHAR(75) null,shipagencyid INTEGER,shipagencycode VARCHAR(75) null,fishingboatregistration VARCHAR(75) null,roleship INTEGER,isdelete INTEGER,markedasdelete INTEGER,modifieddate DATE null,requesteddate DATE null,syncversion VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table history_dmdc_ship";
	public static final String ORDER_BY_JPQL = " ORDER BY historyRmdcShip.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY history_dmdc_ship.id ASC";
	
}