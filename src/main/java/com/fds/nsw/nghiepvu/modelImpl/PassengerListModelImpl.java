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
public class PassengerListModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a passenger list model instance should use the {@link vn.gt.dao.nhapcanh.model.PassengerList} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table passenger_list (id LONG not null primary key,passengercode VARCHAR(75) null,documentno VARCHAR(75) null,fullname VARCHAR(75) null,statecode VARCHAR(75) null,birthday DATE null,birthplace VARCHAR(75) null,passporttypecode VARCHAR(75) null,passportno VARCHAR(75) null,portcheckincode VARCHAR(75) null,portcheckoutcode VARCHAR(75) null,istransit BOOLEAN,createdate DATE null,modifydate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table passenger_list";
	public static final String ORDER_BY_JPQL = " ORDER BY passengerList.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY passenger_list.createdate ASC";
	
}