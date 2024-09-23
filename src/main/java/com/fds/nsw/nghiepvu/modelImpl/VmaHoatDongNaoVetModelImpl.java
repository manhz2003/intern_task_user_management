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
public class VmaHoatDongNaoVetModelImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vma hoat dong nao vet model instance should use the {@link com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet} interface instead.
	 */
	public static final String TABLE_SQL_CREATE = "create table vma_hoatdong_naovet (ID LONG not null primary key,MaritimeCode VARCHAR(75) null,ma_hoat_dong_nao_vet VARCHAR(75) null,ten_cong_trinh VARCHAR(75) null,chu_dau_tu VARCHAR(75) null,nha_thau_thi_cong VARCHAR(75) null,tu_van_giam_sat VARCHAR(75) null,quyet_dinh_phe_duyet VARCHAR(75) null,vi_tri_do VARCHAR(75) null,ten_phuong_tien VARCHAR(75) null,so_dkhc VARCHAR(75) null,cap_phuong_tien VARCHAR(75) null,suc_cho VARCHAR(75) null,cong_dung VARCHAR(75) null,so_dang_kiem VARCHAR(75) null,ngay_cap_dang_kiem DATE null,ngay_het_han_dang_kiem DATE null,ngay_den_cang DATE null,so_giay_phep_tau_den VARCHAR(75) null,ngay_roi_cang DATE null,so_giay_phep_roi VARCHAR(75) null,ngay_cap_phep_hoat_dong_thi_cong DATE null,han_cap_phep_hoat_dong DATE null,tong_thoi_gian_thi_cong VARCHAR(75) null,so_luot_hoat_dong VARCHAR(75) null,khoi_luong_thi_cong VARCHAR(75) null,MarkedAsDelete INTEGER,ModifiedDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table vma_hoatdong_naovet";
	
}