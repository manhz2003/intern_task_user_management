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
public class VmaItineraryProtestModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma itinerary protest model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaItineraryProtest} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_itinerary_protest (id LONG not null primary key,ItineraryNo VARCHAR(75) null,SequenceNo INTEGER,PortofAuthority VARCHAR(75) null,NameOfShip VARCHAR(75) null,DocumentName LONG,DocumentYear INTEGER,NoticeShipType INTEGER,ShipOwnerCode VARCHAR(75) null,ShipOwnersName VARCHAR(75) null,ShipOperatorCode VARCHAR(75) null,ShipOperatorName VARCHAR(75) null,ShipAgencyCode VARCHAR(75) null,ShipAgencyName VARCHAR(75) null,ShipAgencyAddress VARCHAR(75) null,ShipAgencyContactEmail VARCHAR(75) null,ShipAgencyPhone VARCHAR(75) null,ShipAgencyFax VARCHAR(75) null,SecurityLevelCode VARCHAR(75) null,DepartmentCode VARCHAR(75) null,DepartmentName VARCHAR(75) null,ProtestDate DATE null,ProtestRemarks VARCHAR(75) null,MakePayment INTEGER,ModifiedDate DATE null,TroubleShootingDate DATE null,FinishedDate DATE null,DocumentaryCode VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table vma_itinerary_protest";
	
}