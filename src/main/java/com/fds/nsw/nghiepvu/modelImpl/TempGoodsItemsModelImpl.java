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
public class TempGoodsItemsModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp goods items model instance should use the {@link com.fds.nsw.nghiepvu.model.TempGoodsItems} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_GOODS_ITEMS (ID LONG not null primary key,RequestCode VARCHAR(75) null,BillOfLadingNo VARCHAR(75) null,GoodItemCode VARCHAR(75) null,GoodItemDescription VARCHAR(75) null,NumberOfPackage LONG,KindOfPackages VARCHAR(75) null,MarksandNosofGoods VARCHAR(75) null,GrossWeight DOUBLE,GrossWeightUnit VARCHAR(75) null,Measurement DOUBLE,MeasurementUnit VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_GOODS_ITEMS";
	public static final String ORDER_BY_JPQL = " ORDER BY tempGoodsItems.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_GOODS_ITEMS.ID ASC";
	
}