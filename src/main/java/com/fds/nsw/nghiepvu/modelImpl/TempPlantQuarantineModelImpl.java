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
public class TempPlantQuarantineModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp plant quarantine model instance should use the {@link com.fds.nsw.nghiepvu.model.TempPlantQuarantine} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_PLANT_QUARANTINE (ID LONG not null primary key,RequestCode VARCHAR(75) null,RequestState INTEGER,DocumentName LONG,UserCreated VARCHAR(75) null,DocumentYear INTEGER,NameOfShip VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,NameOfMaster VARCHAR(75) null,DoctorName VARCHAR(75) null,NumberOfCrew INTEGER,NumberOfPassengers INTEGER,LastPortOfCallCode VARCHAR(75) null,NextPortOfCallCode VARCHAR(75) null,FirstPortOfLoadingCode VARCHAR(75) null,DateOfDeparture DATE null,PlantNameFirst VARCHAR(75) null,PlantNameBetween VARCHAR(75) null,PlantNameThis VARCHAR(75) null,SignPlace VARCHAR(75) null,SignDate DATE null,MasterSigned INTEGER,MasterSignedImage INTEGER,AttachedFile LONG)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_PLANT_QUARANTINE";
	public static final String ORDER_BY_JPQL = " ORDER BY tempPlantQuarantine.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_PLANT_QUARANTINE.ID DESC";
	
}