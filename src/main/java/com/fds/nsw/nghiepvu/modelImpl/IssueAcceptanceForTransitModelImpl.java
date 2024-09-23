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
public class IssueAcceptanceForTransitModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a issue acceptance for transit model instance should use the {@link com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table ISSUE_ACCEPTANCE_FOR_TRANSIT (ID LONG not null primary key,RequestCode VARCHAR(75) null,NumberAcceptanceForTransit VARCHAR(75) null,DocumentName LONG,DocumentYear INTEGER,NameOfShip VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,CallSign VARCHAR(75) null,NameOfMaster VARCHAR(75) null,LOA DOUBLE,UnitLOA VARCHAR(75) null,Breadth DOUBLE,UnitBreadth VARCHAR(75) null,GrossTonnage DOUBLE,GrossTonnageUnit VARCHAR(75) null,NetTonnage DOUBLE,NetTonnageUnit VARCHAR(75) null,DWT DOUBLE,UnitDWT VARCHAR(75) null,ShownDraftxF DOUBLE,UnitShownDraftxF VARCHAR(75) null,ShownDraftxA DOUBLE,UnitShownDraftxA VARCHAR(75) null,ClearanceHeight DOUBLE,UnitClearanceHeight VARCHAR(75) null,PermittedTransitFrom DATE null,PermittedTransitTo DATE null,TimeOfDeparture DATE null)";
	public static final String TABLE_SQL_DROP = "drop table ISSUE_ACCEPTANCE_FOR_TRANSIT";
	public static final String ORDER_BY_JPQL = " ORDER BY issueAcceptanceForTransit.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ISSUE_ACCEPTANCE_FOR_TRANSIT.ID ASC";
	
}