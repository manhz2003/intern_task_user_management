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
public class VmaAuditLogModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma audit log model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaAuditLog} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_audit_log (id LONG not null primary key,userid LONG,modifyuser VARCHAR(75) null,actionname VARCHAR(75) null,actiontime DATE null,tablename VARCHAR(75) null,keycode VARCHAR(75) null,remarks VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table vma_audit_log";
	
}