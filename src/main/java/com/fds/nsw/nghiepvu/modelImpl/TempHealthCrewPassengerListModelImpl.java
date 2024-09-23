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
public class TempHealthCrewPassengerListModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp health crew passenger list model instance should use the {@link com.fds.nsw.nghiepvu.model.TempHealthCrewPassengerList} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_HEALTH_CREW_PAX_LIST (ID LONG not null primary key,RequestCode VARCHAR(75) null,HealthCrewPassengerCode VARCHAR(75) null,PassengerOrCrewCode VARCHAR(75) null,PassengerOrCrewName VARCHAR(75) null,ClassOrRating INTEGER,StateVisitedCode VARCHAR(75) null,PortVisitedCode VARCHAR(75) null,FromDate DATE null,ToDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_HEALTH_CREW_PAX_LIST";
	public static final String ORDER_BY_JPQL = " ORDER BY tempHealthCrewPassengerList.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_HEALTH_CREW_PAX_LIST.ID ASC";
	
}