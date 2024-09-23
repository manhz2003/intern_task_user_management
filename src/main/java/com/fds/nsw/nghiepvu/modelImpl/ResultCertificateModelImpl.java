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
public class ResultCertificateModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a result certificate model instance should use the {@link com.fds.nsw.nghiepvu.model.ResultCertificate} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table result_certificate (ID LONG not null primary key,DocumentName LONG,DocumentYear INTEGER,MaritimeCode VARCHAR(75) null,CertificateCode VARCHAR(75) null,CertificateOrder INTEGER,CertificateNo VARCHAR(75) null,CertificateIssueDate DATE null,CertificateExpiredDate DATE null,ExaminationDate DATE null,Description VARCHAR(75) null,Comment VARCHAR(75) null,ApprovalName VARCHAR(75) null,IsExamined INTEGER,mandatory INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table result_certificate";
	public static final String ORDER_BY_JPQL = " ORDER BY resultCertificate.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY result_certificate.ID DESC";
	
}