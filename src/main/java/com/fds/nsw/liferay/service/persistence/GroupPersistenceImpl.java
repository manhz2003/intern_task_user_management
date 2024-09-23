/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.fds.nsw.liferay.service.persistence;

import com.fds.nsw.kernel.dao.orm.BasePersistence;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
public class GroupPersistenceImpl extends BasePersistence {
	public static final String TABLE_SQL_CREATE = "create table Group_ (groupId LONG not null primary key,companyId LONG,creatorUserId LONG,classNameId LONG,classPK LONG,parentGroupId LONG,liveGroupId LONG,name VARCHAR(150) null,description STRING null,type_ INTEGER,typeSettings STRING null,friendlyURL VARCHAR(100) null,site BOOLEAN,active_ BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table Group_";
	public static final String ORDER_BY_JPQL = " ORDER BY group_.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Group_.name ASC";

}