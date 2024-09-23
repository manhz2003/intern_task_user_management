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



import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.fds.nsw.kernel.dao.orm.BasePersistence;

/**
 * The persistence implementation for the role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RolePersistence
 * @see RoleUtil
 * @generated
 */
public class RolePersistenceImpl extends BasePersistence{
	
public static final String TABLE_SQL_CREATE = "create table Role_ (roleId LONG not null primary key,companyId LONG,classNameId LONG,classPK LONG,name VARCHAR(75) null,title STRING null,description STRING null,type_ INTEGER,subtype VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Role_";
	public static final String ORDER_BY_JPQL = " ORDER BY role.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY role.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
}