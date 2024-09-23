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
public class TempDeclarationOfHealthModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp declaration of health model instance should use the {@link com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_DECLARATION_OF_HEALTH (ID LONG not null primary key,RequestCode VARCHAR(75) null,RequestState INTEGER,DocumentName LONG,DocumentYear INTEGER,UserCreated VARCHAR(75) null,SubmittedPortCode VARCHAR(75) null,DateSubmitted DATE null,NameOfShip VARCHAR(75) null,Registration VARCHAR(75) null,IMONumber VARCHAR(75) null,ArrivingFrom VARCHAR(75) null,SailingTo VARCHAR(75) null,Nationality VARCHAR(75) null,MasterName VARCHAR(75) null,GrossTonnage DOUBLE,GrossTonnageUnit VARCHAR(75) null,Tonnage DOUBLE,TonnageUnit VARCHAR(75) null,CertificateCarried INTEGER,IssuedAt VARCHAR(75) null,IssueDate DATE null,ReInspectionRequired INTEGER,IsShipVisitedWHO INTEGER,VisitedWHOPortCode VARCHAR(75) null,DateOfVisitedWHO DATE null,ListPortName VARCHAR(75) null,DoctorName VARCHAR(75) null,DoctorSignDate DATE null,SignPlace VARCHAR(75) null,SignDate DATE null,MasterSigned INTEGER,MasterSignedImage INTEGER,AttachedFile LONG)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_DECLARATION_OF_HEALTH";
	public static final String ORDER_BY_JPQL = " ORDER BY tempDeclarationOfHealth.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_DECLARATION_OF_HEALTH.ID DESC";
	
}