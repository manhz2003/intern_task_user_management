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
public class CrewListModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a crew list model instance should use the {@link vn.gt.dao.nhapcanh.model.CrewList} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table crew_list (id LONG not null primary key,crewcode VARCHAR(75) null,documentno VARCHAR(75) null,statecode VARCHAR(75) null,birthday DATE null,birthplace VARCHAR(75) null,familyname VARCHAR(75) null,fullname VARCHAR(75) null,givenname VARCHAR(75) null,passportnumber VARCHAR(75) null,passporttype VARCHAR(75) null,rankcode VARCHAR(75) null,createdate DATE null,modifydate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table crew_list";
	public static final String ORDER_BY_JPQL = " ORDER BY crewList.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY crew_list.createdate ASC";
	
}