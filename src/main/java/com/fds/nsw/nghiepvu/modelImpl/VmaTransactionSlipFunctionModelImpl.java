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
public class VmaTransactionSlipFunctionModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma transaction slip function model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaTransactionSlipFunction} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_transaction_slip_function (id_ LONG not null primary key,ItineraryNo VARCHAR(75) null,DocumentaryCode VARCHAR(75) null,NameOfShip VARCHAR(75) null,TransactionTypeCode VARCHAR(75) null,FunctionCode VARCHAR(75) null,ChargeType INTEGER,FunctionNote VARCHAR(75) null,TransactionNote VARCHAR(75) null,ChargeRate DOUBLE,DiscountRate DOUBLE)";
	public static final String TABLE_SQL_DROP = "drop table vma_transaction_slip_function";
	
}