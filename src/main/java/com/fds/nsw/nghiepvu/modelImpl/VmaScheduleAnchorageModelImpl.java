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
public class VmaScheduleAnchorageModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma schedule anchorage model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_schedule_anchorage (id LONG not null primary key,ItineraryNo VARCHAR(75) null,SequenceNo INTEGER,PortofAuthority VARCHAR(75) null,NameOfShip VARCHAR(75) null,PurposeCode VARCHAR(75) null,PurposeSpecified VARCHAR(75) null,AnchoringDateFrom DATE null,AnchoringDateTo DATE null,AnchoringDuration DOUBLE,AnchorageFreeDuration DOUBLE,AnchorageDomesticDuration DOUBLE,AnchorageForeignDuration DOUBLE,AnchoringPortRegionCode VARCHAR(75) null,AnchoringPortHarbourCode VARCHAR(75) null,AnchoringPortWharfCode VARCHAR(75) null,NoticeShipType INTEGER,PortRegionCode VARCHAR(75) null,PortHarbourCode VARCHAR(75) null,PortWharfCode VARCHAR(75) null,ShipOwnerCode VARCHAR(75) null,ShipOperatorCode VARCHAR(75) null,ShipAgencyCode VARCHAR(75) null,SecurityLevelCode VARCHAR(75) null,AnchorageFreeDurationRemarks VARCHAR(75) null,MakePayment INTEGER,ModifiedDate DATE null,AnchorageSupplement LONG,ItineraryScheduleId LONG,DocumentaryCode VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table vma_schedule_anchorage";
	
}