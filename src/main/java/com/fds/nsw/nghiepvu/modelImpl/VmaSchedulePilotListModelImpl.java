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
public class VmaSchedulePilotListModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma schedule pilot list model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaSchedulePilotList} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_schedule_pilot_list (id LONG not null primary key,ItineraryNo VARCHAR(75) null,SequenceNo INTEGER,PortofAuthority VARCHAR(75) null,NameOfShip VARCHAR(75) null,PilotCompanyCode VARCHAR(75) null,PilotCompanyName VARCHAR(75) null,PilotCode VARCHAR(75) null,PilotName VARCHAR(75) null,PilotCategoryCode VARCHAR(75) null,ModifiedDate DATE null,itineraryScheduleId LONG)";
	public static final String TABLE_SQL_DROP = "drop table vma_schedule_pilot_list";
	
}