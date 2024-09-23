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
public class TempShipSecurityMessageModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp ship security message model instance should use the {@link com.fds.nsw.nghiepvu.model.TempShipSecurityMessage} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_SHIP_SECURITY_MESSAGE (ID LONG not null primary key,RequestCode VARCHAR(75) null,RequestState INTEGER,DocumentYear INTEGER,ShipSecurityCode VARCHAR(75) null,DocumentName LONG,UserCreated VARCHAR(75) null,ShipAgencyCode VARCHAR(75) null,NameOfShipAgent VARCHAR(75) null,ShipName VARCHAR(75) null,ShipTypeCode VARCHAR(75) null,StateCode VARCHAR(75) null,ShipCaptain VARCHAR(75) null,IMO VARCHAR(75) null,GRT DOUBLE,UnitGRT VARCHAR(75) null,CrewNumber LONG,ArrivalDate DATE null,PurposeCode VARCHAR(75) null,PurposeSpecified VARCHAR(75) null,PortRegionCode VARCHAR(75) null,PortWharfCode VARCHAR(75) null,ShipAgencyPhone VARCHAR(75) null,ShipAgencyFax VARCHAR(75) null,IsShipSecurity INTEGER,NameOfISSC VARCHAR(75) null,DateOfISSC DATE null,DateOfExpiryISSC DATE null,SecurityLevelCode VARCHAR(75) null,SecurityFromDate DATE null,IsAdditionalSecurityMeasures INTEGER,AdditionalSecurityDetail VARCHAR(75) null,IsMaintainSecurity INTEGER,MaintainSecurityDetail VARCHAR(75) null,Latitude VARCHAR(75) null,Longitude VARCHAR(75) null,HasShipSecurityPortItems INTEGER,SignPlace VARCHAR(75) null,SignDate DATE null,MasterSigned INTEGER,MasterSignedImage INTEGER,CallSign VARCHAR(75) null,ArrivalPortCode VARCHAR(75) null,PortHarbourCode VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_SHIP_SECURITY_MESSAGE";
	public static final String ORDER_BY_JPQL = " ORDER BY tempShipSecurityMessage.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_SHIP_SECURITY_MESSAGE.ID DESC";
	
}