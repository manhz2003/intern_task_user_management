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
public class LogMessageValidationModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a log message validation model instance should use the {@link com.fds.nsw.nghiepvu.model.LogMessageValidation} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table LOG_MESSAGE_VALIDATION (id LONG not null primary key,RequestCode VARCHAR(75) null,RequestDirection VARCHAR(75) null,RequestDate DATE null,DocumentName LONG,DocumentYear INTEGER,DocumentType VARCHAR(75) null,TagProperty VARCHAR(75) null,DataValidation VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table LOG_MESSAGE_VALIDATION";
	public static final String ORDER_BY_JPQL = " ORDER BY logMessageValidation.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY LOG_MESSAGE_VALIDATION.id ASC";
	
}