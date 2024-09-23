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
public class IssuePortClearanceModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a issue port clearance model instance should use the {@link com.fds.nsw.nghiepvu.model.IssuePortClearance} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table ISSUE_PORT_CLEARANCE (ID LONG not null primary key,RequestCode VARCHAR(75) null,NumberPortClearance VARCHAR(75) null,DocumentName LONG,DocumentYear INTEGER,PortofAuthority VARCHAR(75) null,NameOfShip VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,NumberOfCrews INTEGER,NumberOfPassengers INTEGER,CallSign VARCHAR(75) null,NameOfMaster VARCHAR(75) null,Cargo VARCHAR(75) null,VolumeCargo DOUBLE,CargoUnit VARCHAR(75) null,CargoDescription VARCHAR(75) null,TransitCargo VARCHAR(75) null,VolumeTransitCargo DOUBLE,TransitCargoUnit VARCHAR(75) null,TimeOfDeparture DATE null,NextProvinceCode VARCHAR(75) null,NextPortOfCallCode VARCHAR(75) null,MaritimePortCode VARCHAR(75) null,PortCode VARCHAR(75) null,PortHarbourCode VARCHAR(75) null,ValidUntil DATE null,IssueDate DATE null,DirectorOfMaritime VARCHAR(75) null,CertificateNo VARCHAR(75) null,RequestState INTEGER,GT DOUBLE,DWT DOUBLE,NextMaritimePortCode VARCHAR(75) null,NextPortRegionCode VARCHAR(75) null,NextPortHarbourCode VARCHAR(75) null,NextPortWharfCode VARCHAR(75) null,VersionNo VARCHAR(75) null,IsApproval INTEGER,ApprovalDate DATE null,ApprovalName VARCHAR(75) null,Remarks VARCHAR(75) null,IsCancel INTEGER,CancelDate DATE null,CancelName VARCHAR(75) null,CancelNote VARCHAR(75) null,Representative VARCHAR(75) null,SignDate DATE null,SignName VARCHAR(75) null,SignTitle VARCHAR(75) null,SignPlace VARCHAR(75) null,StampStatus LONG,AttachedFile LONG)";
	public static final String TABLE_SQL_DROP = "drop table ISSUE_PORT_CLEARANCE";
	public static final String ORDER_BY_JPQL = " ORDER BY issuePortClearance.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY ISSUE_PORT_CLEARANCE.ID DESC";
	
}