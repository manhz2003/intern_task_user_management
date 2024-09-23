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
public class VmaItineraryModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma itinerary model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaItinerary} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_itinerary (id LONG not null primary key,mtgateway INTEGER,MaritimeCode VARCHAR(75) null,ItineraryNo VARCHAR(75) null,NameOfShip VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,IMONumber VARCHAR(75) null,CallSign VARCHAR(75) null,VRCode VARCHAR(75) null,RegistryNumber VARCHAR(75) null,ShipPosition INTEGER,ShipTypeCode VARCHAR(75) null,VmaShipTypeCode VARCHAR(75) null,ShipCaptain VARCHAR(75) null,PortofAuthority VARCHAR(75) null,RepresentativeofAuthority VARCHAR(75) null,MarkedAsArrival INTEGER,MarkedAsDeparture INTEGER,MarkedAsTransmit INTEGER,MarkedAsVoyage INTEGER,DocumentNameIN LONG,DocumentYearIN INTEGER,DocumentNameOUT LONG,DocumentYearOUT INTEGER,DocumentNameTRA LONG,DocumentYearTRA INTEGER,DocumentNameVOY LONG,DocumentYearVOY INTEGER,VoyageNumber VARCHAR(75) null,TimeOfArrival DATE null,TimeOfDeparture DATE null,ShipOwnerCode VARCHAR(75) null,ShipOwnerName VARCHAR(75) null,ShipOperatorCode VARCHAR(75) null,ShipOperatorName VARCHAR(75) null,ArrivalShipAgencyCode VARCHAR(75) null,ArrivalShipAgency VARCHAR(75) null,DepartureShipAgencyCode VARCHAR(75) null,DepartureShipAgency VARCHAR(75) null,ModifiedDate DATE null,ArrivalPortCode VARCHAR(75) null,LastPortCode VARCHAR(75) null,NextPortCode VARCHAR(75) null,DischargedPorts VARCHAR(75) null,CargoDescription VARCHAR(75) null,TugboatCondition1 VARCHAR(75) null,TugboatCondition2 VARCHAR(75) null,inRequestCodeGeneralDeclaration VARCHAR(75) null,outRequestCodeGeneralDeclaration VARCHAR(75) null,Payment2ServiceStatus INTEGER,Payment2ItineraryStatus INTEGER,Payment2ArrivalStatus INTEGER,Payment2DepartureStatus INTEGER,Payment2AnchorageStatus INTEGER,Payment2CargoStatus INTEGER,DomesticTransportCertificate INTEGER,PortClearancePreviousCertificate VARCHAR(75) null,ShipPreviousName VARCHAR(75) null,MarkupMaintainane INTEGER,MarkupConstruction INTEGER,MarkupDeconstruction INTEGER,Payment2ProtestStatus  INTEGER,NewShipOwnerCode VARCHAR(75) null,NewShipOwnerName VARCHAR(75) null,DocumentaryCode VARCHAR(75) null,InvoiceDocumentaryCode VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table vma_itinerary";
	
}