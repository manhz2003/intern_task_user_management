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
public class InterfaceRequestFieldModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a interface request field model instance should use the {@link com.fds.nsw.nghiepvu.model.InterfaceRequest} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table INTERFACE_REQUEST (ID LONG not null primary key,RequestCode VARCHAR(75) null,OrganizationCode VARCHAR(75) null,LocCode VARCHAR(75) null,RequestDate DATE null,RequestedDate DATE null,RequestDirection VARCHAR(75) null,RequestState INTEGER,RequestResponseTime DATE null,DocumentType VARCHAR(75) null,DocumentNameRef VARCHAR(75) null,BusinessType VARCHAR(75) null,FunctionType VARCHAR(75) null,SenderName VARCHAR(75) null,RequestVersion VARCHAR(75) null,SenderIdentify VARCHAR(75) null,ReceiverName VARCHAR(75) null,SendingDate DATE null,ReceiverIdentify VARCHAR(75) null,Remarks VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table INTERFACE_REQUEST";
	public static final String ORDER_BY_JPQL = " ORDER BY interfaceRequestField.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY INTERFACE_REQUEST.ID ASC";
	
}