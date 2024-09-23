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
public class VmaTransactionReceiptModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma transaction receipt model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaTransactionReceipt} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_transaction_receipt (id LONG not null primary key,MaritimeCode VARCHAR(75) null,SequenceNo INTEGER,TransactionTypeCode VARCHAR(75) null,TransactionFunctionNote VARCHAR(75) null,TransactionNoteV VARCHAR(75) null,TransactionNoteE VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table vma_transaction_receipt";
	public static final String ORDER_BY_JPQL = " ORDER BY vmaTransactionReceipt.maritimeCode ASC, vmaTransactionReceipt.sequenceNo ASC, vmaTransactionReceipt.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY vma_transaction_receipt.MaritimeCode ASC, vma_transaction_receipt.SequenceNo ASC, vma_transaction_receipt.id ASC";
	
}