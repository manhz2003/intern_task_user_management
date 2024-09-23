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
public class DmShipAgencyModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dm ship agency model instance should use the {@link com.fds.nsw.nghiepvu.model.DmShipAgency} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table DM_SHIP_AGENCY (id INTEGER not null primary key,ShipAgencyCode VARCHAR(75) null,ShipAgencyName VARCHAR(75) null,ShipAgencyNameVN VARCHAR(75) null,TaxCode VARCHAR(75) null,StateCode VARCHAR(75) null,CityCode VARCHAR(75) null,Address VARCHAR(75) null,AddressVN VARCHAR(75) null,Phone VARCHAR(75) null,Fax VARCHAR(75) null,Email VARCHAR(75) null,Description VARCHAR(75) null,Representative1 VARCHAR(75) null,RepresentativeTitle1 VARCHAR(75) null,Representative2 VARCHAR(75) null,RepresentativeTitle2 VARCHAR(75) null,Contacter VARCHAR(75) null,Position VARCHAR(75) null,ContactTell VARCHAR(75) null,IsDelete INTEGER,MarkedAsDelete INTEGER,ModifiedDate DATE null,RequestedDate DATE null,SyncVersion VARCHAR(75) null,ShipAgencyShortName VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table DM_SHIP_AGENCY";
	public static final String ORDER_BY_JPQL = " ORDER BY dmShipAgency.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY DM_SHIP_AGENCY.id ASC";
	
}