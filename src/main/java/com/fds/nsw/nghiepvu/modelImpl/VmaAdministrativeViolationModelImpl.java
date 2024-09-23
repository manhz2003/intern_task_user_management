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
public class VmaAdministrativeViolationModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma administrative violation model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_administrative_violation (id LONG not null primary key,PortofAuthority VARCHAR(75) null,ViolationCode VARCHAR(75) null,ViolationBrief VARCHAR(75) null,DamagedPartName VARCHAR(75) null,Conclusion VARCHAR(75) null,MetaData VARCHAR(75) null,PaymentAmount DOUBLE,AmountInWords VARCHAR(75) null,DocumentaryNo VARCHAR(75) null,DocumentaryDate DATE null,DecisionNo VARCHAR(75) null,DecisionDate DATE null,DecisionOrganization VARCHAR(75) null,OfficialNo VARCHAR(75) null,OfficialDate DATE null,OfficialPlace VARCHAR(75) null,ViolationPartCode VARCHAR(75) null,IssueDate DATE null,IssueBy VARCHAR(75) null,ViolationPartName VARCHAR(75) null,ViolationPartAddress VARCHAR(75) null,Representative VARCHAR(75) null,RepresentativeTitle VARCHAR(75) null,F1AttachedReport VARCHAR(75) null,F2AttachedReport VARCHAR(75) null,PaymentDate DATE null,NameOfShip VARCHAR(75) null,IMONumber VARCHAR(75) null,CallSign VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,RegistryNumber VARCHAR(75) null,ViolationDate DATE null,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table vma_administrative_violation";
	
}