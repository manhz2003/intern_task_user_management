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
public class DmTestN01RequestModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a dm test n01 request model instance should use the {@link com.fds.nsw.nghiepvu.model.DmTestN01Request} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table dm_test_n01_request (requestid INTEGER not null primary key,enterprisecode VARCHAR(75) null,ten_du_an VARCHAR(75) null,su_can_thiet_dau_tu VARCHAR(75) null,vi_tri VARCHAR(75) null,loai_tau_bien_gioi_han_vao_cang VARCHAR(75) null,so_luong_cau_cang VARCHAR(75) null,tong_dien_tich_xay_dung_du_kien VARCHAR(75) null,tai_lieu_lien_quan VARCHAR(75) null,modifieddate DATE null,modifiedUser VARCHAR(75) null,isAuthorized INTEGER,authorizeddate DATE null,authorizeduser VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table dm_test_n01_request";
	public static final String ORDER_BY_JPQL = " ORDER BY dmTestN01Request.requestID ASC";
	public static final String ORDER_BY_SQL = " ORDER BY dm_test_n01_request.requestid ASC";
	
}