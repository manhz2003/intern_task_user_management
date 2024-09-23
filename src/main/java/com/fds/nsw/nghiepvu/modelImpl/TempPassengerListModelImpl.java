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
public class TempPassengerListModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp passenger list model instance should use the {@link com.fds.nsw.nghiepvu.model.TempPassengerList} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_PASSENGER_LIST (ID LONG not null primary key,RequestCode VARCHAR(75) null,RequestState INTEGER,DocumentName LONG,UserCreated VARCHAR(75) null,isArrival INTEGER,NameOfShip VARCHAR(75) null,PortOfArrivalCode VARCHAR(75) null,DateOfArrival DATE null,IMONumber VARCHAR(75) null,CallSign VARCHAR(75) null,VoyageNumber VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,ListPassengers INTEGER,SignPlace VARCHAR(75) null,SignDate DATE null,MasterSigned INTEGER,MasterSignedImage INTEGER,AttachedFile LONG,DocumentYear INTEGER,NumberOfLeftPassengers INTEGER,NumberOfVNmeses INTEGER,NumberOfForeigners INTEGER,TotalPassengers INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_PASSENGER_LIST";
	public static final String ORDER_BY_JPQL = " ORDER BY tempPassengerList.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_PASSENGER_LIST.ID DESC";
	
}