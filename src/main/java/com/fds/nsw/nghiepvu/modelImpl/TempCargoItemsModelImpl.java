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
public class TempCargoItemsModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp cargo items model instance should use the {@link com.fds.nsw.nghiepvu.model.TempCargoItems} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_CARGO_ITEMS (ID LONG not null primary key,RequestCode VARCHAR(75) null,DocumentName LONG,DocumentYear INTEGER,CargoCategory VARCHAR(75) null,CargoType VARCHAR(75) null,CargoCode VARCHAR(75) null,Description VARCHAR(75) null,Quantity DOUBLE,Unit VARCHAR(75) null,Sequence INTEGER,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_CARGO_ITEMS";
	public static final String ORDER_BY_JPQL = " ORDER BY tempCargoItems.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_CARGO_ITEMS.ID ASC";
	
}