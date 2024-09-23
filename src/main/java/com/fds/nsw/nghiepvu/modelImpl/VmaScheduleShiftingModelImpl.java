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
public class VmaScheduleShiftingModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma schedule shifting model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaScheduleShifting} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_schedule_shifting (id LONG not null primary key,ItineraryNo VARCHAR(75) null,PortofAuthority VARCHAR(75) null,NameOfShip VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,AnchoringPortHarbourCode VARCHAR(75) null,AnchoringPortWharfCode VARCHAR(75) null,ShiftingPortRegionCode VARCHAR(75) null,ShiftingPortHarbourCode VARCHAR(75) null,ShiftingPortWharfCode VARCHAR(75) null,ShiftingDate DATE null,PurposeCode VARCHAR(75) null,ReasonToShift VARCHAR(75) null,IssueDate DATE null,DirectorOfMaritime VARCHAR(75) null,CertificateNo VARCHAR(75) null,RequestState INTEGER,VersionNo VARCHAR(75) null,IsApproval INTEGER,ApprovalDate DATE null,ApprovalName VARCHAR(75) null,Remarks VARCHAR(75) null,IsCancel INTEGER,CancelDate DATE null,CancelName VARCHAR(75) null,CancelNote VARCHAR(75) null,Representative VARCHAR(75) null,DigitalAttachedFile INTEGER,SignName VARCHAR(75) null,SignTitle VARCHAR(75) null,SignDate DATE null,SignPlace VARCHAR(75) null,AttachedFile LONG,StampStatus INTEGER,ShownDraftxF DOUBLE,UnitShownDraftxF VARCHAR(75) null,ShownDraftxA DOUBLE,UnitShownDraftxA VARCHAR(75) null,LOA DOUBLE,LOAUNIT VARCHAR(75) null,DWT DOUBLE,DWTUNIT VARCHAR(75) null,TugBoat VARCHAR(75) null,From_ VARCHAR(75) null,To_ VARCHAR(75) null,GT INTEGER,NT INTEGER,UnitGRT VARCHAR(75) null,UnitNT VARCHAR(75) null,TaxCodeOfShipownersAgents VARCHAR(75) null,NameOfShipownersAgents VARCHAR(75) null,SecurityLevelCode VARCHAR(75) null,ShipAgencyAddress VARCHAR(75) null,ShipAgencyPhone VARCHAR(75) null,ShipAgencyFax VARCHAR(75) null,ShipAgencyEmail VARCHAR(75) null,ChanelCodeList VARCHAR(75) null,ChanelName VARCHAR(75) null,CargoCode VARCHAR(75) null,QuantityOfCargo VARCHAR(75) null,DepartmentCode VARCHAR(75) null,DepartmentName VARCHAR(75) null,MergedShip VARCHAR(75) null,itineraryScheduleId LONG,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table vma_schedule_shifting";
	
}