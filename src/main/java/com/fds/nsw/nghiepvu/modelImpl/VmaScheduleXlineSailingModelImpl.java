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
public class VmaScheduleXlineSailingModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma schedule xline sailing model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_schedule_xline_sailing (id LONG not null primary key,ShipOperatorCode VARCHAR(75) null,ScheduleYear INTEGER,ScheduleMonth INTEGER,SequenceNo INTEGER,PortofAuthority VARCHAR(75) null,NameOfShip VARCHAR(75) null,IMONumber VARCHAR(75) null,CallSign VARCHAR(75) null,RegistryNumber VARCHAR(75) null,VoyageNo VARCHAR(75) null,TimeOfArrival DATE null,TimeOfDeparture DATE null,StateCode VARCHAR(75) null,ProvinceCode VARCHAR(75) null,MaritimePortCode VARCHAR(75) null,PortGoingToStateName VARCHAR(75) null,PortGoingToCode VARCHAR(75) null,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table vma_schedule_xline_sailing";
	
}