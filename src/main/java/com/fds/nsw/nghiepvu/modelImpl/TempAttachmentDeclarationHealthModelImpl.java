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
public class TempAttachmentDeclarationHealthModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a temp attachment declaration health model instance should use the {@link com.fds.nsw.nghiepvu.model.TempAttachmentDeclarationHealth} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table TEMP_ATT_DECLARATION_HEALTH (ID LONG not null primary key,RequestCode VARCHAR(75) null,AttachmentCode VARCHAR(75) null,PassengerOrCrewCode VARCHAR(75) null,Name VARCHAR(75) null,Age INTEGER,Sex INTEGER,Nationality VARCHAR(75) null,PortJoinVesselCode VARCHAR(75) null,DateJoinVessel DATE null,ClassOrRating INTEGER,NatureOfIllness VARCHAR(75) null,DateOfOnsetOfSymptom DATE null,ReportedMedicalOfficer INTEGER,DisposalOfCase VARCHAR(75) null,MedicinesOrOther VARCHAR(75) null,Comments VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_ATT_DECLARATION_HEALTH";
	public static final String ORDER_BY_JPQL = " ORDER BY tempAttachmentDeclarationHealth.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_ATT_DECLARATION_HEALTH.ID ASC";
	
}