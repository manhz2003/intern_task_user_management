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
public class TempUnitGeneralModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp unit general model instance should use the {@link com.fds.nsw.nghiepvu.model.TempUnitGeneral} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_UNIT_GENERAL (ID LONG not null primary key,RequestCode VARCHAR(75) null,DocumentName LONG,DocumentYear INTEGER,GrossTonnageUnit VARCHAR(75) null,NetTonnageUnit VARCHAR(75) null,UnitDWT VARCHAR(75) null,UnitLOA VARCHAR(75) null,UnitBreadth VARCHAR(75) null,UnitClearanceHeight VARCHAR(75) null,UnitShownDraft VARCHAR(75) null,UnitShownDraftxF VARCHAR(75) null,UnitShownDraftxA VARCHAR(75) null,UnitHorsePower VARCHAR(75) null,UnitRemainingOnBoardxB VARCHAR(75) null,UnitRemainingOnBoardxS VARCHAR(75) null,UnitFO VARCHAR(75) null,UnitLO VARCHAR(75) null,UnitFW VARCHAR(75) null,UnitDO VARCHAR(75) null,UnitSludgeOil VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_UNIT_GENERAL";
	public static final String ORDER_BY_JPQL = " ORDER BY tempUnitGeneral.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_UNIT_GENERAL.ID ASC";
	
}