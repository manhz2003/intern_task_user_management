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
public class TempMaritimePaymentConfigModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp maritime payment config model instance should use the {@link com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_MARITIME_PAYMENT_CONFIG (ID LONG not null primary key,MARITIMECODE VARCHAR(75) null,MERCHANTCODE VARCHAR(75) null,MERCHANTKEY VARCHAR(75) null,MERCHANTNAME VARCHAR(75) null,KEYPAYDOMAIN VARCHAR(75) null,KEYPAYVERSION VARCHAR(75) null,RETURNURL VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_MARITIME_PAYMENT_CONFIG";
	public static final String ORDER_BY_JPQL = " ORDER BY tempMaritimePaymentConfig.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_MARITIME_PAYMENT_CONFIG.ID ASC";
	
}