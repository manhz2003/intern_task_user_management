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
public class maTransactionFunctionModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ma transaction function model instance should use the {@link com.fds.nsw.nghiepvu.model.maTransactionFunction} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_transaction_function (id LONG not null primary key,PortofAuthority VARCHAR(75) null,TransactionTypeCode VARCHAR(75) null,FunctionCode VARCHAR(75) null,FunctionName VARCHAR(75) null,MakePayment INTEGER,ForArrival INTEGER,ForDeparture INTEGER,ChargeType INTEGER,FunctionNote VARCHAR(75) null,TransactionNote VARCHAR(75) null,ChargeRate DOUBLE,ChargeConditions DOUBLE,DiscountRate INTEGER,DiscountType1 INTEGER,DiscountType2 INTEGER,DiscountType3 INTEGER,DiscountType4 INTEGER,DiscountType5 INTEGER,applied VARCHAR(75) null,appliedFrom DATE null,appliedTo DATE null,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table vma_transaction_function";
	
}