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
public class VmaReportCategoryModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma report category model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaReportCategory} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_report_category (ID LONG not null primary key,RptCode VARCHAR(75) null,RptName VARCHAR(75) null,RptDescription VARCHAR(75) null,RptGroup VARCHAR(75) null,RptLevel VARCHAR(75) null,JasperFileTemplate VARCHAR(75) null,ExcelFileTemplate VARCHAR(75) null,SequenceNo INTEGER,MaritimeCodeSelect INTEGER,PortCodeSelect INTEGER,PortRegionCodeSelect INTEGER,PortHarbourCodeSelect INTEGER,PortWharfCodeSelect INTEGER,ChannelCodeSelect INTEGER,DepartmentCodeSelect INTEGER,ShipTypeCodeSelect INTEGER,FlagStateCodeSelect INTEGER,GrossTonnageSelect INTEGER,DeadWeightSelect INTEGER,CargoSelect INTEGER,CargoCategorySelect INTEGER,CargoUploadingSelect INTEGER,CargoGroupSelect INTEGER,ShipAgencyCodeSelect INTEGER,ShipOwnerCodeSelect INTEGER,PilotCompanyCodeSelect INTEGER,PilotCodeSelect INTEGER,TugboatCompanyCodeSelect INTEGER,TugboatCodeSelect INTEGER,ShipCodeSelect INTEGER,shipYardCodeSelect INTEGER,SecurityOfficeCodeSelect INTEGER,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table vma_report_category";
	public static final String ORDER_BY_JPQL = " ORDER BY vmaReportCategory.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY vma_report_category.ID ASC";
	
}