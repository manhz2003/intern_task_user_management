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
public class DocumentGeneralModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a document general model instance should use the {@link vn.gt.dao.nhapcanh.model.DocumentGeneral} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table document_general (id LONG not null primary key,documentno VARCHAR(75) null,documentfunction VARCHAR(75) null,isarrival INTEGER,isdeparture INTEGER,shipcode VARCHAR(75) null,arrivalordepartureportcode VARCHAR(75) null,timearrivalordeparture DATE null,imonumber VARCHAR(75) null,callsign VARCHAR(75) null,voyagenumber INTEGER,shipnationcode VARCHAR(75) null,nameofmaster VARCHAR(75) null,lastport VARCHAR(75) null,contactdetailshipagent VARCHAR(75) null,grosstonnage DOUBLE,nettonnage DOUBLE,positionshipinport INTEGER,briefparticularsvoyage VARCHAR(75) null,briefdescriptioncargo VARCHAR(75) null,crewnumber INTEGER,passengernumber INTEGER,remarks VARCHAR(75) null,cargodeclaration VARCHAR(75) null,shipstoresdeclaration VARCHAR(75) null,crewlist VARCHAR(75) null,passengerlist VARCHAR(75) null,shiprequirements VARCHAR(75) null,iscreweffectdeclaration INTEGER,ismaritimedeclarationhealth INTEGER,createdate DATE null,modifydate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table document_general";
	public static final String ORDER_BY_JPQL = " ORDER BY documentGeneral.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY document_general.createdate ASC";
	
}