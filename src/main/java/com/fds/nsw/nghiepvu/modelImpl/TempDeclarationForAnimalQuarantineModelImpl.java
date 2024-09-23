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
public class TempDeclarationForAnimalQuarantineModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp declaration for animal quarantine model instance should use the {@link com.fds.nsw.nghiepvu.model.TempDeclarationForAnimalQuarantine} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table temp_animal_quarantine (ID LONG not null primary key,RequestCode VARCHAR(75) null,RequestState INTEGER,DocumentName LONG,DocumentYear INTEGER,UserCreated VARCHAR(75) null,NameOfShip VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,NumberOfCrew INTEGER,NumberOfPassengers INTEGER,LastPortOfCallCode VARCHAR(75) null,NextPortOfCallCode VARCHAR(75) null,AnimalNameFirst VARCHAR(75) null,AnimalNameBetween VARCHAR(75) null,AnimalNameThis VARCHAR(75) null,NameOfMaster VARCHAR(75) null,SignPlace VARCHAR(75) null,SignDate DATE null,MasterSigned INTEGER,MasterSignedImage INTEGER,AttachedFile LONG)";
	public static final String TABLE_SQL_DROP = "drop table temp_animal_quarantine";
	public static final String ORDER_BY_JPQL = " ORDER BY tempDeclarationForAnimalQuarantine.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY temp_animal_quarantine.ID ASC";
	
}