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
public class TempShipStoresDeclarationModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp ship stores declaration model instance should use the {@link com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_SHIP_STORES_DECLARATION (ID LONG not null primary key,RequestCode VARCHAR(75) null,RequestState INTEGER,DocumentName LONG,DocumentYear INTEGER,UserCreated VARCHAR(75) null,isArrival INTEGER,NameOfShip VARCHAR(75) null,IMONumber VARCHAR(75) null,Callsign VARCHAR(75) null,VoyageNumber VARCHAR(75) null,NationalityOfShip VARCHAR(75) null,PortOfArrivalCode VARCHAR(75) null,DateOfArrival DATE null,LastPortOfCallCode VARCHAR(75) null,NumberOfPersonsOnBoat INTEGER,PeriodOfStay VARCHAR(75) null,PeriodOfStayUnit VARCHAR(75) null,NameOfMaster VARCHAR(75) null,ListShipsStores INTEGER,SignPlace VARCHAR(75) null,SignDate DATE null,MasterSigned INTEGER,MasterSignedImage INTEGER,AttachedFile LONG)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_SHIP_STORES_DECLARATION";
	public static final String ORDER_BY_JPQL = " ORDER BY tempShipStoresDeclaration.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_SHIP_STORES_DECLARATION.ID DESC";
	
}