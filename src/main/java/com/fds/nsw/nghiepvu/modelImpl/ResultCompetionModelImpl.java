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
public class ResultCompetionModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a result competion model instance should use the {@link com.fds.nsw.nghiepvu.model.ResultCompletion} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table RESULT_COMPLETION (id LONG not null primary key,RequestCode VARCHAR(75) null,RequestState INTEGER,DocumentName LONG,DocumentYear INTEGER,NameOfShip VARCHAR(75) null,FlagStateOfShip VARCHAR(75) null,NameOfMaster VARCHAR(75) null,GrossTonnage DOUBLE,PortOfArrivalCode VARCHAR(75) null,MaritimeCode VARCHAR(75) null,Division VARCHAR(75) null,ApprovalName VARCHAR(75) null,ApprovalTime DATE null,CertificateNo VARCHAR(75) null,ResponseStatusHQ INTEGER,ResponseStatusBP INTEGER,ResponseStatusYT INTEGER,ResponseStatusDV INTEGER,ResponseStatusTV INTEGER,ResponseStatusCVHH INTEGER,ResponseTimeHQ DATE null,ResponseTimeBP DATE null,ResponseTimeYT DATE null,ResponseTimeDV DATE null,ResponseTimeTV DATE null,ResponseTimeCVHH DATE null,ResponseHQ VARCHAR(75) null,ResponseBP VARCHAR(75) null,ResponseYT VARCHAR(75) null,ResponseDV VARCHAR(75) null,ResponseTV VARCHAR(75) null,ResponseCVHH VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table RESULT_COMPLETION";
	public static final String ORDER_BY_JPQL = " ORDER BY resultCompetion.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY RESULT_COMPLETION.id DESC";
	
}