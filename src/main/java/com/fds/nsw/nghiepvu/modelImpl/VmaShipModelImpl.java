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
public class VmaShipModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma ship model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaShip} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_ship (id LONG not null primary key,mtgateway INTEGER,MaritimeCode VARCHAR(75) null,ShipName VARCHAR(75) null,ShipPreviousName VARCHAR(75) null,ShipTypeMT VARCHAR(75) null,ShipTypeCode VARCHAR(75) null,ShipBoat VARCHAR(75) null,HasTugBoat INTEGER,TugBoatName VARCHAR(75) null,NameOfMaster VARCHAR(75) null,ChiefOfEngineer VARCHAR(75) null,IMONumber VARCHAR(75) null,CallSign VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,VRCode VARCHAR(75) null,RegistryNumber VARCHAR(75) null,RegistryDate DATE null,RegistryPortCode VARCHAR(75) null,GT DOUBLE,NT DOUBLE,DWT DOUBLE,LOA DOUBLE,Breadth DOUBLE,ClearanceHeight DOUBLE,Power DOUBLE,MaxDraft DOUBLE,ShownDraftxF DOUBLE,ShownDraftxA DOUBLE,UnitPower VARCHAR(75) null,ProductionCountry VARCHAR(75) null,ProductionYear VARCHAR(75) null,ShipOwnerCode VARCHAR(75) null,ShipOperatorCode VARCHAR(75) null,ShipAgencyCode VARCHAR(75) null,ExpiredDate DATE null,Remarks VARCHAR(75) null,IsDelete INTEGER,ModifiedDate DATE null,MarkedAsDelete INTEGER,RequestedDate DATE null,SyncVersion VARCHAR(75) null,CertificateOfMaster VARCHAR(75) null,CertificateChiefOfEngineer VARCHAR(75) null,SecurityLevelCode VARCHAR(75) null,Violated INTEGER,Seat INTEGER,Lies INTEGER,Passengers INTEGER,Craneload DOUBLE,UnitCraneload VARCHAR(75) null,Deconstructed INTEGER,constructionShipyardCode VARCHAR(75) null,deconstructionShipyardCode VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table vma_ship";
	public static final String ORDER_BY_JPQL = " ORDER BY vmaShip.imoNumber ASC, vmaShip.callSign ASC, vmaShip.vrCode ASC, vmaShip.registryNumber ASC";
	public static final String ORDER_BY_SQL = " ORDER BY vma_ship.IMONumber ASC, vma_ship.CallSign ASC, vma_ship.VRCode ASC, vma_ship.RegistryNumber ASC";
	
}