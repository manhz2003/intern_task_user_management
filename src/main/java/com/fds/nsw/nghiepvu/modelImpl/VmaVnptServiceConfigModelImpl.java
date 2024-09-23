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
public class VmaVnptServiceConfigModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma vnpt service config model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_vnptserviceconfig (id LONG not null primary key,MaritimeCode VARCHAR(75) null,TaxCode VARCHAR(75) null,PublishServiceSoapAddress VARCHAR(75) null,InvoiceAccount VARCHAR(75) null,InvoiceACpass VARCHAR(75) null,RemoteUsername VARCHAR(75) null,WebservicePassword VARCHAR(75) null,PatternCode VARCHAR(75) null,SerialCode VARCHAR(75) null,remarks VARCHAR(75) null,TestSoapAddress VARCHAR(75) null,TestMode INTEGER,TestInvoiceAccount VARCHAR(75) null,TestInvoiceACpass VARCHAR(75) null,TestRemoteUsername VARCHAR(75) null,TestWebservicePassword VARCHAR(75) null,IsDelete INTEGER,ModifiedDate DATE null,MarkedAsDelete INTEGER,RequestedDate DATE null,SyncVersion VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table vma_vnptserviceconfig";
	public static final String ORDER_BY_JPQL = " ORDER BY vmaVnptServiceConfig.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY vma_vnptserviceconfig.id ASC";
	
}