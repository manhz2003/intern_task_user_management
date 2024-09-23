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
public class VmaShipCertificateModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma ship certificate model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaShipCertificate} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_ship_certificate (id LONG not null primary key,mtgateway INTEGER,MaritimeCode VARCHAR(75) null,IMONumber VARCHAR(75) null,CallSign VARCHAR(75) null,VRCode VARCHAR(75) null,RegistryNumber VARCHAR(75) null,CertificateCode VARCHAR(75) null,CertificateName VARCHAR(75) null,CertificateOrder INTEGER,CertificateNo VARCHAR(75) null,Description VARCHAR(75) null,CertificateIssueDate DATE null,CertificateExpiredDate DATE null,ExaminationDate DATE null,Comment VARCHAR(75) null,IsExamined INTEGER,mandatory INTEGER,shipId LONG)";
	public static final String TABLE_SQL_DROP = "drop table vma_ship_certificate";
	
}