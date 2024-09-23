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
public class ResultNotificationModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a result notification model instance should use the {@link com.fds.nsw.nghiepvu.model.ResultNotification} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table result_notification (ID LONG not null primary key,RequestCode VARCHAR(75) null,RequestState INTEGER,DocumentName LONG,DocumentYear INTEGER,MaritimeCode VARCHAR(75) null,Role LONG,Response VARCHAR(75) null,Organization VARCHAR(75) null,Division VARCHAR(75) null,OfficerName VARCHAR(75) null,LatestDate VARCHAR(75) null,BusinessTypeCode INTEGER,Remarks VARCHAR(75) null,IsReply INTEGER,ResponseTime DATE null)";
	public static final String TABLE_SQL_DROP = "drop table result_notification";
	public static final String ORDER_BY_JPQL = " ORDER BY resultNotification.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY result_notification.ID DESC";
	
}