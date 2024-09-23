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
public class ViewHoanThanhThuTucModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a view hoan thanh thu tuc model instance should use the {@link com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table viewHoanThanhThuTuc (CVHH VARCHAR(75) not null primary key,MaritimeOrder INTEGER,NC_KYSO INTEGER,XC_KYSO INTEGER,QC_KYSO INTEGER,VC_KYSO INTEGER,RC_KYSO INTEGER,CCV_KYSO INTEGER,CCR_KYSO INTEGER,VCDK_KYSO INTEGER,RCDK_KYSO INTEGER,NCDK_KYSO INTEGER,XCDK_KYSO INTEGER,VCTND_KYSO INTEGER,RCTND_KYSO INTEGER,NC_DUYET INTEGER,XC_DUYET INTEGER,QC_DUYET INTEGER,VC_DUYET INTEGER,RC_DUYET INTEGER,CCV_DUYET INTEGER,CCR_DUYET INTEGER,VCDK_DUYET INTEGER,RCDK_DUYET INTEGER,NCDK_DUYET INTEGER,XCDK_DUYET INTEGER,VCTND_DUYET INTEGER,RCTND_DUYET INTEGER,NCPTTND_DUYET INTEGER,XCPTTND_DUYET INTEGER,NCPTTND_KYSO INTEGER,XCPTTND_KYSO INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table viewHoanThanhThuTuc";
	public static final String ORDER_BY_JPQL = " ORDER BY viewHoanThanhThuTuc.MaritimeOrder ASC";
	public static final String ORDER_BY_SQL = " ORDER BY viewHoanThanhThuTuc.MaritimeOrder ASC";
	
}