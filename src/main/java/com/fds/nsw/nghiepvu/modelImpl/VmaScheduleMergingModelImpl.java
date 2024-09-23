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
public class VmaScheduleMergingModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma schedule merging model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaScheduleMerging} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_schedule_merging (id LONG not null primary key,ItineraryNo VARCHAR(75) null,SequenceNo INTEGER,PortofAuthority VARCHAR(75) null,DocumentName LONG,DocumentYear INTEGER,NoticeShipType INTEGER,ShipBoat VARCHAR(75) null,NameOfShip VARCHAR(75) null,ShipOwnerCode VARCHAR(75) null,ShipOwnersName VARCHAR(75) null,ShipOperatorCode VARCHAR(75) null,ShipOperatorName VARCHAR(75) null,ShipAgencyCode VARCHAR(75) null,ShipAgencyName VARCHAR(75) null,ShipAgencyAddress VARCHAR(75) null,ShipAgencyContactEmail VARCHAR(75) null,ShipAgencyPhone VARCHAR(75) null,ShipAgencyFax VARCHAR(75) null,SecurityLevelCode VARCHAR(75) null,ArrivalPortCode VARCHAR(75) null,PortRegionCode VARCHAR(75) null,PortHarbourCode VARCHAR(75) null,PortWharfCode VARCHAR(75) null,GT DOUBLE,NT DOUBLE,DWT DOUBLE,LOA DOUBLE,Breadth DOUBLE,ClearanceHeight DOUBLE,Power DOUBLE,MaxDraft DOUBLE,ShownDraftxF DOUBLE,ShownDraftxA DOUBLE,UnitLOA VARCHAR(75) null,UnitBreadth VARCHAR(75) null,UnitClearanceHeight VARCHAR(75) null,UnitShownDraftxF VARCHAR(75) null,UnitShownDraftxA VARCHAR(75) null,UnitGRT VARCHAR(75) null,UnitNT VARCHAR(75) null,UnitDWT VARCHAR(75) null,UnitPower VARCHAR(75) null,UnitMaxDraft VARCHAR(75) null,PurposeCode VARCHAR(75) null,PurposeSpecified VARCHAR(75) null,CrewNumber DOUBLE,PassengerNumber DOUBLE,MergeStatus VARCHAR(75) null,ShipLashRegistryNumber VARCHAR(75) null,ShipLashName VARCHAR(75) null,MergeDateFrom DATE null,MergeDateTo DATE null,ModifiedDate DATE null,ShipTypeMT VARCHAR(75) null,ShipTypeCode VARCHAR(75) null,ShipLashIMONumber VARCHAR(75) null,ShipLashCallSign VARCHAR(75) null,ShipLashFlagStateOfShip VARCHAR(75) null,ShipLashVRCode VARCHAR(75) null,MakePayment INTEGER,DocumentaryCode VARCHAR(75) null,itineraryScheduleId LONG)";
	public static final String TABLE_SQL_DROP = "drop table vma_schedule_merging";
	
}