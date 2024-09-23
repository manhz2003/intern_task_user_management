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
public class VmaItineraryScheduleModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma itinerary schedule model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaItinerarySchedule} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_itinerary_schedule (id LONG not null primary key,ItineraryNo VARCHAR(75) null,DocumentName LONG,DocumentYear INTEGER,NoticeShipType INTEGER,CertificateNo VARCHAR(75) null,ShipBoat VARCHAR(75) null,NameOfShip VARCHAR(75) null,ShipOwnerCode VARCHAR(75) null,ShipOwnersName VARCHAR(75) null,ShipOperatorCode VARCHAR(75) null,ShipOperatorName VARCHAR(75) null,ShipAgencyCode VARCHAR(75) null,ShipAgencyName VARCHAR(75) null,ShipAgencyAddress VARCHAR(75) null,ShipAgencyContactEmail VARCHAR(75) null,ShipAgencyPhone VARCHAR(75) null,ShipAgencyFax VARCHAR(75) null,SecurityLevelCode VARCHAR(75) null,ArrivalPortCode VARCHAR(75) null,PortRegionCode VARCHAR(75) null,PortHarbourCode VARCHAR(75) null,PortWharfCode VARCHAR(75) null,GT DOUBLE,NT DOUBLE,DWT DOUBLE,LOA DOUBLE,Breadth DOUBLE,ClearanceHeight DOUBLE,Power DOUBLE,MaxDraft DOUBLE,ShownDraftxF DOUBLE,ShownDraftxA DOUBLE,UnitLOA VARCHAR(75) null,UnitBreadth VARCHAR(75) null,UnitClearanceHeight VARCHAR(75) null,UnitShownDraftxF VARCHAR(75) null,UnitShownDraftxA VARCHAR(75) null,UnitGRT VARCHAR(75) null,UnitNT VARCHAR(75) null,UnitDWT VARCHAR(75) null,UnitPower VARCHAR(75) null,UnitMaxDraft VARCHAR(75) null,PurposeCode VARCHAR(75) null,PurposeSpecified VARCHAR(75) null,CrewNumber DOUBLE,PassengerNumber DOUBLE,AsPerManifest INTEGER,ContainerNumber DOUBLE,NumberTEU DOUBLE,NumberTNE DOUBLE,TimeOfDeparture DATE null,TimeOfArrival DATE null,TimeOfPORTArrival DATE null,TimeOfPilotOnBoard DATE null,TimeOfApproval DATE null,TugBoat VARCHAR(75) null,DO_ VARCHAR(75) null,FO_ VARCHAR(75) null,FW_ VARCHAR(75) null,OilWater VARCHAR(75) null,QuantityOfCargo VARCHAR(75) null,RemainingCargo VARCHAR(75) null,ShipRequirementsInTermsOfWaste VARCHAR(75) null,PreviousPortsOfCall VARCHAR(75) null,SubsequentPortsOfCall VARCHAR(75) null,DischargedPorts VARCHAR(75) null,PortGoingToStateName VARCHAR(75) null,PortGoingToCode VARCHAR(75) null,LastPortOfCallCode VARCHAR(75) null,LastProvinceCode VARCHAR(75) null,LastMaritimePortCode VARCHAR(75) null,LastPortRegionCode VARCHAR(75) null,LastPortHarbourCode VARCHAR(75) null,LastPortWharfCode VARCHAR(75) null,NextProvinceCode VARCHAR(75) null,NextMaritimePortCode VARCHAR(75) null,NextPortRegionCode VARCHAR(75) null,NextPortHarbourCode VARCHAR(75) null,NextPortWharfCode VARCHAR(75) null,ChanelCodeList VARCHAR(75) null,ChanelName VARCHAR(75) null,Remarks VARCHAR(75) null,RequestCodeNoticeShipMessage VARCHAR(75) null,RequestCodeGeneralDeclaration VARCHAR(75) null,RequestCodeShipSecurityMessage VARCHAR(75) null,ModifiedDate DATE null,AnchorageFreeDuration DOUBLE,CargoType VARCHAR(75) null,DepartmentCode VARCHAR(75) null,DepartmentName VARCHAR(75) null,CheckNoticeApproval INTEGER,CheckBerthPlan INTEGER,CheckPilotPlan INTEGER,CheckTugboatPlan INTEGER,Violated INTEGER,MaritimeRemarks VARCHAR(75) null,Deconstructed INTEGER,MergedShip VARCHAR(75) null,ShipPreviousName VARCHAR(75) null,InitFrom VARCHAR(75) null,RouteLevelCode VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table vma_itinerary_schedule";
	
}