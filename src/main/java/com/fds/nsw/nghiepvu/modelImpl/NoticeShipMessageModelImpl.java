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
public class NoticeShipMessageModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a notice ship message model instance should use the {@link com.fds.nsw.nghiepvu.model.NoticeShipMessage} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table notice_ship_message (id LONG not null primary key,noticeshipcode VARCHAR(75) null,documentname LONG,usercreated VARCHAR(75) null,confirmtime INTEGER,callsign VARCHAR(75) null,arrivaldate DATE null,arrivalportcode VARCHAR(75) null,portgoingtocode VARCHAR(75) null,nameandaddofshipowners VARCHAR(75) null,clearanceheight LONG,showndraft LONG,dwt LONG,shipagencycode VARCHAR(75) null,purposecode INTEGER,crewnumber LONG,passengernumber LONG,quantityandtypeofcargo VARCHAR(75) null,otherpersons INTEGER,remarks VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table notice_ship_message";
	public static final String ORDER_BY_JPQL = " ORDER BY noticeShipMessage.id DESC";
	public static final String ORDER_BY_SQL = " ORDER BY notice_ship_message.id DESC";
	
}