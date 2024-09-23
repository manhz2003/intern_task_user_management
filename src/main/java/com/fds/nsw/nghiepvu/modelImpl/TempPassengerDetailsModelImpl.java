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
public class TempPassengerDetailsModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp passenger details model instance should use the {@link com.fds.nsw.nghiepvu.model.TempPassengerDetails} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_PASSENGER_DETAILS (ID LONG not null primary key,RequestCode VARCHAR(75) null,PassengerCode VARCHAR(75) null,FamilyName VARCHAR(75) null,GivenName VARCHAR(75) null,Nationality VARCHAR(75) null,BirthDay DATE null,BirthPlace VARCHAR(75) null,TypeOfIdentity VARCHAR(75) null,PassportExpiredDate DATE null,SerialNumberOfIdentity VARCHAR(75) null,PortOfEmbarkation VARCHAR(75) null,PortOfDisembarkation VARCHAR(75) null,IsTransit INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_PASSENGER_DETAILS";
	public static final String ORDER_BY_JPQL = " ORDER BY tempPassengerDetails.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_PASSENGER_DETAILS.ID ASC";
	
}