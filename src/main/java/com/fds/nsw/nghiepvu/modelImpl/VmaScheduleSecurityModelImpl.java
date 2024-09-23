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
public class VmaScheduleSecurityModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma schedule security model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaScheduleSecurity} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_schedule_security (id LONG not null primary key,ItineraryNo VARCHAR(75) null,PortofAuthority VARCHAR(75) null,NameOfShip VARCHAR(75) null,DocumentName LONG,DocumentYear INTEGER,NoticeShipType INTEGER,SecurityOfficeCode VARCHAR(75) null,SecurityCompanyName VARCHAR(75) null,SecurityOfficialNo VARCHAR(75) null,SecurityDate DATE null,SecurityPlace VARCHAR(75) null,SecurityReason VARCHAR(75) null,Evacuated INTEGER,EvacuateOfficialCode VARCHAR(75) null,EvacuateCompanyName VARCHAR(75) null,EvacuateOfficialNo VARCHAR(75) null,EvacuateDate DATE null,EvacuateReason VARCHAR(75) null,Representative VARCHAR(75) null,DigitalAttachedFile INTEGER,SignName VARCHAR(75) null,SignTitle VARCHAR(75) null,SignDate DATE null,SignPlace VARCHAR(75) null,AttachedFile LONG,StampStatus INTEGER,SecurityLevelCode VARCHAR(75) null,ShipOwnerCode VARCHAR(75) null,ShipOwnersName VARCHAR(75) null,ShipOperatorCode VARCHAR(75) null,ShipOperatorName VARCHAR(75) null,ShipAgencyCode VARCHAR(75) null,ShipAgencyName VARCHAR(75) null,ShipAgencyAddress VARCHAR(75) null,ShipAgencyContactEmail VARCHAR(75) null,ShipAgencyPhone VARCHAR(75) null,ShipAgencyFax VARCHAR(75) null,DepartmentCode VARCHAR(75) null,DepartmentName VARCHAR(75) null,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table vma_schedule_security";
	
}