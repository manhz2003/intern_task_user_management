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
public class TempShipStoresItemsModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp ship stores items model instance should use the {@link com.fds.nsw.nghiepvu.model.TempShipStoresItems} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_SHIP_STORES_ITEMS (ID LONG not null primary key,RequestCode VARCHAR(75) null,ShipsStoreItemCode VARCHAR(75) null,NameOfArticle VARCHAR(75) null,Quantity VARCHAR(75) null,QuantityUnit VARCHAR(75) null,LocationOnBoat VARCHAR(75) null,UseInBoat INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_SHIP_STORES_ITEMS";
	public static final String ORDER_BY_JPQL = " ORDER BY tempShipStoresItems.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_SHIP_STORES_ITEMS.ID ASC";
	
}