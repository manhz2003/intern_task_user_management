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
public class VmaScheduleShipyardModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma schedule shipyard model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaScheduleShipyard} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_schedule_shipyard (id LONG not null primary key,ItineraryNo VARCHAR(75) null,PortofAuthority VARCHAR(75) null,NameOfShip VARCHAR(75) null,DocumentName LONG,DocumentYear INTEGER,NoticeShipType INTEGER,ShipYardCode VARCHAR(75) null,ShipYardCompanyName VARCHAR(75) null,ShipYardOfficialNo VARCHAR(75) null,RepairingFrom DATE null,RepairingTo DATE null,RepairingPlace VARCHAR(75) null,RepairingReason VARCHAR(75) null,Repaired INTEGER,IssueDate DATE null,DirectorOfMaritime VARCHAR(75) null,CertificateNo VARCHAR(75) null,RequestState INTEGER,VersionNo VARCHAR(75) null,IsApproval INTEGER,ApprovalDate DATE null,ApprovalName VARCHAR(75) null,Remarks VARCHAR(75) null,IsCancel INTEGER,CancelDate DATE null,CancelName VARCHAR(75) null,CancelNote VARCHAR(75) null,Representative VARCHAR(75) null,DigitalAttachedFile INTEGER,SignName VARCHAR(75) null,SignTitle VARCHAR(75) null,SignDate DATE null,SignPlace VARCHAR(75) null,AttachedFile LONG,StampStatus INTEGER,SecurityLevelCode VARCHAR(75) null,ShipOwnerCode VARCHAR(75) null,ShipOwnersName VARCHAR(75) null,ShipOperatorCode VARCHAR(75) null,ShipOperatorName VARCHAR(75) null,ShipAgencyCode VARCHAR(75) null,ShipAgencyName VARCHAR(75) null,ShipAgencyAddress VARCHAR(75) null,ShipAgencyContactEmail VARCHAR(75) null,ShipAgencyPhone VARCHAR(75) null,ShipAgencyFax VARCHAR(75) null,DepartmentCode VARCHAR(75) null,DepartmentName VARCHAR(75) null,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table vma_schedule_shipyard";
	
}