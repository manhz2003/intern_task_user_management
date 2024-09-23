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
public class TempDocumentModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp document model instance should use the {@link com.fds.nsw.nghiepvu.model.TempDocument} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_DOCUMENT (ID LONG not null primary key,RequestCode VARCHAR(75) null,RequestState INTEGER,DocumentName LONG,DocumentYear INTEGER,DocumentTypeCode VARCHAR(75) null,UserCreated VARCHAR(75) null,ShipAgencyCode VARCHAR(75) null,ShipName VARCHAR(75) null,ShipTypeCode VARCHAR(75) null,StateCode VARCHAR(75) null,ShipCaptain VARCHAR(75) null,IMO VARCHAR(75) null,GRT DOUBLE,NT DOUBLE,DWT DOUBLE,UnitGRT VARCHAR(75) null,UnitNT VARCHAR(75) null,UnitDWT VARCHAR(75) null,CallSign VARCHAR(75) null,PreDocumentName VARCHAR(75) null,CreatedDate DATE null,LastModifiedDate DATE null,FlowFlag INTEGER,DocumentStatusCode INTEGER,LocationCode VARCHAR(75) null,MaritimeCode VARCHAR(75) null,PortRegionCode VARCHAR(75) null,PortCode VARCHAR(75) null,LastPortCode VARCHAR(75) null,ShipPosition INTEGER,ShipOwnerCode VARCHAR(75) null,ArrivalShipAgency VARCHAR(75) null,DepartureShipAgency VARCHAR(75) null,ShipDateFrom DATE null,ShipDateTo DATE null,UpgradeVersion INTEGER,NameOfShipownersAgents VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_DOCUMENT";
	public static final String ORDER_BY_JPQL = " ORDER BY tempDocument.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_DOCUMENT.ID ASC";
	
}