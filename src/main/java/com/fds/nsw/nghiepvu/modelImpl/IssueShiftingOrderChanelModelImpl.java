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
public class IssueShiftingOrderChanelModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a issue shifting order chanel model instance should use the {@link com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table ISSUE_SHIFTING_ORDER_CHANEL (ShiftingOrderId LONG not null,ChanelCode VARCHAR(75) not null,chanelName VARCHAR(75) null,Order_ INTEGER,primary key (ShiftingOrderId, ChanelCode))";
	public static final String TABLE_SQL_DROP = "drop table ISSUE_SHIFTING_ORDER_CHANEL";
	public static final String ORDER_BY_JPQL = " ORDER BY issueShiftingOrderChanel.order ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ISSUE_SHIFTING_ORDER_CHANEL.Order_ ASC";
	
}