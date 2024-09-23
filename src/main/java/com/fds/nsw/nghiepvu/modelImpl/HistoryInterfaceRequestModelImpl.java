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
public class HistoryInterfaceRequestModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a history interface request model instance should use the {@link com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table HISTORY_INTERFACE_REQUEST (ID LONG not null primary key,RequestCode VARCHAR(75) null,OrganizationCode VARCHAR(75) null,LocCode VARCHAR(75) null,RequestDate DATE null,RequestedDate DATE null,RequestDirection VARCHAR(75) null,RequestState INTEGER,RequestResponseTime DATE null,DocumentType VARCHAR(75) null,BusinessType VARCHAR(75) null,FunctionType VARCHAR(75) null,RequestContent VARCHAR(75) null,SenderName VARCHAR(75) null,RequestVersion VARCHAR(75) null,SenderIdentify VARCHAR(75) null,ReceiverName VARCHAR(75) null,SendingDate DATE null,ReceiverIdentify VARCHAR(75) null,Remarks VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table HISTORY_INTERFACE_REQUEST";
	public static final String ORDER_BY_JPQL = " ORDER BY historyInterfaceRequest.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY HISTORY_INTERFACE_REQUEST.ID ASC";
	
}