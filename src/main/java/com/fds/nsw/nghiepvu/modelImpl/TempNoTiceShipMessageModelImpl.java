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
public class TempNoTiceShipMessageModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp no tice ship message model instance should use the {@link com.fds.nsw.nghiepvu.model.TempNoticeShipMessage} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_NOTICE_SHIP_MESSAGE (ID LONG not null primary key,RequestCode VARCHAR(75) null,RequestState INTEGER,NoticeShipType VARCHAR(75) null,NoticeShipCode VARCHAR(75) null,DocumentName LONG,UserCreated VARCHAR(75) null,ConfirmTime INTEGER,ShipName VARCHAR(75) null,ShipTypeCode VARCHAR(75) null,StateCode VARCHAR(75) null,ShipCaptain VARCHAR(75) null,IMO VARCHAR(75) null,GRT DOUBLE,DWT DOUBLE,UnitGRT VARCHAR(75) null,UnitDWT VARCHAR(75) null,CallSign VARCHAR(75) null,ArrivalDate DATE null,ArrivalPortCode VARCHAR(75) null,PortHarbourCode VARCHAR(75) null,PortRegionCode VARCHAR(75) null,PortWharfCode VARCHAR(75) null,PortGoingToStateName VARCHAR(75) null,PortGoingToCode VARCHAR(75) null,NameOfShipOwners VARCHAR(75) null,AddressOfShipOwners VARCHAR(75) null,ShipOwnerStateCode VARCHAR(75) null,ShipOwnerPhone VARCHAR(75) null,ShipOwnerFax VARCHAR(75) null,ShipOwnerEmail VARCHAR(75) null,LOA DOUBLE,Breadth DOUBLE,ClearanceHeight DOUBLE,ShownDraftxF DOUBLE,ShownDraftxA DOUBLE,UnitLOA VARCHAR(75) null,UnitBreadth VARCHAR(75) null,UnitClearanceHeight VARCHAR(75) null,UnitShownDraftxF VARCHAR(75) null,UnitShownDraftxA VARCHAR(75) null,CertificateOfRegistryNumber VARCHAR(75) null,CertificateOfRegistryDate DATE null,CertificateOfRegistryPortName VARCHAR(75) null,ShipAgencyCode VARCHAR(75) null,ShipAgencyAddress VARCHAR(75) null,ShipAgencyPhone VARCHAR(75) null,ShipAgencyFax VARCHAR(75) null,ShipAgencyEmail VARCHAR(75) null,PurposeCode VARCHAR(75) null,PurposeSpecified VARCHAR(75) null,CrewNumber LONG,UnitCrew VARCHAR(75) null,PassengerNumber LONG,UnitPassenger VARCHAR(75) null,QuantityOfCargo VARCHAR(75) null,UnitQuantityofCargo VARCHAR(75) null,TypeOfCargo VARCHAR(75) null,OtherPersons INTEGER,Remarks VARCHAR(75) null,SignPlace VARCHAR(75) null,SignDate DATE null,MasterSigned INTEGER,MasterSignedImage INTEGER,DocumentYear INTEGER,NameOfShipAgent VARCHAR(75) null,DocumentReference LONG,IsArrival INTEGER,VoyageNumber VARCHAR(75) null,NetTonnage DOUBLE,NetTonnageUnit VARCHAR(75) null,PositionOfShipInPort VARCHAR(75) null,BriefParticularsOfVoyage VARCHAR(75) null,PreviousPortsOfCall VARCHAR(75) null,SubsequentPortsOfCall VARCHAR(75) null,DischargedPorts VARCHAR(75) null,RemainingCargo VARCHAR(75) null,ShipRequirementsInTermsOfWaste VARCHAR(75) null,NumberCargoDeclaration INTEGER,NumberShipStoreDeclaration INTEGER,NumberCrewList INTEGER,NumberPassengerList INTEGER,NumberCrewEffectsDeclaration INTEGER,NumberHealthMaritimeDeclaration INTEGER,PortClearanceNo VARCHAR(75) null,TimeOfPORTArrival DATE null,TimeOfPilotOnBoard DATE null,TugBoat VARCHAR(75) null,DO_ VARCHAR(75) null,FO VARCHAR(75) null,FW VARCHAR(75) null,PlaceOfReception VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_NOTICE_SHIP_MESSAGE";
	public static final String ORDER_BY_JPQL = " ORDER BY tempNoTiceShipMessage.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_NOTICE_SHIP_MESSAGE.ID DESC";
	
}