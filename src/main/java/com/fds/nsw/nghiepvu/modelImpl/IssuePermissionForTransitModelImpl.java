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
public class IssuePermissionForTransitModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a issue permission for transit model instance should use the {@link com.fds.nsw.nghiepvu.model.IssuePermissionForTransit} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table ISSUE_PERMISSION_FOR_TRANSIT (ID LONG not null primary key,RequestCode VARCHAR(75) null,NumberPermissionForTransit VARCHAR(75) null,DocumentName LONG,DocumentYear INTEGER,RequestState INTEGER,PortofAuthority VARCHAR(75) null,NameOfShip VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,GT DOUBLE,NumberOfCrews INTEGER,NumberOfPassengers INTEGER,CallSign VARCHAR(75) null,NameOfMaster VARCHAR(75) null,TransitCargo VARCHAR(75) null,VolumeCargo DOUBLE,CargoUnit VARCHAR(75) null,CargoDescription VARCHAR(75) null,PermittedTransitFrom VARCHAR(75) null,PermittedTransitTo VARCHAR(75) null,TimeOfDeparture DATE null,ValidUntil DATE null,DateOfSign DATE null,UserCreated VARCHAR(75) null,DirectorOfMaritime VARCHAR(75) null,CreatedDate DATE null,CertificateNo VARCHAR(75) null,VersionNo VARCHAR(75) null,IsApproval INTEGER,ApprovalDate DATE null,ApprovalName VARCHAR(75) null,Remarks VARCHAR(75) null,IsCancel INTEGER,CancelDate DATE null,CancelName VARCHAR(75) null,CancelNote VARCHAR(75) null,Representative VARCHAR(75) null,DigitalAttachedFile LONG,SignDate DATE null,SignName VARCHAR(75) null,SignTitle VARCHAR(75) null,SignPlace VARCHAR(75) null,StampStatus LONG,AttachedFile LONG)";
	public static final String TABLE_SQL_DROP = "drop table ISSUE_PERMISSION_FOR_TRANSIT";
	public static final String ORDER_BY_JPQL = " ORDER BY issuePermissionForTransit.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY ISSUE_PERMISSION_FOR_TRANSIT.ID DESC";
	
}