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
public class VmaScheduleTugboatListModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma schedule tugboat list model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_schedule_tugboat_list (id LONG not null primary key,ItineraryNo VARCHAR(75) null,SequenceNo INTEGER,PortofAuthority VARCHAR(75) null,NameOfShip VARCHAR(75) null,TugboatCompanyCode VARCHAR(75) null,TugboatCompanyName VARCHAR(75) null,ShipCode VARCHAR(75) null,ShipName VARCHAR(75) null,Power DOUBLE,UnitPower VARCHAR(75) null,ModifiedDate DATE null,DocumentaryCode VARCHAR(75) null,itineraryScheduleId LONG,GT DOUBLE,UnitGRT VARCHAR(75) null,TugMode VARCHAR(75) null,TugboatShortName VARCHAR(75) null,MakePayment INTEGER,InvoiceDocumentaryCode VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table vma_schedule_tugboat_list";
	
}