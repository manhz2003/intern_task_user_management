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
public class TempGeneralDeclarationModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp general declaration model instance should use the {@link com.fds.nsw.nghiepvu.model.TempGeneralDeclaration} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_GENERAL_DECLARATION (ID LONG not null primary key,RequestCode VARCHAR(75) null,RequestState INTEGER,DocumentName LONG,DocumentReference LONG,DocumentYear INTEGER,UserCreated VARCHAR(75) null,IsArrival INTEGER,NameOfShip VARCHAR(75) null,ShipTypeCode VARCHAR(75) null,IMONumber VARCHAR(75) null,CallSign VARCHAR(75) null,VoyageNumber VARCHAR(75) null,PortOfArrivalCode VARCHAR(75) null,DateOfArrival DATE null,PortHarbourCode VARCHAR(75) null,PortRegionCode VARCHAR(75) null,PortWharfCode VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,NameOfMaster VARCHAR(75) null,LastPortOfCallCode VARCHAR(75) null,CertificateOfRegistryNumber VARCHAR(75) null,CertificateOfRegistryDate DATE null,CertificateOfRegistryPortName VARCHAR(75) null,TaxCodeOfShipownersAgents VARCHAR(75) null,NameOfShipownersAgents VARCHAR(75) null,ShipAgencyAddress VARCHAR(75) null,ShipAgencyPhone VARCHAR(75) null,ShipAgencyFax VARCHAR(75) null,ShipAgencyEmail VARCHAR(75) null,GrossTonnage DOUBLE,NetTonnage DOUBLE,PositionOfShipInPort VARCHAR(75) null,BriefParticularsOfVoyage VARCHAR(75) null,PreviousPortsOfCall VARCHAR(75) null,SubsequentPortsOfCall VARCHAR(75) null,DischargedPorts VARCHAR(75) null,RemainingCargo VARCHAR(75) null,BriefDescriptionOfTheCargo VARCHAR(75) null,NumberOfCrew INTEGER,NumberOfPassengers INTEGER,ShipRequirementsInTermsOfWaste VARCHAR(75) null,Remarks VARCHAR(75) null,NumberCargoDeclaration VARCHAR(75) null,NumberShipStoreDeclaration VARCHAR(75) null,NumberCrewList VARCHAR(75) null,NumberPassengerList VARCHAR(75) null,NumberCrewEffects VARCHAR(75) null,NumberHealthMaritime VARCHAR(75) null,SignPlace VARCHAR(75) null,SignDate DATE null,MasterSigned INTEGER,MasterSignedImage INTEGER,CargoOnBoard VARCHAR(75) null,Cargo VARCHAR(75) null,VolumeCargo DOUBLE,CargoUnit VARCHAR(75) null,CargoDescription VARCHAR(75) null,RatedPower VARCHAR(75) null,SeatingCapacity VARCHAR(75) null,LyingCapacity VARCHAR(75) null,MaritimePortCode VARCHAR(75) null,LastProvinceCode VARCHAR(75) null,NextProvinceCode VARCHAR(75) null,LastMaritimePortCode VARCHAR(75) null,LastPortRegionCode VARCHAR(75) null,LastPortHarbourCode VARCHAR(75) null,LastPortWharfCode VARCHAR(75) null,NextMaritimePortCode VARCHAR(75) null,NextPortRegionCode VARCHAR(75) null,NextPortHarbourCode VARCHAR(75) null,NextPortWharfCode VARCHAR(75) null,Link VARCHAR(75) null,QCCode VARCHAR(75) null,PortClearanceNo VARCHAR(75) null,LOA DOUBLE,UnitLOA VARCHAR(75) null,Breadth DOUBLE,UnitBreadth VARCHAR(75) null,ClearanceHeight DOUBLE,UnitClearanceHeight VARCHAR(75) null,ShownDraftxF DOUBLE,UnitShownDraftxF VARCHAR(75) null,ShownDraftxA DOUBLE,UnitShownDraftxA VARCHAR(75) null,DWT DOUBLE,UnitDWT VARCHAR(75) null,TimeOfPORTArrival DATE null,TimeOfPilotOnBoard DATE null,TugBoat VARCHAR(75) null,DO_ VARCHAR(75) null,FO VARCHAR(75) null,FW VARCHAR(75) null,PlaceOfReception VARCHAR(75) null,NameOfShipOwners VARCHAR(75) null,AddressOfShipOwners VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_GENERAL_DECLARATION";
	public static final String ORDER_BY_JPQL = " ORDER BY tempGeneralDeclaration.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_GENERAL_DECLARATION.ID DESC";
	
}