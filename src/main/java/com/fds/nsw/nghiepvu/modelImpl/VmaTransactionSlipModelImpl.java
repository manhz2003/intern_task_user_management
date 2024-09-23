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
public class VmaTransactionSlipModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma transaction slip model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaTransactionSlip} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_transaction_slip (id LONG not null primary key,ItineraryNo VARCHAR(75) null,SequenceNo INTEGER,DocumentaryCode VARCHAR(75) null,SupplementDocumentary VARCHAR(75) null,ApprovalDate DATE null,FundTransferNo VARCHAR(75) null,FundTransferDate DATE null,FundTransferDetails VARCHAR(75) null,ReceiptNo VARCHAR(75) null,ReceiptDate DATE null,ReceiptDetails VARCHAR(75) null,ReceiptRemark VARCHAR(75) null,ReceiptBookNo VARCHAR(75) null,eReceiptNo VARCHAR(75) null,eReceiptDate DATE null,eReceiptDetails VARCHAR(75) null,EmailRecipients VARCHAR(75) null,PortClearanceCertificateNo VARCHAR(75) null,DocumentaryKind VARCHAR(75) null,DocumentaryNo VARCHAR(75) null,DocumentaryIssued DATE null,SignTitle VARCHAR(75) null,SignName VARCHAR(75) null,PortofAuthority VARCHAR(75) null,NameOfShip VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,IMONumber VARCHAR(75) null,CallSign VARCHAR(75) null,RegistryNumber VARCHAR(75) null,GT DOUBLE,DWT DOUBLE,ArrivalPortName VARCHAR(75) null,LastPortName VARCHAR(75) null,NextPortName VARCHAR(75) null,DischargedPorts VARCHAR(75) null,CargoDescription VARCHAR(75) null,ArrivalDate DATE null,DepartureDate DATE null,NoticeShipType INTEGER,DoCharge VARCHAR(75) null,ShipAgencyCode VARCHAR(75) null,ShipAgencyName VARCHAR(75) null,ShipOwnerCode VARCHAR(75) null,ShipOwnerName VARCHAR(75) null,ShipOperationName VARCHAR(75) null,HideShipOwnerShipAgency INTEGER,PaymentBy VARCHAR(75) null,PaymentName VARCHAR(75) null,PaymentType INTEGER,MakePayment2Arrival INTEGER,MakePayment2Departure INTEGER,PaymentStatus INTEGER,AttachedFile INTEGER,fromDate DATE null,toDate DATE null,PaymentCategory INTEGER,TrackChangesCargoList INTEGER,TrachChangesAnchorage INTEGER,TrachChangesProtest INTEGER,PaidAdvanceAmount DOUBLE,StampStatus INTEGER,StampSerialNo VARCHAR(75) null,Reportby VARCHAR(75) null,Reportdate DATE null,Tax DOUBLE,HideExchangeRate INTEGER,ExchangeRate DOUBLE,CurrencyExgDate DATE null,DocumentaryExchangeRate DOUBLE,DocumentaryCurrencyExgDate DATE null,ExchangeRateDifferences DOUBLE,DifferencesExgDate DATE null,PaymentDifferences DOUBLE,PaymentInFigures DOUBLE,PaymentRealAmount DOUBLE,PaymentAmount DOUBLE,PaymentDate DATE null,F1319Vnd DOUBLE,F1311Vnd DOUBLE,F1312Usd DOUBLE,F1329Vnd DOUBLE,F1321Vnd DOUBLE,F1322Usd DOUBLE,F1339Vnd DOUBLE,F1331Vnd DOUBLE,F1332Usd DOUBLE,F1349Vnd DOUBLE,F1341Vnd DOUBLE,F1342Usd DOUBLE,F1359Vnd DOUBLE,F1351Vnd DOUBLE,F1352Usd DOUBLE,F1369Vnd DOUBLE,F1361Vnd DOUBLE,F1362Usd DOUBLE,F1379Vnd DOUBLE,F1371Vnd DOUBLE,F1372Usd DOUBLE,F1389Vnd DOUBLE,F1381Vnd DOUBLE,F1382Usd DOUBLE,F1399Vnd DOUBLE,F1391Vnd DOUBLE,F1392Usd DOUBLE,F1309Vnd DOUBLE,F1301Vnd DOUBLE,F1302Usd DOUBLE,F1313Vnd DOUBLE,F1363Vnd DOUBLE,F1373Vnd DOUBLE,TotalAnchorageHours VARCHAR(75) null,NumberItineraryPerMonth VARCHAR(75) null,MainPurpose VARCHAR(75) null,F1411Vnd DOUBLE,F1412Vnd DOUBLE,F1413Vnd DOUBLE,F1421Vnd DOUBLE,F1422Vnd DOUBLE,F1423Vnd DOUBLE,F1431Vnd DOUBLE,F1432Vnd DOUBLE,NameOfPortFacility VARCHAR(75) null,AddressOfPortFacility VARCHAR(75) null,StatementNumber VARCHAR(75) null,DateOfStatement DATE null,StatementIssuedAt VARCHAR(75) null,StatementValidUntil DATE null,F1411Remarks VARCHAR(75) null,F1412Remarks VARCHAR(75) null,F1413Remarks VARCHAR(75) null,F1421Remarks VARCHAR(75) null,F1422Remarks VARCHAR(75) null,F1423Remarks VARCHAR(75) null,F1431Remarks VARCHAR(75) null,F1432Remarks VARCHAR(75) null,VndTotalAmount DOUBLE,UsdTotalAmount DOUBLE,AmountInWordsVnd VARCHAR(75) null,AmountInWordsUsd VARCHAR(75) null,FinancialAccountant VARCHAR(75) null,DepartmentCode VARCHAR(75) null,DepartmentName VARCHAR(75) null,debitnoteid INTEGER,GtRemarks VARCHAR(75) null,F1311Remarks VARCHAR(75) null,F1312Remarks VARCHAR(75) null,F1321Remarks VARCHAR(75) null,F1322Remarks VARCHAR(75) null,F1331Remarks VARCHAR(75) null,F1332Remarks VARCHAR(75) null,F1341Remarks VARCHAR(75) null,F1342Remarks VARCHAR(75) null,F1351Remarks VARCHAR(75) null,F1352Remarks VARCHAR(75) null,F1361Remarks VARCHAR(75) null,F1362Remarks VARCHAR(75) null,F1371Remarks VARCHAR(75) null,F1372Remarks VARCHAR(75) null,F1381Remarks VARCHAR(75) null,F1382Remarks VARCHAR(75) null,F1391Remarks VARCHAR(75) null,F1392Remarks VARCHAR(75) null,F1301Remarks VARCHAR(75) null,F1302Remarks VARCHAR(75) null,F1313Remarks VARCHAR(75) null,F1363Remarks VARCHAR(75) null,F1373Remarks VARCHAR(75) null,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table vma_transaction_slip";
	
}