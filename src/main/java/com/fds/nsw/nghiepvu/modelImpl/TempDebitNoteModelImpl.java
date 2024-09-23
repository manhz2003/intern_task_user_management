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
public class TempDebitNoteModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp debit note model instance should use the {@link com.fds.nsw.nghiepvu.model.TempDebitnote} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table temp_debitnote (id LONG not null primary key,ATTACHEDFILE LONG,corporationid VARCHAR(75) null,debitnotenumber VARCHAR(75) null,division VARCHAR(75) null,DocumentName LONG,DocumentYear INTEGER,financialaccountant VARCHAR(75) null,fromdate DATE null,markasdeleted INTEGER,organization VARCHAR(75) null,paymenttype INTEGER,reportby VARCHAR(75) null,reportdate DATE null,todate DATE null,totalpayment DOUBLE,currency VARCHAR(75) null,foreigncurrency VARCHAR(75) null,totalforeignpayment DOUBLE,transactionid VARCHAR(75) null,maritimeCode INTEGER,comments VARCHAR(75) null,documentTypeCode VARCHAR(75) null,keypayGoodCode VARCHAR(75) null,keypayMerchantCode VARCHAR(75) null,paymentGateStatusCode VARCHAR(75) null,paymentGateResponseData VARCHAR(75) null,paymentGateCheckCode INTEGER,paymentGateCheckResponseData VARCHAR(75) null,keypayURL VARCHAR(75) null,ItineraryNo VARCHAR(75) null,description VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table temp_debitnote";
	public static final String ORDER_BY_JPQL = " ORDER BY tempDebitNote.reportdate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY temp_debitnote.reportdate DESC";
	
}