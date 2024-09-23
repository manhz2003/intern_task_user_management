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
public class VmaAnNinhCangBienModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma an ninh cang bien model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_anninh_cangbien (ID LONG not null primary key,MaritimeCode VARCHAR(75) null,CertificateNo VARCHAR(75) null,IssueDate DATE null,ExpireDate DATE null,IssuePlace VARCHAR(75) null,AttachFileId INTEGER,PortFacilityName VARCHAR(75) null,PortFacilityAddress VARCHAR(75) null,SecurityName VARCHAR(75) null,SecurityContact VARCHAR(75) null,ExpireStatus VARCHAR(75) null,FirstAuthorized INTEGER,FirstVerificationDate DATE null,SecondAuthorized INTEGER,SecondVerificationDate DATE null,ThirdAuthorized INTEGER,ThirdVerificationDate DATE null,FourthAuthorized INTEGER,FourthVerificationDate DATE null,PassengerShipApproval INTEGER,PassengerHighSpeedApproval INTEGER,CargoHighSpeedApproval INTEGER,BulkCarrierApproval INTEGER,OilTankerApproval INTEGER,ChemicalTankerApproval INTEGER,GasCarrierApproval INTEGER,MobileOffshoreApproval INTEGER,OtherCargoShipApproval INTEGER,MarkedAsDelete INTEGER,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table vma_anninh_cangbien";
	
}