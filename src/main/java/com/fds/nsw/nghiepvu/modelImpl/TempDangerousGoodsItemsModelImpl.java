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
public class TempDangerousGoodsItemsModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp dangerous goods items model instance should use the {@link com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_DANGEROUS_GOODS_ITEMS (ID LONG not null primary key,RequestCode VARCHAR(75) null,DangerousGoodItemCode VARCHAR(75) null,RefNumber VARCHAR(75) null,MarksContainer VARCHAR(75) null,NumberContainer VARCHAR(75) null,NumberOfPackage VARCHAR(75) null,KindOfPackages VARCHAR(75) null,ProperShippingName VARCHAR(75) null,GoodClass VARCHAR(75) null,UNNumber VARCHAR(75) null,PackingGroup VARCHAR(75) null,SubsidiaryRisk VARCHAR(75) null,FlashPoint VARCHAR(75) null,MarinePollutant VARCHAR(75) null,GrossWeight DOUBLE,Ems VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_DANGEROUS_GOODS_ITEMS";
	public static final String ORDER_BY_JPQL = " ORDER BY tempDangerousGoodsItems.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_DANGEROUS_GOODS_ITEMS.ID ASC";
	
}