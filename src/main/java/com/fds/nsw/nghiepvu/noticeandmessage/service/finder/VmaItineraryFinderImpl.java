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

package com.fds.nsw.nghiepvu.noticeandmessage.service.finder;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.VmaConversionType;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.baocao.bc12bt.BC12BTLuotHHModel;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.util.ConvertUtil;
import com.fds.nsw.nghiepvu.util.FormatData;

import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.tichhop.report.ReportsBusinessUtils;

@Service
@Slf4j
public class VmaItineraryFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaItinerary> queryFactory;

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;

//	  public JSONArray findVmaItinerary(String portofAuthority, String
//	  shiftingDate, int start, int end) throws SystemException {
//
//
//
//	  JSONArray data = JSONFactoryUtil.createJSONArray();
//
//	  try {
//
//	  StringBuilder query = new StringBuilder();
//
//	  query.append("SELECT "); query.append("A1.NameOfShip,");
//	  query.append("A1.FlagStateOfShip,"); query.append("A1.IMONumber,");
//	  query.append("A1.CallSign,"); query.append("A1.VRCode,");
//	  query.append("A1.RegistryNumber,"); query.append("A2.GT,");
//	  query.append("A2.NT,"); query.append("A2.DWT,"); query.append("A2.LOA,");
//	  query.append("A2.ShownDraftxF,"); query.append("A2.ShownDraftxA,");
//	  query.append("A2.UnitGRT,"); query.append("A2.UnitNT,");
//	  query.append("A2.UnitDWT,"); query.append("A2.LOAUNIT,");
//	  query.append("A2.UnitShownDraftxF,");
//	  query.append("A2.UnitShownDraftxA,"); query.append("A2.From_,");
//	  query.append("A2.To_,"); query.append("A2.ShiftingDate,");
//	  query.append("A2.NameOfShipownersAgents,");
//	  query.append("A2.TaxCodeOfShipownersAgents,");
//	  query.append("A2.ChanelName,"); query.append("A2.ChanelCodeList,");
//	  query.append("A2.ID,"); query.append("A2.ItineraryNo");
//
//	  query.append(
//	  " FROM vma_itinerary AS A1 INNER JOIN vma_schedule_shifting AS A2 ON A1.ItineraryNo = A2.ItineraryNo"
//	  );
//
//	  query.append(" WHERE 1=1 ");
//
//	  query.append(generateCondition2(portofAuthority, shiftingDate));
//
//	  query.append(" ORDER BY A2.TimeOfArrival ASC");
//
//	  log.info(
//	  "=========select vma_schedule_shifting>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
//	  + query.toString());
//
//	  QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
//
//
//
//	  Iterator<Object[]> itr = (Iterator<Object[]>)  queryFactory.getResultList(builder);
//		} catch (Exception e) {
//			throw new SystemException(e);
//		} finally {
//
//		}
//	}

	public VmaItinerary findVmaItineraryLeftByIMOandCallSign(
			String maritimeCode, Date TimeOfDeparture, String callSign,
			String imo, String ShipPosition) throws SystemException {

		List<VmaItinerary> allVmaItinerary = null;
		try {

			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM vma_itinerary ");
			query.append(" WHERE 1 = 1 ");

			if (maritimeCode != null) {
				query.append(" and MaritimeCode = " + maritimeCode);
			}

			if (Validator.isNotNull(TimeOfDeparture)) {
				String dateTo = FormatData.parseDateToTring(TimeOfDeparture);
				query.append(" and TimeOfDeparture <= concat(date('" + dateTo
						+ "'), ' 23:59:59') ");
			}

			// Ho hieu
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
			}

			// IMO
			if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
				query.append(" AND IMONumber like '%" + imo.trim() + "%' ");
			}

			if (ShipPosition != null) {
				query.append(" and ShipPosition in (" + ShipPosition + ")");
			}

			String sql = query.toString();
			sql = sql + " order by TimeOfDeparture desc limit 1 ";

			// execute the query and return a list from the db
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(VmaItinerary.class).build();

			allVmaItinerary = (List<VmaItinerary>) queryFactory.getResultList(builder);

			if (allVmaItinerary != null && allVmaItinerary.size() > 0) {
				return allVmaItinerary.get(0);
			}
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
		return null;
	}

	public VmaItinerary findVmaItineraryByVoyageIMOandCallSign(
			String maritimeCode, String VoyageNumber, String callSign,
			String imo, String ShipPosition) throws SystemException {

		List<VmaItinerary> allVmaItinerary = null;
		try {
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM vma_itinerary ");
			query.append(" WHERE 1 = 1 ");

			if (maritimeCode != null) {
				query.append(" and MaritimeCode = " + maritimeCode);
			}

			if (Validator.isNotNull(VoyageNumber)
					&& VoyageNumber.trim().length() > 0) {
				query.append(" AND VoyageNumber like '%" + VoyageNumber.trim()
						+ "%' ");
			}

			// Ho hieu
			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				query.append(" AND CallSign like '%" + callSign.trim() + "%' ");
			}

			// IMO
			if (Validator.isNotNull(imo) && imo.trim().length() > 0) {
				query.append(" AND IMONumber like '%" + imo.trim() + "%' ");
			}

			if (ShipPosition != null) {
				query.append(" and ShipPosition in (" + ShipPosition + ")");
			}

			String sql = query.toString();
			sql = sql + " order by TimeOfDeparture desc limit 1 ";
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(VmaItinerary.class).build();
			// execute the query and return a list from the db

			allVmaItinerary = (List<VmaItinerary>) queryFactory.getResultList(builder);

			if (allVmaItinerary != null && allVmaItinerary.size() > 0) {
				return allVmaItinerary.get(0);
			}
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {

		}
		return null;
	}

	public String getItineraryNoWithRule(String MaritimeCode)
			throws SystemException {
		String sItineraryNo = StringPool.BLANK;

		try {

			DmMaritime maritime = DmMaritimeLocalServiceUtil
					.getByMaritimeCode(MaritimeCode);
			if (Validator.isNull(maritime)) {
				maritime = new DmMaritime();
			}
			Date date = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("MMM");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yy");

			String keyData = maritime.getMaritimeReference() + "."
					+ sdf.format(date).toUpperCase() + sdf2.format(date); // (JAN19,
			// FEB19)
			sItineraryNo = ReportsBusinessUtils.taoSo("VmaItinerary", keyData,
					5);
		} catch (Exception e) {
			throw new SystemException(e);
		}
		return sItineraryNo;
	}

	public List<VmaItinerary> findVmaItinerary(Class<?> clazz,
											   String className, String sql, int start, int end)
			throws SystemException {

		try {

			log.info("=========select vms_itinerary>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);


			/* QueryPos qPos = QueryPos.getInstance(q); */

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaItinerary.class).build();


			return (List<VmaItinerary>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}


	public long countVmaItinerary(String sql) throws SystemException { long count = 0; try {


			log.info("=========count vms_itinerary>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public JSONObject getModelMau12T(String maritimeCode, String startDate,
			String endDate, String objName) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			

			String query = "SELECT temp1.*, temp2.* FROM ( SELECT MaritimeCode, sum( CASE vma_schedule_cargolist.CargoCategory WHEN 'C2_DO' THEN Quantity ELSE 0 END ) AS tong_nhap, sum( CASE vma_schedule_cargolist.CargoCategory WHEN 'C1_XEP' THEN Quantity ELSE 0 END ) AS tong_xuat, sum( CASE vma_schedule_cargolist.CargoCategory WHEN 'C3_XEP' THEN Quantity WHEN 'C3_DO' THEN Quantity ELSE 0 END ) AS tong_noi_dia, sum( CASE vma_schedule_cargolist.CargoCategory WHEN 'C4' THEN Quantity WHEN 'C4_XEP' THEN Quantity WHEN 'C4_DO' THEN Quantity ELSE 0 END ) AS qua_canh_xep_do, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C1_XEP' AND vma_schedule_cargolist.CargoType = 9 THEN Quantity ELSE 0 END ) AS hang_container_xuat, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C1_XEP' AND vma_schedule_cargolist.CargoType = 9 THEN TeusQuantity ELSE 0 END ) AS hang_container_xuat_teus, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C2_DO' AND vma_schedule_cargolist.CargoType = 9 THEN Quantity ELSE 0 END ) AS hang_container_nhap, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C2_DO' AND vma_schedule_cargolist.CargoType = 9 THEN TeusQuantity ELSE 0 END ) AS hang_container_nhap_teus, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C3_DO' AND vma_schedule_cargolist.CargoType = 9 THEN Quantity WHEN vma_schedule_cargolist.CargoCategory = 'C3_XEP' AND vma_schedule_cargolist.CargoType = 9 THEN Quantity ELSE 0 END ) AS hang_container_noi_dia, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C3_DO' AND vma_schedule_cargolist.CargoType = 9 THEN TeusQuantity WHEN vma_schedule_cargolist.CargoCategory = 'C3_XEP' AND vma_schedule_cargolist.CargoType = 9 THEN TeusQuantity ELSE 0 END ) AS hang_container_noi_dia_teus, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C1_XEP' AND vma_schedule_cargolist.CargoType = 3 THEN Quantity ELSE 0 END ) AS hang_long_xuat, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C2_DO' AND vma_schedule_cargolist.CargoType = 3 THEN Quantity ELSE 0 END ) AS hang_long_nhap, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C3_DO' AND vma_schedule_cargolist.CargoType = 3 THEN Quantity WHEN vma_schedule_cargolist.CargoCategory = 'C3_XEP' AND vma_schedule_cargolist.CargoType = 3 THEN Quantity ELSE 0 END ) AS hang_long_noi_dia, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C1_XEP' AND vma_schedule_cargolist.CargoType = 12 THEN Quantity ELSE 0 END ) AS hang_kho_xuat, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C2_DO' AND vma_schedule_cargolist.CargoType = 12 THEN Quantity ELSE 0 END ) AS hang_kho_nhap, sum( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C3_DO' AND vma_schedule_cargolist.CargoType = 12 THEN Quantity WHEN vma_schedule_cargolist.CargoCategory = 'C3_XEP' AND vma_schedule_cargolist.CargoType = 12 THEN Quantity ELSE 0 END ) AS hang_kho_noi_dia, sum( CASE WHEN vma_itinerary.VRCode IN ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') THEN Quantity ELSE 0 END ) AS hh_tau_bien, sum( CASE WHEN vma_itinerary.VRCode IN ('VR-SB','VR-SI','VR-SII','---') THEN Quantity ELSE 0 END ) AS hh_pttnd, sum( CASE WHEN vma_itinerary.VRCode = 'VR-SB' THEN Quantity ELSE 0 END ) AS hh_pttnd_vr_sb, sum( CASE WHEN vma_itinerary.VRCode IN ('VR-SB','VR-SI','VR-SII','---') AND LastPortCode IN ( SELECT PortCode FROM dm_port WHERE PortType = 0 ) THEN Quantity WHEN vma_itinerary.VRCode IN ('VR-SB','VR-SI','VR-SII','---') AND NextPortCode IN ( SELECT PortCode FROM dm_port WHERE PortType = 0 ) THEN Quantity ELSE 0 END ) AS hh_pttnd_vr_sb_cb_cb, sum( CASE WHEN vma_itinerary.VRCode IN ('VR-SB','VR-SI','VR-SII','---') AND LastPortCode IN ( SELECT PortCode FROM dm_port WHERE PortType = 1 ) THEN Quantity WHEN vma_itinerary.VRCode IN ('VR-SB','VR-SI','VR-SII','---') AND NextPortCode IN ( SELECT PortCode FROM dm_port WHERE PortType = 1 ) THEN Quantity ELSE 0 END ) AS hh_pttnd_vr_sb_cb_nd, sum( CASE WHEN vma_itinerary.LastPortCode IN ( SELECT PortCode FROM dm_port WHERE PortType = 2 ) THEN Quantity WHEN vma_itinerary.NextPortCode IN ( SELECT PortCode FROM dm_port WHERE PortType = 2 ) THEN Quantity ELSE 0 END ) AS hh_tu_bo_ra_dao, sum( CASE vma_schedule_cargolist.CargoCategory WHEN 'C5' THEN Quantity ELSE 0 END ) AS qua_canh_khong_xep_do FROM vma_itinerary LEFT JOIN vma_schedule_cargolist ON vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo WHERE ( ( vma_schedule_cargolist.CargoCategory IN ( 'C2_DO', 'C3_DO', 'C4', 'C5' ) AND vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND vma_itinerary.LastPortCode IS NOT NULL ) OR ( vma_schedule_cargolist.CargoCategory IN ('C1_XEP', 'C3_XEP', 'C4', 'C5') AND vma_itinerary.MarkedAsDeparture NOT IN (0, 1, 6) AND vma_itinerary.NextPortCode IS NOT NULL ) ) AND MaritimeCode = '"
					+ maritimeCode
					+ "' AND ( ( vma_itinerary.MarkedAsDeparture NOT IN (0, 1, 6) AND vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ " 23:59:59' AND vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND ( (vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59') OR (vma_itinerary.TimeOfArrival < '"
					+ startDate
					+ " 00:00:00') )) OR ( vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59' AND (vma_itinerary.MarkedAsDeparture IN (0, 1, 6) OR vma_itinerary.TimeOfDeparture > '"
					+ endDate
					+ " 23:59:59') ) ) ) AS temp1 INNER JOIN ( SELECT MaritimeCode AS MaritimeCode_, sum( CASE WHEN vma_itinerary.VRCode IN ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') AND vma_itinerary.FlagStateOfShip = 'VN' THEN PassengerNumber ELSE 0 END ) AS khach_tau_bien_vn, sum( CASE WHEN vma_itinerary.VRCode IN ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') AND vma_itinerary.FlagStateOfShip <> 'VN' THEN PassengerNumber ELSE 0 END ) AS khach_tau_bien_qt, sum( CASE WHEN vma_itinerary.VRCode IN ('VR-SB','VR-SI','VR-SII','---') THEN PassengerNumber ELSE 0 END ) AS khach_pttnd, sum( CASE WHEN vma_itinerary.LastPortCode IN ( SELECT PortCode FROM dm_port WHERE PortType = 2 ) THEN PassengerNumber WHEN vma_itinerary.NextPortCode IN ( SELECT PortCode FROM dm_port WHERE PortType = 2 ) THEN PassengerNumber ELSE 0 END ) AS khach_tu_bo_ra_dao FROM vma_itinerary_schedule INNER JOIN vma_itinerary ON vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo WHERE ( vma_itinerary_schedule.NoticeShipType = '2' AND vma_itinerary.MarkedAsDeparture NOT IN (0, 1, 6) AND vma_itinerary.NextPortCode IS NOT NULL ) OR ( vma_itinerary_schedule.NoticeShipType = '1' AND vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND vma_itinerary.LastPortCode IS NOT NULL ) AND MaritimeCode = '"
					+ maritimeCode
					+ "' AND ( ( vma_itinerary.MarkedAsDeparture NOT IN (0, 1, 6) AND vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ " 23:59:59' AND vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND ( (vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59') OR (vma_itinerary.TimeOfArrival < '"
					+ startDate
					+ " 00:00:00') )) OR ( vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59' AND (vma_itinerary.MarkedAsDeparture IN (0, 1, 6) OR vma_itinerary.TimeOfDeparture > '"
					+ endDate
					+ " 23:59:59') ) ) ) AS temp2 ON temp1.MaritimeCode = temp2.MaritimeCode_";

			log.info("=========select bao cao mau_17_12t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();



			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					double tong_nhap = Double.valueOf(String
							.valueOf(objects[1] != null ? objects[1] : 0));
					double tong_xuat = Double.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : 0));
					double tong_noi_dia = Double.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : 0));
					double qua_canh_xep_do = Double.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : 0));
					double hang_container_xuat = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : 0));
					double hang_container_xuat_teus = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : 0));
					double hang_container_nhap = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : 0));
					double hang_container_nhap_teus = Double.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : 0));
					double hang_container_noidia = Double.valueOf(String
							.valueOf(objects[9] != null ? objects[9] : 0));
					double hang_container_noidia_teus = Double.valueOf(String
							.valueOf(objects[10] != null ? objects[10] : 0));
					double hang_long_xuat = Double.valueOf(String
							.valueOf(objects[11] != null ? objects[11] : 0));
					double hang_long_nhap = Double.valueOf(String
							.valueOf(objects[12] != null ? objects[12] : 0));
					double hang_long_noi_dia = Double.valueOf(String
							.valueOf(objects[13] != null ? objects[13] : 0));
					double hang_kho_xuat = Double.valueOf(String
							.valueOf(objects[14] != null ? objects[14] : 0));
					double hang_kho_nhap = Double.valueOf(String
							.valueOf(objects[15] != null ? objects[15] : 0));
					double hang_kho_noi_dia = Double.valueOf(String
							.valueOf(objects[16] != null ? objects[16] : 0));
					double hh_tau_bien = Double.valueOf(String
							.valueOf(objects[17] != null ? objects[17] : 0));
					double hh_pttnd = Double.valueOf(String
							.valueOf(objects[18] != null ? objects[18] : 0));
					double hh_pttnd_vr_sb = Double.valueOf(String
							.valueOf(objects[19] != null ? objects[19] : 0));
					double hh_pttnd_vr_sb_cb_cb = Double.valueOf(String
							.valueOf(objects[20] != null ? objects[20] : 0));
					double hh_pttnd_vr_sb_cb_nd = Double.valueOf(String
							.valueOf(objects[21] != null ? objects[21] : 0));
					double hh_tu_bo_ra_dao = Double.valueOf(String
							.valueOf(objects[22] != null ? objects[22] : 0));
					double qua_canh_khong_xep_do = Double.valueOf(String
							.valueOf(objects[24] != null ? objects[23] : 0));
					int khach_tau_bien_vn = Integer.valueOf(String
							.valueOf(objects[24] != null ? objects[24] : 0));
					int khach_tau_bien_qt = Integer.valueOf(String
							.valueOf(objects[25] != null ? objects[25] : 0));
					int khach_pttnd = Integer.valueOf(String
							.valueOf(objects[26] != null ? objects[26] : 0));
					int khach_tu_bo_ra_dao = Integer.valueOf(String
							.valueOf(objects[27] != null ? objects[27] : 0));

					NumberFormat formatter = new DecimalFormat("#0.00");

					Date date = new Date();
					try {
						String ngay = String.valueOf(objects[28]);
						date = FormatData.formatDateShort2.parse(ngay);
					} catch (Exception e) {

					}

					double tong1 = BaoCaoBussinessUtils.sum1(tong_nhap,
							tong_noi_dia, tong_xuat, qua_canh_xep_do);
					if (tong1 >= 0) {
						result.put(objName + "1", formatter.format(tong1));
					}
					if (tong_xuat >= 0) {
						result.put(objName + "2", formatter.format(tong_xuat));
					}
					if (tong_nhap >= 0) {
						result.put(objName + "3", formatter.format(tong_nhap));
					}
					if (tong_noi_dia >= 0) {
						result.put(objName + "4",
								formatter.format(tong_noi_dia));
					}
					if (qua_canh_xep_do >= 0) {
						result.put(objName + "5",
								formatter.format(qua_canh_xep_do));
					}
					double tong2 = BaoCaoBussinessUtils.sum1(
							hang_container_nhap, hang_container_noidia,
							hang_container_xuat);
					if (tong2 >= 0) {
						result.put(objName + "6", formatter.format(tong2));
					}
					double tong3 = BaoCaoBussinessUtils.sum1(
							hang_container_nhap_teus,
							hang_container_noidia_teus,
							hang_container_xuat_teus);
					if (tong3 >= 0) {
						result.put(objName + "7", formatter.format(tong3));
					}
					if (hang_container_xuat >= 0) {
						result.put(objName + "8",
								formatter.format(hang_container_xuat));
					}
					if (hang_container_xuat_teus >= 0) {
						result.put(objName + "9",
								formatter.format(hang_container_xuat_teus));
					}
					if (hang_container_nhap >= 0) {
						result.put(objName + "10",
								formatter.format(hang_container_nhap));
					}
					if (hang_container_nhap_teus >= 0) {
						result.put(objName + "11",
								formatter.format(hang_container_nhap_teus));
					}
					if (hang_container_noidia >= 0) {
						result.put(objName + "12",
								formatter.format(hang_container_noidia));
					}
					if (hang_container_noidia_teus >= 0) {
						result.put(objName + "13",
								formatter.format(hang_container_noidia_teus));
					}
					double tong4 = BaoCaoBussinessUtils.sum1(hang_long_nhap,
							hang_long_noi_dia, hang_long_xuat);
					if (tong4 >= 0) {
						result.put(objName + "14", formatter.format(tong4));
					}
					if (hang_long_xuat >= 0) {
						result.put(objName + "15",
								formatter.format(hang_long_xuat));
					}
					if (hang_long_nhap >= 0) {
						result.put(objName + "16",
								formatter.format(hang_long_nhap));
					}
					if (hang_long_noi_dia >= 0) {
						result.put(objName + "17",
								formatter.format(hang_long_noi_dia));
					}
					double tong5 = BaoCaoBussinessUtils.sum1(hang_kho_nhap,
							hang_kho_noi_dia, hang_kho_xuat);
					if (tong5 >= 0) {
						result.put(objName + "18", formatter.format(tong5));
					}
					if (hang_kho_xuat >= 0) {
						result.put(objName + "19",
								formatter.format(hang_kho_xuat));
					}
					if (hang_kho_nhap >= 0) {
						result.put(objName + "20",
								formatter.format(hang_kho_nhap));
					}
					if (hang_kho_noi_dia >= 0) {
						result.put(objName + "21",
								formatter.format(hang_kho_noi_dia));
					}
					if (hh_tau_bien >= 0) {
						result.put(objName + "22",
								formatter.format(hh_tau_bien));
					}
					if (hh_pttnd >= 0) {
						result.put(objName + "23", formatter.format(hh_pttnd));
					}
					if (hh_pttnd_vr_sb >= 0) {
						result.put(objName + "24",
								formatter.format(hh_pttnd_vr_sb));
					}
					if (hh_pttnd_vr_sb_cb_cb >= 0) {
						result.put(objName + "25",
								formatter.format(hh_pttnd_vr_sb_cb_cb));
					}
					if (hh_pttnd_vr_sb_cb_nd >= 0) {
						result.put(objName + "26",
								formatter.format(hh_pttnd_vr_sb_cb_nd));
					}
					if (qua_canh_khong_xep_do >= 0) {
						result.put(objName + "27",
								formatter.format(qua_canh_khong_xep_do));
					}
					if (hh_tu_bo_ra_dao >= 0) {
						result.put(objName + "28",
								formatter.format(hh_tu_bo_ra_dao));
					}
					int tong6 = BaoCaoBussinessUtils.sum2(khach_pttnd,
							khach_tau_bien_qt, khach_tau_bien_vn,
							khach_tu_bo_ra_dao);
					if (tong6 >= 0) {
						result.put(objName + "29", tong6);
					}
					if (khach_tau_bien_vn >= 0) {
						result.put(objName + "30", khach_tau_bien_vn);
					}
					if (khach_tau_bien_qt >= 0) {
						result.put(objName + "31", khach_tau_bien_qt);
					}
					if (khach_pttnd >= 0) {
						result.put(objName + "32", khach_pttnd);
					}
					if (khach_tu_bo_ra_dao >= 0) {
						result.put(objName + "33", khach_tu_bo_ra_dao);
					}
				}
			}

			return result;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONObject getModelMau14T(String maritimeCode, String startDate,
			String endDate, String objName) throws SystemException {

		
		try {
			

			String query = "select temp1.*, temp2.* from (select MaritimeCode, sum(case vma_schedule_cargolist.CargoCategory when 'C2_DO' then Quantity else 0 end) as tong_nhap, sum(case vma_schedule_cargolist.CargoCategory when 'C1_XEP' then Quantity else 0 end) as tong_xuat, sum(case vma_schedule_cargolist.CargoCategory when 'C3_XEP' then Quantity when 'C3_DO' then Quantity else 0 end) as tong_noi_dia, sum(case when vma_schedule_cargolist.CargoType =9 then Quantity else 0 end) as hang_container, sum(case when vma_schedule_cargolist.CargoType =9 then TeusQuantity else 0 end) as hang_container_teus, sum(case when vma_schedule_cargolist.CargoType =3 then Quantity else 0 end) as hang_long, sum(case when vma_schedule_cargolist.CargoType =12 then Quantity else 0 end) as hang_kho from vma_schedule_cargolist inner join vma_itinerary on vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary.FlagStateOfShip = 'VN' and MaritimeCode = "
					+ maritimeCode
					+ " and (case vma_schedule_cargolist.CargoCategory when 'C2_DO' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59' when 'C3_DO' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59' when 'C1_XEP' then vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ " 23:59:59' when 'C3_XEP' then vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ " 23:59:59' else null end) ) as temp1 inner join ( select MaritimeCode as MaritimeCode_, sum(PassengerNumber) as khach_tau_bien_vn from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary.FlagStateOfShip = 'VN' and ((vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.NextPortCode is not null) or (vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.LastPortCode is not null)) and MaritimeCode = "
					+ maritimeCode
					+ " and (case vma_itinerary_schedule.NoticeShipType when '1' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59' when '2' then vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ " 23:59:59' else null end) ) as temp2 on temp1.MaritimeCode = temp2.MaritimeCode_";

			log.info("=========select bao cao mau_18_14t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();



			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();


			JSONObject result = JSONFactoryUtil.createJSONObject();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					double tong_nhap = Double.valueOf(String
							.valueOf(objects[1] != null ? objects[1] : -1));
					double tong_xuat = Double.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : -1));
					double tong_noi_dia = Double.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					double hang_container = Double.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					double hang_container_teus = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					double hang_long = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					double hang_kho = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : -1));
					String maritimeCode_ = String
							.valueOf(objects[8] != null ? objects[8]
									: StringPool.BLANK);
					int khach_tau_bien_vn = Integer.valueOf(String
							.valueOf(objects[9] != null ? objects[9] : -1));

					double tong1 = BaoCaoBussinessUtils.sum1(tong_nhap,
							tong_noi_dia, tong_xuat);
					if (tong1 >= 0) {
						result.put(objName + "1", tong1);
					}
					if (tong_xuat >= 0) {
						result.put(objName + "2", tong_xuat);
					}
					if (tong_nhap >= 0) {
						result.put(objName + "3", tong_nhap);
					}
					if (tong_noi_dia >= 0) {
						result.put(objName + "4", tong_noi_dia);
					}
					if (hang_container_teus >= 0) {
						result.put(objName + "5", hang_container_teus);
					}
					if (hang_container >= 0) {
						result.put(objName + "6", hang_container);
					}
					if (hang_long >= 0) {
						result.put(objName + "7", hang_long);
					}
					if (hang_kho >= 0) {
						result.put(objName + "8", hang_kho);
					}
					if (khach_tau_bien_vn >= 0) {
						result.put(objName + "9", khach_tau_bien_vn);
					}
				}
			}
			return result;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public BC12BTLuotHHModel getModelMau12BTLuotHH(String maritimeCode,
			String startDate, String endDate) throws SystemException {

		
		try {
			

			String query = "select temp1.*, temp2.* FROM (select MaritimeCode, sum(case when VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien, sum(case when FlagStateOfShip in ('CN','KH') and DWT < 200 and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when FlagStateOfShip in ('CN','KH') and DWT < 200 and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_duoi_200, sum(case when DWT > 200 and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when DWT > 200 and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_tren_200, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_nn, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_vn, sum( case when vma_itinerary.DocumentNameIN IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC')) AND vma_itinerary.DocumentYearIN IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC') ) AND (vma_itinerary.FlagStateOfShip = 'VN') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.DocumentNameOUT IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC')) AND vma_itinerary.DocumentYearOUT IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC') ) AND (vma_itinerary.FlagStateOfShip = 'VN') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end ) as luot_tau_bien_vn_xnc, sum(case when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_pttnd, sum(case when VRCode in ('VR-SB') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode in ('VR-SB') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_pttnd_vrsb, sum( case when vma_itinerary.DocumentNameIN IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = '4')) AND vma_itinerary.DocumentYearIN IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = '4') ) AND (vma_itinerary.FlagStateOfShip = 'VN') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.DocumentNameOUT IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = '5')) AND vma_itinerary.DocumentYearOUT IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = '5') ) AND (vma_itinerary.FlagStateOfShip = 'VN') and vma_itinerary_schedule.NoticeShipType = '2' then 1 when vma_itinerary.DocumentNameIN =0 AND vma_itinerary.DocumentYearIN = 0 AND (vma_itinerary.FlagStateOfShip = 'VN') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.DocumentNameOUT =0 AND vma_itinerary.DocumentYearOUT AND (vma_itinerary.FlagStateOfShip = 'VN') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end ) as luot_tau_bien_vn_noi_dia, sum(case when vma_itinerary_schedule.NoticeShipType = '1' then PassengerNumber when vma_itinerary_schedule.NoticeShipType = '2' then PassengerNumber else 0 end) as tong_luot_khach, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then PassengerNumber when vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then PassengerNumber else 0 end) as luot_khach_tau_bien_nn, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then PassengerNumber when vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then PassengerNumber else 0 end) as luot_khach_tau_bien_vn, sum(case when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then PassengerNumber when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then PassengerNumber else 0 end) as luot_khach_pttnd, sum(case when vma_itinerary.LastPortCode in (select PortCode from dm_port where PortType=2) then PassengerNumber when vma_itinerary.NextPortCode in (select PortCode from dm_port where PortType=2) then PassengerNumber else 0 end) as khach_tu_bo_ra_dao from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and MaritimeCode = "
					+ maritimeCode
					+ " AND (case when vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00'  and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59'  when vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00'  and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59'  else null end) ) as temp1 left join (select vma_itinerary.MaritimeCode as _MaritimeCode, sum(case when vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') then Quantity else 0 end) as tong_hang_tau_bien, sum(case when vma_schedule_cargolist.CargoCategory ='C2_DO' and vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity else 0 end) as tong_nhap, sum(case when vma_schedule_cargolist.CargoCategory ='C1_XEP' and vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity else 0 end) as tong_xuat, sum(case when vma_schedule_cargolist.CargoCategory ='C3_XEP' and vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity when vma_schedule_cargolist.CargoCategory ='C3_DO' and vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity else 0 end) as tong_noi_dia, sum(case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') and vma_schedule_cargolist.CargoType =9 and vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity else 0 end) as hang_container_tau_bien, sum(case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') and vma_schedule_cargolist.CargoType =9 and vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then TeusQuantity else 0 end) as hang_container_tau_bien_teus, sum(case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') and vma_schedule_cargolist.CargoType =3 and vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity else 0 end) as hang_long_tau_bien, sum(case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') and vma_schedule_cargolist.CargoType =12 and vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity else 0 end) as hang_kho_tau_bien, sum(case when vma_itinerary.VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') then Quantity else 0 end) as tong_hang_pttnd, sum(case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') and vma_schedule_cargolist.CargoType =9 and vma_itinerary.VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity else 0 end) as hang_container_pttnd, sum(case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') and vma_schedule_cargolist.CargoType =9 and vma_itinerary.VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then TeusQuantity else 0 end) as hang_container_pttnd_teus, sum(case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') and vma_schedule_cargolist.CargoType =3 and vma_itinerary.VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity else 0 end) as hang_long_pttnd, sum(case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') and vma_schedule_cargolist.CargoType =12 and vma_itinerary.VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') then Quantity else 0 end) as hang_kho_pttnd, sum(case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') and vma_itinerary.VRCode = 'VR-SB' then Quantity else 0 end) as hang_container_pttnd_vrsb, sum(case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C1_XEP', 'C3_XEP', 'C3_DO') and vma_itinerary.VRCode = 'VR-SB' then TeusQuantity else 0 end) as hang_container_pttnd_vrsb_teus, sum(case when vma_schedule_cargolist.CargoCategory in ('C4', 'C4', 'C5') then Quantity else 0 end) as tong_hang_qua_canh, sum(Quantity) as tong_hang from vma_schedule_cargolist inner join vma_itinerary on vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4') and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4') and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and MaritimeCode = "
					+ maritimeCode
					+ " AND (case vma_schedule_cargolist.CargoCategory when 'C2_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00'  and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59'  when 'C3_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00'  and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59'  when 'C4' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00'  and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59'  when 'C5' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00'  and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59'  when 'C1_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00'  and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59'  when 'C3_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00'  and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59'  when 'C4' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00'  and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59'  else null end) ) as temp2 on temp1.MaritimeCode = temp2._MaritimeCode";

			log.info("=========select bao cao mau_23_12bt_luot_hh>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();



			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			BC12BTLuotHHModel bc12btLuotHHModel = new BC12BTLuotHHModel();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					int luot_tau_bien = Integer.valueOf(String
							.valueOf(objects[1] != null ? objects[1] : -1));
					int luot_tau_bien_duoi_200 = Integer.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : -1));
					int luot_tau_bien_tren_200 = Integer.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					int luot_tau_bien_nn = Integer.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					int luot_tau_bien_vn = Integer.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					int luot_tau_bien_vn_xnc = Integer.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					int luot_pttnd = Integer.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : -1));
					int luot_pttnd_vrsb = Integer.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : -1));
					int luot_tau_bien_vn_noi_dia = Integer.valueOf(String
							.valueOf(objects[9] != null ? objects[9] : -1));
					int tong_luot_khach = Integer.valueOf(String
							.valueOf(objects[10] != null ? objects[10] : -1));
					int luot_khach_tau_bien_nn = Integer.valueOf(String
							.valueOf(objects[11] != null ? objects[11] : -1));
					int luot_khach_tau_bien_vn = Integer.valueOf(String
							.valueOf(objects[12] != null ? objects[12] : -1));
					int luot_khach_pttnd = Integer.valueOf(String
							.valueOf(objects[13] != null ? objects[13] : -1));
					int khach_tu_bo_ra_dao = Integer.valueOf(String
							.valueOf(objects[14] != null ? objects[14] : -1));
					String _maritimeCode = String
							.valueOf(objects[15] != null ? objects[15]
									: StringPool.BLANK);
					double tong_hang_tau_bien = Double.valueOf(String
							.valueOf(objects[16] != null ? objects[16] : -1));
					double tong_nhap = Double.valueOf(String
							.valueOf(objects[17] != null ? objects[17] : -1));
					double tong_xuat = Double.valueOf(String
							.valueOf(objects[18] != null ? objects[18] : -1));
					double tong_noi_dia = Double.valueOf(String
							.valueOf(objects[19] != null ? objects[19] : -1));
					double hang_container_tau_bien = Double.valueOf(String
							.valueOf(objects[20] != null ? objects[20] : -1));
					double hang_container_tau_bien_teus = Double.valueOf(String
							.valueOf(objects[21] != null ? objects[21] : -1));
					double hang_long_tau_bien = Double.valueOf(String
							.valueOf(objects[22] != null ? objects[22] : -1));
					double hang_kho_tau_bien = Double.valueOf(String
							.valueOf(objects[23] != null ? objects[23] : -1));
					double tong_hang_pttnd = Double.valueOf(String
							.valueOf(objects[24] != null ? objects[24] : -1));
					double hang_container_pttnd = Double.valueOf(String
							.valueOf(objects[25] != null ? objects[25] : -1));
					double hang_container_pttnd_teus = Double.valueOf(String
							.valueOf(objects[26] != null ? objects[26] : -1));
					double hang_long_pttnd = Double.valueOf(String
							.valueOf(objects[27] != null ? objects[27] : -1));
					double hang_kho_pttnd = Double.valueOf(String
							.valueOf(objects[28] != null ? objects[28] : -1));
					double hang_container_pttnd_vrsb = Double.valueOf(String
							.valueOf(objects[29] != null ? objects[29] : -1));
					double hang_container_pttnd_vrsb_teus = Double
							.valueOf(String
									.valueOf(objects[30] != null ? objects[30]
											: -1));
					double tong_hang_qua_canh = Double.valueOf(String
							.valueOf(objects[31] != null ? objects[31] : -1));
					double tong_hang = Double.valueOf(String
							.valueOf(objects[32] != null ? objects[32] : -1));

					bc12btLuotHHModel.setMaritimeCode(maritimeCode);
					bc12btLuotHHModel.setLuot_tau_bien(luot_tau_bien);
					bc12btLuotHHModel
							.setLuot_tau_bien_duoi_200(luot_tau_bien_duoi_200);
					bc12btLuotHHModel
							.setLuot_tau_bien_tren_200(luot_tau_bien_tren_200);
					bc12btLuotHHModel.setLuot_tau_bien_nn(luot_tau_bien_nn);
					bc12btLuotHHModel.setLuot_tau_bien_vn(luot_tau_bien_vn);
					bc12btLuotHHModel
							.setLuot_tau_bien_vn_xnc(luot_tau_bien_vn_xnc);
					bc12btLuotHHModel.setLuot_pttnd(luot_pttnd);
					bc12btLuotHHModel.setLuot_pttnd_vrsb(luot_pttnd_vrsb);
					bc12btLuotHHModel
							.setLuot_tau_bien_vn_noi_dia(luot_tau_bien_vn_noi_dia);
					bc12btLuotHHModel.setTong_luot_khach(tong_luot_khach);
					bc12btLuotHHModel
							.setLuot_khach_tau_bien_nn(luot_khach_tau_bien_nn);
					bc12btLuotHHModel
							.setLuot_khach_tau_bien_vn(luot_khach_tau_bien_vn);
					bc12btLuotHHModel.setLuot_khach_pttnd(luot_khach_pttnd);
					bc12btLuotHHModel.setKhach_tu_bo_ra_dao(khach_tu_bo_ra_dao);
					bc12btLuotHHModel.set_maritimeCode(_maritimeCode);
					bc12btLuotHHModel.setTong_hang_tau_bien(tong_hang_tau_bien);
					bc12btLuotHHModel.setTong_nhap(tong_nhap);
					bc12btLuotHHModel.setTong_xuat(tong_xuat);
					bc12btLuotHHModel.setTong_noi_dia(tong_noi_dia);
					bc12btLuotHHModel
							.setHang_container_tau_bien(hang_container_tau_bien);
					bc12btLuotHHModel
							.setHang_container_tau_bien_teus(hang_container_tau_bien_teus);
					bc12btLuotHHModel.setHang_long_tau_bien(hang_long_tau_bien);
					bc12btLuotHHModel.setHang_kho_tau_bien(hang_kho_tau_bien);
					bc12btLuotHHModel.setTong_hang_pttnd(tong_hang_pttnd);
					bc12btLuotHHModel
							.setHang_container_pttnd(hang_container_pttnd);
					bc12btLuotHHModel
							.setHang_container_pttnd_teus(hang_container_pttnd_teus);
					bc12btLuotHHModel.setHang_long_pttnd(hang_long_pttnd);
					bc12btLuotHHModel.setHang_kho_pttnd(hang_kho_pttnd);
					bc12btLuotHHModel
							.setHang_container_pttnd_vrsb(hang_container_pttnd_vrsb);
					bc12btLuotHHModel
							.setHang_container_pttnd_vrsb_teus(hang_container_pttnd_vrsb_teus);
					bc12btLuotHHModel.setTong_hang_qua_canh(tong_hang_qua_canh);
					bc12btLuotHHModel.setTong_hang(tong_hang);
				}
			}

			return bc12btLuotHHModel;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray getModelMau11T(String maritimeCode, String startDate,
			String endDate) throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "Select * from ( ( SELECT temp1.ItineraryNo, temp1.NameOfShip, temp1.CallSign, temp1.IMONumber, temp1.StateName, temp1.ShipTypeName, temp1.LOA, temp1.mom_nuoc_den, temp2.mom_nuoc_roi, temp1.DWT, temp1.GT, temp1.ClearanceHeight_den, temp2.ClearanceHeight_roi, temp1.luot_khach_den, temp2.luot_khach_roi, ( CASE WHEN temp1.LastPortCode IS NOT NULL AND temp1.LastPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.LastPortCode ) ELSE '' END ) AS cang_roi, ( CASE WHEN temp1.ArrivalPortCode IS NOT NULL AND temp1.ArrivalPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.ArrivalPortCode ) ELSE '' END ) AS cang_den, ( CASE WHEN temp1.NextPortCode IS NOT NULL AND temp1.NextPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.NextPortCode ) ELSE '' END ) AS cang_dich, temp1.dai_ly_tau, temp1.ngay_den, temp2.ngay_roi FROM ( SELECT tbl_tem1.*, dm_state.StateName AS StateName, dm_ship_type.ShipTypeName AS ShipTypeName FROM ( SELECT vma_itinerary.ItineraryNo AS ItineraryNo, vma_itinerary.NameOfShip AS NameOfShip, vma_itinerary.CallSign AS CallSign, vma_itinerary.IMONumber AS IMONumber, vma_itinerary_schedule.LOA AS LOA, concat( vma_itinerary_schedule.ShownDraftxF, '/', vma_itinerary_schedule.ShownDraftxA ) AS mom_nuoc_den, vma_itinerary_schedule.DWT AS DWT, vma_itinerary_schedule.GT AS GT, vma_itinerary_schedule.ClearanceHeight AS ClearanceHeight_den, vma_itinerary_schedule.PassengerNumber AS luot_khach_den, vma_itinerary.LastPortCode AS LastPortCode, vma_itinerary.ArrivalPortCode AS ArrivalPortCode, vma_itinerary.NextPortCode AS NextPortCode, vma_itinerary.TimeOfArrival AS ngay_den, CASE WHEN vma_itinerary.ArrivalShipAgency = vma_itinerary.DepartureShipAgency THEN vma_itinerary.ArrivalShipAgency WHEN LENGTH(vma_itinerary.DepartureShipAgency) = 0 THEN vma_itinerary.ArrivalShipAgency ELSE CONCAT( vma_itinerary.ArrivalShipAgency, '; ', vma_itinerary.DepartureShipAgency ) END AS dai_ly_tau, vma_itinerary.ShipTypeCode AS ShipTypeCode, vma_itinerary.FlagStateOfShip AS FlagStateOfShip FROM vma_itinerary INNER JOIN vma_itinerary_schedule ON vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo WHERE 1=1 AND vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') AND	vma_itinerary_schedule.NoticeShipType = '1' AND vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND vma_itinerary.maritimeCode = '"
					+ maritimeCode
					+ "' AND ( (vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59') OR (vma_itinerary.TimeOfArrival < '"
					+ startDate
					+ " 00:00:00') ) GROUP BY vma_itinerary.ItineraryNo ) AS tbl_tem1 LEFT JOIN dm_ship_type ON tbl_tem1.ShipTypeCode = dm_ship_type.ShipTypeCode LEFT JOIN dm_state ON dm_state.StateCode = tbl_tem1.FlagStateOfShip ) AS temp1 INNER JOIN ( SELECT vma_itinerary.ItineraryNo AS ItineraryNo, concat( vma_itinerary_schedule.ShownDraftxF, '/', vma_itinerary_schedule.ShownDraftxA ) AS mom_nuoc_roi, vma_itinerary_schedule.ClearanceHeight AS ClearanceHeight_roi, vma_itinerary_schedule.PassengerNumber AS luot_khach_roi, vma_itinerary_schedule.TimeOfDeparture AS ngay_roi FROM vma_itinerary INNER JOIN vma_itinerary_schedule ON vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo WHERE 1=1 AND vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') AND vma_itinerary_schedule.NoticeShipType = '2' AND vma_itinerary.MarkedAsDeparture NOT IN (0, 1, 6) AND vma_itinerary.maritimeCode = '"
					+ maritimeCode
					+ "' AND vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ " 23:59:59' GROUP BY vma_itinerary.ItineraryNo ) AS temp2 ON temp1.ItineraryNo = temp2.ItineraryNo ) UNION ( SELECT temp1.ItineraryNo, temp1.NameOfShip, temp1.CallSign, temp1.IMONumber, temp1.StateName, temp1.ShipTypeName, temp1.LOA, temp1.mom_nuoc_den, '' as mom_nuoc_roi, temp1.DWT, temp1.GT, temp1.ClearanceHeight_den, '' as ClearanceHeight_roi, temp1.luot_khach_den, 0 as luot_khach_roi, ( CASE WHEN temp1.LastPortCode IS NOT NULL AND temp1.LastPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.LastPortCode ) ELSE '' END ) AS cang_roi, ( CASE WHEN temp1.ArrivalPortCode IS NOT NULL AND temp1.ArrivalPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.ArrivalPortCode ) ELSE '' END ) AS cang_den, ( CASE WHEN temp1.NextPortCode IS NOT NULL AND temp1.NextPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.NextPortCode ) ELSE '' END ) AS cang_dich, temp1.dai_ly_tau, temp1.ngay_den, '' as ngay_roi FROM ( SELECT tbl_tem1.*, dm_state.StateName AS StateName, dm_ship_type.ShipTypeName AS ShipTypeName FROM ( SELECT vma_itinerary.ItineraryNo AS ItineraryNo, vma_itinerary.NameOfShip AS NameOfShip, vma_itinerary.CallSign AS CallSign, vma_itinerary.IMONumber AS IMONumber, vma_itinerary_schedule.LOA AS LOA, concat( vma_itinerary_schedule.ShownDraftxF, '/', vma_itinerary_schedule.ShownDraftxA ) AS mom_nuoc_den, vma_itinerary_schedule.DWT AS DWT, vma_itinerary_schedule.GT AS GT, vma_itinerary_schedule.ClearanceHeight AS ClearanceHeight_den, vma_itinerary_schedule.PassengerNumber AS luot_khach_den, vma_itinerary.LastPortCode AS LastPortCode, vma_itinerary.ArrivalPortCode AS ArrivalPortCode, vma_itinerary.NextPortCode AS NextPortCode, vma_itinerary.TimeOfArrival AS ngay_den, CASE WHEN vma_itinerary.ArrivalShipAgency = vma_itinerary.DepartureShipAgency THEN vma_itinerary.ArrivalShipAgency WHEN LENGTH(vma_itinerary.DepartureShipAgency) = 0 THEN vma_itinerary.ArrivalShipAgency ELSE CONCAT( vma_itinerary.ArrivalShipAgency, '; ', vma_itinerary.DepartureShipAgency ) END AS dai_ly_tau, vma_itinerary.ShipTypeCode AS ShipTypeCode, vma_itinerary.FlagStateOfShip AS FlagStateOfShip FROM vma_itinerary INNER JOIN vma_itinerary_schedule ON vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo WHERE  1=1 AND vma_itinerary.VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') AND vma_itinerary_schedule.NoticeShipType = '1' AND vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND vma_itinerary.maritimeCode = '"
					+ maritimeCode
					+ "' AND vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59' AND (vma_itinerary.MarkedAsDeparture IN (0, 1, 6) OR vma_itinerary.TimeOfDeparture > '"
					+ endDate
					+ " 23:59:59') GROUP BY vma_itinerary.ItineraryNo ) AS tbl_tem1 LEFT JOIN dm_ship_type ON tbl_tem1.ShipTypeCode = dm_ship_type.ShipTypeCode LEFT JOIN dm_state ON dm_state.StateCode = tbl_tem1.FlagStateOfShip ) AS temp1 ) ORDER BY ngay_den ) AS temp3 LEFT JOIN ( SELECT vma_itinerary.ItineraryNo AS ItineraryNo, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C1_XEP' THEN Quantity ELSE 0 END ) AS tong_xuat, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C1_XEP' THEN TeusQuantity ELSE 0 END ) AS tong_xuat_teus, SUM( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C1_XEP' AND vma_schedule_cargolist.CargoCode = 'CON02' THEN TeusQuantity ELSE 0 END ) AS tong_xuat_teus_rong, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C2_DO' THEN Quantity ELSE 0 END ) AS tong_nhap, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C2_DO' THEN TeusQuantity ELSE 0 END ) AS tong_nhap_teus, SUM( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C2_DO' AND vma_schedule_cargolist.CargoCode = 'CON02' THEN Quantity ELSE 0 END ) AS tong_nhap_teus_rong, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C3_DO' THEN Quantity ELSE 0 END ) AS noi_dia_den, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C3_DO' THEN TeusQuantity ELSE 0 END ) AS noi_dia_den_teus, SUM( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C3_DO' AND vma_schedule_cargolist.CargoCode = 'CON02' THEN TeusQuantity ELSE 0 END ) AS noi_dia_den_teus_rong, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C3_XEP' THEN Quantity ELSE 0 END ) AS noi_dia_roi, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C3_XEP' THEN TeusQuantity ELSE 0 END ) AS noi_dia_roi_teus, SUM( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C3_XEP' AND vma_schedule_cargolist.CargoCode = 'CON02' THEN TeusQuantity ELSE 0 END ) AS noi_dia_roi_teus_rong, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C4' THEN Quantity WHEN 'C4_XEP' THEN Quantity WHEN 'C4_DO' THEN Quantity ELSE 0 END ) AS qua_canh_boc_do, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C4' THEN TeusQuantity WHEN 'C4_XEP' THEN TeusQuantity WHEN 'C4_DO' THEN TeusQuantity ELSE 0 END ) AS qua_canh_boc_do_teus, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C5' THEN Quantity ELSE 0 END ) AS qua_canh_khong_boc_do, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C5' THEN TeusQuantity ELSE 0 END ) AS qua_canh_khong_boc_do_teus, SUM( CASE WHEN vma_schedule_cargolist.PortWharfCode IN ( SELECT PortWharfCode FROM dm_port_wharf WHERE IsDelete = 0 AND PortWharfType = 1 ) THEN Quantity ELSE 0 END ) AS chuyen_tai, SUM( CASE WHEN vma_schedule_cargolist.PortWharfCode IN ( SELECT PortWharfCode FROM dm_port_wharf WHERE IsDelete = 0 AND PortWharfType = 1 ) THEN TeusQuantity ELSE 0 END ) AS chuyen_tai_teus, dm_dataitem.NAME AS ten_hang FROM vma_itinerary LEFT JOIN ( vma_schedule_cargolist INNER JOIN dm_dataitem ON dm_dataitem. CODE = vma_schedule_cargolist.CargoCode AND dm_dataitem.datagroupid = '124' ) ON vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo AND vma_schedule_cargolist.itineraryScheduleId >= 0 WHERE ( ( vma_schedule_cargolist.CargoCategory IN ( 'C2_DO', 'C3_DO', 'C4', 'C5' ) AND vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND vma_itinerary.LastPortCode IS NOT NULL ) OR ( vma_schedule_cargolist.CargoCategory IN ('C1_XEP', 'C3_XEP', 'C4', 'C5') AND vma_itinerary.MarkedAsDeparture NOT IN (0, 1, 6) AND vma_itinerary.NextPortCode IS NOT NULL ) ) AND maritimeCode = '"
					+ maritimeCode
					+ "' GROUP BY ItineraryNo ) AS temp4 ON temp3.ItineraryNo = temp4.ItineraryNo";

			log.info("=========select bao cao mau_68_11t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();



			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					JSONObject result = JSONFactoryUtil.createJSONObject();

					String itineraryNo = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String nameOfShip = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String callSign = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String imoNumber = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String stateName = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					String shipTypeName = String
							.valueOf(objects[5] != null ? objects[5]
									: StringPool.BLANK);
					double loa = GetterUtil.getDouble(objects[6], 0);
					String mon_nuoc_den = String
							.valueOf(objects[7] != null ? objects[7]
									: StringPool.BLANK);
					String mon_nuoc_roi = String
							.valueOf(objects[8] != null ? objects[8]
									: StringPool.BLANK);
					double dwt = GetterUtil.getDouble(objects[9], 0);
					double gt = GetterUtil.getDouble(objects[10], 0);
					double clearanceHeight_den = GetterUtil.getDouble(
							objects[11], 0);
					double clearanceHeight_roi = GetterUtil.getDouble(
							objects[12], 0);
					int luot_khach_den = GetterUtil.getInteger(objects[13], 0);
					int luot_khach_roi = GetterUtil.getInteger(objects[14], 0);
					String cang_roi = String
							.valueOf(objects[15] != null ? objects[15]
									: StringPool.BLANK);
					String cang_den = String
							.valueOf(objects[16] != null ? objects[16]
									: StringPool.BLANK);
					String cang_dich = String
							.valueOf(objects[17] != null ? objects[17]
									: StringPool.BLANK);
					String dai_ly_tau = String
							.valueOf(objects[18] != null ? objects[18]
									: StringPool.BLANK);
					String date1 = String
							.valueOf(objects[19] != null ? objects[19]
									: StringPool.BLANK);
					String date2 = String
							.valueOf(objects[20] != null ? objects[20]
									: StringPool.BLANK);
					double tong_xuat = GetterUtil.getDouble(objects[21], 0);
					double tong_xuat_teus = GetterUtil
							.getDouble(objects[23], 0);
					double tong_xuat_teus_rong = GetterUtil.getDouble(
							objects[24], 0);
					double tong_nhap = GetterUtil.getDouble(objects[25], 0);
					double tong_nhap_teus = GetterUtil
							.getDouble(objects[26], 0);
					double tong_nhap_teus_rong = GetterUtil.getDouble(
							objects[27], 0);
					double noi_dia_den = GetterUtil.getDouble(objects[28], 0);
					double noi_dia_den_teus = GetterUtil.getDouble(objects[29],
							0);
					double noi_dia_den_teus_rong = GetterUtil.getDouble(
							objects[30], 0);
					double noi_dia_roi = GetterUtil.getDouble(objects[31], 0);
					double noi_dia_roi_teus = GetterUtil.getDouble(objects[32],
							0);
					double noi_dia_roi_teus_rong = GetterUtil.getDouble(
							objects[33], 0);
					double qua_canh_boc_do = GetterUtil.getDouble(objects[34],
							0);
					double qua_canh_boc_do_teus = GetterUtil.getDouble(
							objects[35], 0);
					double qua_canh_khong_boc_do = GetterUtil.getDouble(
							objects[36], 0);
					double qua_canh_khong_boc_do_teus = GetterUtil.getDouble(
							objects[37], 0);
					double chuyen_tai = GetterUtil.getDouble(objects[38], 0);
					double chuyen_tai_teus = GetterUtil.getDouble(objects[39],
							0);
					String ten_hang = String
							.valueOf(objects[40] != null ? objects[40]
									: StringPool.BLANK);

					NumberFormat formatter = new DecimalFormat("#0.00");
					try {
						result.put("itineraryNo", itineraryNo);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A1", nameOfShip);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A2", callSign);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A3", imoNumber);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A4", stateName);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A5", shipTypeName);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A6", loa >= 0 ? loa : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A7",
								DanhMucUtils.encodeUTF8("Đến: ") + mon_nuoc_den
										+ DanhMucUtils.encodeUTF8(", Rời: ")
										+ mon_nuoc_roi);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A8", dwt >= 0 ? dwt : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A9", gt >= 0 ? gt : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A10", clearanceHeight_den + "/"
								+ clearanceHeight_roi);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A32", cang_den);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A33", cang_roi);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A34", cang_dich);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A35", date1);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A36", date2);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A37", dai_ly_tau);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A11",
								tong_xuat >= 0 ? formatter.format(tong_xuat)
										: null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A12",
								tong_xuat_teus >= 0 ? formatter
										.format(tong_xuat_teus) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A13",
								tong_xuat_teus_rong >= 0 ? formatter
										.format(tong_xuat_teus_rong) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A14",
								tong_nhap >= 0 ? formatter.format(tong_nhap)
										: null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A15",
								tong_nhap_teus >= 0 ? formatter
										.format(tong_nhap_teus) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A16",
								tong_nhap_teus_rong >= 0 ? formatter
										.format(tong_nhap_teus_rong) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A17",
								noi_dia_den >= 0 ? formatter
										.format(noi_dia_den) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A18",
								noi_dia_den_teus >= 0 ? formatter
										.format(noi_dia_den_teus) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A19",
								noi_dia_den_teus_rong >= 0 ? formatter
										.format(noi_dia_den_teus_rong) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A20",
								noi_dia_roi >= 0 ? formatter
										.format(noi_dia_roi) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A21",
								noi_dia_roi_teus >= 0 ? formatter
										.format(noi_dia_roi_teus) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A22",
								noi_dia_roi_teus_rong >= 0 ? formatter
										.format(noi_dia_roi_teus_rong) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A23",
								chuyen_tai >= 0 ? formatter.format(chuyen_tai)
										: null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A24",
								chuyen_tai_teus >= 0 ? formatter
										.format(chuyen_tai_teus) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A25",
								qua_canh_boc_do >= 0 ? formatter
										.format(qua_canh_boc_do) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A26",
								qua_canh_boc_do_teus >= 0 ? formatter
										.format(qua_canh_boc_do_teus) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A27",
								qua_canh_khong_boc_do >= 0 ? formatter
										.format(qua_canh_khong_boc_do) : null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put(
								"A28",
								qua_canh_khong_boc_do_teus >= 0 ? formatter
										.format(qua_canh_khong_boc_do_teus)
										: null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A29", luot_khach_den >= 0 ? luot_khach_den
								: null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A30", luot_khach_roi >= 0 ? luot_khach_roi
								: null);
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						result.put("A31", ten_hang);
					} catch (Exception e) {
						// TODO: handle exception
					}

					results.put(result);
				}
			}

			return results;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray getModelMau11BT(String maritimeCode, String startDate,
			String endDate) throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "Select * from ( ( SELECT temp1.ItineraryNo, temp1.NameOfShip, temp1.RegistryNumber, temp1.VRCode, temp1.StateName, temp1.ShipTypeName, temp1.LOA, temp1.mom_nuoc_den, temp2.mom_nuoc_roi, temp1.DWT, temp1.GT, temp1.ClearanceHeight_den, temp2.ClearanceHeight_roi, temp1.luot_khach_den, temp2.luot_khach_roi, ( CASE WHEN temp1.LastPortCode IS NOT NULL AND temp1.LastPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.LastPortCode ) ELSE '' END ) AS cang_roi, ( CASE WHEN temp1.ArrivalPortCode IS NOT NULL AND temp1.ArrivalPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.ArrivalPortCode ) ELSE '' END ) AS cang_den, ( CASE WHEN temp1.NextPortCode IS NOT NULL AND temp1.NextPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.NextPortCode ) ELSE '' END ) AS cang_dich, temp1.dai_ly_tau, temp1.ngay_den, temp2.ngay_roi FROM ( SELECT tbl_tem1.*, dm_state.StateName AS StateName, dm_ship_type.ShipTypeName AS ShipTypeName FROM ( SELECT vma_itinerary.ItineraryNo AS ItineraryNo, vma_itinerary.NameOfShip AS NameOfShip, vma_itinerary.RegistryNumber AS RegistryNumber, vma_itinerary.VRCode AS VRCode, vma_itinerary_schedule.LOA AS LOA, concat( vma_itinerary_schedule.ShownDraftxF, '/', vma_itinerary_schedule.ShownDraftxA ) AS mom_nuoc_den, vma_itinerary_schedule.DWT AS DWT, vma_itinerary_schedule.GT AS GT, vma_itinerary_schedule.ClearanceHeight AS ClearanceHeight_den, vma_itinerary_schedule.PassengerNumber AS luot_khach_den, vma_itinerary.LastPortCode AS LastPortCode, vma_itinerary.ArrivalPortCode AS ArrivalPortCode, vma_itinerary.NextPortCode AS NextPortCode, vma_itinerary.TimeOfArrival AS ngay_den, CASE WHEN LENGTH(vma_itinerary.DepartureShipAgency) = 0 OR LENGTH(vma_itinerary.ArrivalShipAgency) THEN vma_itinerary.ShipOwnerName WHEN vma_itinerary.ArrivalShipAgency = vma_itinerary.DepartureShipAgency THEN vma_itinerary.ArrivalShipAgency WHEN LENGTH(vma_itinerary.DepartureShipAgency) = 0 THEN vma_itinerary.ArrivalShipAgency ELSE CONCAT( vma_itinerary.ArrivalShipAgency, '; ', vma_itinerary.DepartureShipAgency ) END AS dai_ly_tau, vma_itinerary.ShipTypeCode AS ShipTypeCode, vma_itinerary.FlagStateOfShip AS FlagStateOfShip FROM vma_itinerary INNER JOIN vma_itinerary_schedule ON vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo WHERE 1=1 AND vma_itinerary.VRCode in ('VR-SB','VR-SI','VR-SII','---') AND	vma_itinerary_schedule.NoticeShipType = '1' AND vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND vma_itinerary.MaritimeCode = '"
					+ maritimeCode
					+ "' AND ( (vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59') OR (vma_itinerary.TimeOfArrival < '"
					+ startDate
					+ " 00:00:00') ) GROUP BY vma_itinerary.ItineraryNo ) AS tbl_tem1 LEFT JOIN dm_ship_type ON tbl_tem1.ShipTypeCode = dm_ship_type.ShipTypeCode LEFT JOIN dm_state ON dm_state.StateCode = tbl_tem1.FlagStateOfShip ) AS temp1 INNER JOIN ( SELECT vma_itinerary.ItineraryNo AS ItineraryNo, concat( vma_itinerary_schedule.ShownDraftxF, '/', vma_itinerary_schedule.ShownDraftxA ) AS mom_nuoc_roi, vma_itinerary_schedule.ClearanceHeight AS ClearanceHeight_roi, vma_itinerary_schedule.PassengerNumber AS luot_khach_roi, vma_itinerary_schedule.TimeOfDeparture AS ngay_roi FROM vma_itinerary INNER JOIN vma_itinerary_schedule ON vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo WHERE 1=1 AND vma_itinerary.VRCode in ('VR-SB','VR-SI','VR-SII','---') AND	vma_itinerary_schedule.NoticeShipType = '2' AND vma_itinerary.MarkedAsDeparture NOT IN (0, 1, 6) AND vma_itinerary.MaritimeCode = '"
					+ maritimeCode
					+ "' AND vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ " 23:59:59' GROUP BY vma_itinerary.ItineraryNo ) AS temp2 ON temp1.ItineraryNo = temp2.ItineraryNo ) UNION ( SELECT temp1.ItineraryNo, temp1.NameOfShip, temp1.RegistryNumber, temp1.VRCode, temp1.StateName, temp1.ShipTypeName, temp1.LOA, temp1.mom_nuoc_den, '' as mom_nuoc_roi, temp1.DWT, temp1.GT, temp1.ClearanceHeight_den, '' as ClearanceHeight_roi, temp1.luot_khach_den, 0 as luot_khach_roi, ( CASE WHEN temp1.LastPortCode IS NOT NULL AND temp1.LastPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.LastPortCode ) ELSE '' END ) AS cang_roi, ( CASE WHEN temp1.ArrivalPortCode IS NOT NULL AND temp1.ArrivalPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.ArrivalPortCode ) ELSE '' END ) AS cang_den, ( CASE WHEN temp1.NextPortCode IS NOT NULL AND temp1.NextPortCode <> '' THEN ( SELECT PortName FROM dm_port WHERE PortCode = temp1.NextPortCode ) ELSE '' END ) AS cang_dich, temp1.dai_ly_tau, temp1.ngay_den, '' as ngay_roi FROM ( SELECT tbl_tem1.*, dm_state.StateName AS StateName, dm_ship_type.ShipTypeName AS ShipTypeName FROM ( SELECT vma_itinerary.ItineraryNo AS ItineraryNo, vma_itinerary.NameOfShip AS NameOfShip, vma_itinerary.RegistryNumber AS RegistryNumber, vma_itinerary.VRCode AS VRCode, vma_itinerary_schedule.LOA AS LOA, concat( vma_itinerary_schedule.ShownDraftxF, '/', vma_itinerary_schedule.ShownDraftxA ) AS mom_nuoc_den, vma_itinerary_schedule.DWT AS DWT, vma_itinerary_schedule.GT AS GT, vma_itinerary_schedule.ClearanceHeight AS ClearanceHeight_den, vma_itinerary_schedule.PassengerNumber AS luot_khach_den, vma_itinerary.LastPortCode AS LastPortCode, vma_itinerary.ArrivalPortCode AS ArrivalPortCode, vma_itinerary.NextPortCode AS NextPortCode, vma_itinerary.TimeOfArrival AS ngay_den, CASE WHEN LENGTH(vma_itinerary.DepartureShipAgency) = 0 OR LENGTH(vma_itinerary.ArrivalShipAgency) THEN vma_itinerary.ShipOwnerName WHEN vma_itinerary.ArrivalShipAgency = vma_itinerary.DepartureShipAgency THEN vma_itinerary.ArrivalShipAgency WHEN LENGTH(vma_itinerary.DepartureShipAgency) = 0 THEN vma_itinerary.ArrivalShipAgency ELSE CONCAT( vma_itinerary.ArrivalShipAgency, '; ', vma_itinerary.DepartureShipAgency ) END AS dai_ly_tau, vma_itinerary.ShipTypeCode AS ShipTypeCode, vma_itinerary.FlagStateOfShip AS FlagStateOfShip FROM vma_itinerary INNER JOIN vma_itinerary_schedule ON vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo WHERE 1=1 AND vma_itinerary.VRCode in ('VR-SB','VR-SI','VR-SII','---') AND	vma_itinerary_schedule.NoticeShipType = '1' AND vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND vma_itinerary.MaritimeCode = '"
					+ maritimeCode
					+ "' AND vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' AND vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59' AND (vma_itinerary.MarkedAsDeparture IN (0, 1, 6) OR vma_itinerary.TimeOfDeparture > '"
					+ endDate
					+ " 23:59:59') GROUP BY vma_itinerary.ItineraryNo ) AS tbl_tem1 LEFT JOIN dm_ship_type ON tbl_tem1.ShipTypeCode = dm_ship_type.ShipTypeCode LEFT JOIN dm_state ON dm_state.StateCode = tbl_tem1.FlagStateOfShip ) AS temp1 ) ORDER BY ngay_den ) AS temp3 LEFT JOIN ( SELECT vma_itinerary.ItineraryNo AS ItineraryNo, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C1_XEP' THEN Quantity ELSE 0 END ) AS tong_xuat, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C1_XEP' THEN TeusQuantity ELSE 0 END ) AS tong_xuat_teus, SUM( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C1_XEP' AND vma_schedule_cargolist.CargoCode = 'CON02' THEN TeusQuantity ELSE 0 END ) AS tong_xuat_teus_rong, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C2_DO' THEN Quantity ELSE 0 END ) AS tong_nhap, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C2_DO' THEN TeusQuantity ELSE 0 END ) AS tong_nhap_teus, SUM( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C2_DO' AND vma_schedule_cargolist.CargoCode = 'CON02' THEN Quantity ELSE 0 END ) AS tong_nhap_teus_rong, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C3_DO' THEN Quantity ELSE 0 END ) AS noi_dia_den, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C3_DO' THEN TeusQuantity ELSE 0 END ) AS noi_dia_den_teus, SUM( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C3_DO' AND vma_schedule_cargolist.CargoCode = 'CON02' THEN TeusQuantity ELSE 0 END ) AS noi_dia_den_teus_rong, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C3_XEP' THEN Quantity ELSE 0 END ) AS noi_dia_roi, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C3_XEP' THEN TeusQuantity ELSE 0 END ) AS noi_dia_roi_teus, SUM( CASE WHEN vma_schedule_cargolist.CargoCategory = 'C3_XEP' AND vma_schedule_cargolist.CargoCode = 'CON02' THEN TeusQuantity ELSE 0 END ) AS noi_dia_roi_teus_rong, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C4' THEN Quantity WHEN 'C4_XEP' THEN Quantity WHEN 'C4_DO' THEN Quantity ELSE 0 END ) AS qua_canh_boc_do, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C4' THEN TeusQuantity WHEN 'C4_XEP' THEN TeusQuantity WHEN 'C4_DO' THEN TeusQuantity ELSE 0 END ) AS qua_canh_boc_do_teus, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C5' THEN Quantity ELSE 0 END ) AS qua_canh_khong_boc_do, SUM( CASE vma_schedule_cargolist.CargoCategory WHEN 'C5' THEN TeusQuantity ELSE 0 END ) AS qua_canh_khong_boc_do_teus, SUM( CASE WHEN vma_schedule_cargolist.PortWharfCode IN ( SELECT PortWharfCode FROM dm_port_wharf WHERE IsDelete = 0 AND PortWharfType = 1 ) THEN Quantity ELSE 0 END ) AS chuyen_tai, SUM( CASE WHEN vma_schedule_cargolist.PortWharfCode IN ( SELECT PortWharfCode FROM dm_port_wharf WHERE IsDelete = 0 AND PortWharfType = 1 ) THEN TeusQuantity ELSE 0 END ) AS chuyen_tai_teus, dm_dataitem.NAME AS ten_hang FROM vma_itinerary LEFT JOIN ( vma_schedule_cargolist INNER JOIN dm_dataitem ON dm_dataitem. CODE = vma_schedule_cargolist.CargoCode AND dm_dataitem.datagroupid = '124' ) ON vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo AND vma_schedule_cargolist.itineraryScheduleId >= 0 WHERE ( ( vma_schedule_cargolist.CargoCategory IN ( 'C2_DO', 'C3_DO', 'C4', 'C5' ) AND vma_itinerary.MarkedAsArrival NOT IN (0, 1, 6) AND vma_itinerary.LastPortCode IS NOT NULL ) OR ( vma_schedule_cargolist.CargoCategory IN ('C1_XEP', 'C3_XEP', 'C4', 'C5') AND vma_itinerary.MarkedAsDeparture NOT IN (0, 1, 6) AND vma_itinerary.NextPortCode IS NOT NULL ) ) AND MaritimeCode = '"
					+ maritimeCode
					+ "' GROUP BY ItineraryNo ) AS temp4 ON temp3.ItineraryNo = temp4.ItineraryNo";

			log.info("=========select bao cao mau_45_11bt>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					JSONObject result = JSONFactoryUtil.createJSONObject();

					String itineraryNo = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String nameOfShip = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String registryNumber = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String vrCode = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String shipTypeName = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					double loa = GetterUtil.getDouble(objects[5], 0);
					double dwt = GetterUtil.getDouble(objects[6], 0);
					double gt = GetterUtil.getDouble(objects[7], 0);
					int luot_khach_den = GetterUtil.getInteger(objects[8], 0);
					int luot_khach_roi = GetterUtil.getInteger(objects[9], 0);
					String cang_roi = String
							.valueOf(objects[10] != null ? objects[10]
									: StringPool.BLANK);
					String cang_den = String
							.valueOf(objects[11] != null ? objects[11]
									: StringPool.BLANK);
					String cang_dich = String
							.valueOf(objects[12] != null ? objects[12]
									: StringPool.BLANK);
					String dai_ly_tau = String
							.valueOf(objects[13] != null ? objects[18]
									: StringPool.BLANK);
					String date1 = String
							.valueOf(objects[14] != null ? objects[14]
									: StringPool.BLANK);
					String date2 = String
							.valueOf(objects[15] != null ? objects[15]
									: StringPool.BLANK);
					itineraryNo = String
							.valueOf(objects[16] != null ? objects[16]
									: StringPool.BLANK);
					double tong_xuat = GetterUtil.getDouble(objects[17], 0);
					double tong_xuat_teus = GetterUtil
							.getDouble(objects[18], 0);
					double tong_xuat_teus_rong = GetterUtil.getDouble(
							objects[19], 0);
					double tong_nhap = GetterUtil.getDouble(objects[20], 0);
					double tong_nhap_teus = GetterUtil
							.getDouble(objects[21], 0);
					double tong_nhap_teus_rong = GetterUtil.getDouble(
							objects[22], 0);
					double noi_dia_den = GetterUtil.getDouble(objects[23], 0);
					double noi_dia_den_teus = GetterUtil.getDouble(objects[24],
							0);
					double noi_dia_den_teus_rong = GetterUtil.getDouble(
							objects[25], 0);
					double noi_dia_roi = GetterUtil.getDouble(objects[26], 0);
					double noi_dia_roi_teus = GetterUtil.getDouble(objects[27],
							0);
					double noi_dia_roi_teus_rong = GetterUtil.getDouble(
							objects[28], 0);
					double qua_canh_boc_do = GetterUtil.getDouble(objects[29],
							0);
					double qua_canh_boc_do_teus = GetterUtil.getDouble(
							objects[30], 0);
					double qua_canh_khong_boc_do = GetterUtil.getDouble(
							objects[31], 0);
					double qua_canh_khong_boc_do_teus = GetterUtil.getDouble(
							objects[32], 0);
					double chuyen_tai = GetterUtil.getDouble(objects[33], 0);
					double chuyen_tai_teus = GetterUtil.getDouble(objects[34],
							0);
					String ten_hang = String
							.valueOf(objects[35] != null ? objects[35]
									: StringPool.BLANK);

					NumberFormat formatter = new DecimalFormat("#0.00");
					try {
						result.put("itineraryNo", itineraryNo);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A1", nameOfShip);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A2", registryNumber);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A3", shipTypeName);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A4", vrCode);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A5", loa >= 0 ? loa : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A6", dwt >= 0 ? dwt : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A7", gt >= 0 ? gt : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A8",
								tong_xuat >= 0 ? formatter.format(tong_xuat)
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A9",
								tong_xuat_teus >= 0 ? formatter
										.format(tong_xuat_teus) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A10",
								tong_xuat_teus_rong >= 0 ? formatter
										.format(tong_xuat_teus_rong) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A11",
								tong_nhap >= 0 ? formatter.format(tong_nhap)
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A12",
								tong_nhap_teus >= 0 ? formatter
										.format(tong_nhap_teus) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A13",
								tong_nhap_teus_rong >= 0 ? formatter
										.format(tong_nhap_teus_rong) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A14",
								noi_dia_den >= 0 ? formatter
										.format(noi_dia_den) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A15",
								noi_dia_den_teus >= 0 ? formatter
										.format(noi_dia_den_teus) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A16",
								noi_dia_den_teus_rong >= 0 ? formatter
										.format(noi_dia_den_teus_rong) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A17",
								noi_dia_roi >= 0 ? formatter
										.format(noi_dia_roi) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A18",
								noi_dia_roi_teus >= 0 ? formatter
										.format(noi_dia_roi_teus) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A19",
								noi_dia_roi_teus_rong >= 0 ? formatter
										.format(noi_dia_roi_teus_rong) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A20",
								chuyen_tai >= 0 ? formatter.format(chuyen_tai)
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A21",
								chuyen_tai_teus >= 0 ? formatter
										.format(chuyen_tai_teus) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A22",
								qua_canh_boc_do >= 0 ? formatter
										.format(qua_canh_boc_do) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A23",
								qua_canh_boc_do_teus >= 0 ? formatter
										.format(qua_canh_boc_do_teus) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A24",
								qua_canh_khong_boc_do >= 0 ? formatter
										.format(qua_canh_khong_boc_do) : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								"A25",
								qua_canh_khong_boc_do_teus >= 0 ? formatter
										.format(qua_canh_khong_boc_do_teus)
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A26", luot_khach_den >= 0 ? luot_khach_den
								: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A27", luot_khach_roi >= 0 ? luot_khach_roi
								: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A28", ten_hang);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A29", cang_den);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A30", cang_roi);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A31", cang_dich);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A32", date1);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A33", date2);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A34", dai_ly_tau);
					} catch (Exception e) {
						// nothing to do
					}

					results.put(result);
				}
			}

			return results;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONObject getModelMau19T(String maritimeCode, String startDate,
			String endDate) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			

			String query = "select temp1.*, temp2.* from (select MaritimeCode, sum(case when tb4.CargoCategory in ('C1_XEP', 'C2_DO', 'C3_DO', 'C3_XEP') and tb4.CargoType =9 then Quantity else 0 end) as hang_container, sum(case when tb4.CargoCategory in ('C1_XEP', 'C2_DO', 'C3_DO', 'C3_XEP') and tb4.CargoType =3 then Quantity else 0 end) as hang_long, sum(case when tb4.CargoCategory in ('C1_XEP', 'C2_DO', 'C3_DO', 'C3_XEP') and tb4.CargoType =12 then Quantity else 0 end) as hang_kho, sum(case when tb4.CargoCategory in ('C5','C4','C4') then Quantity else 0 end) as qua_canh, sum(case when tb3.ShipTypeCode <> 'T69' then 1 else 0 end) as tau_hang, sum(case when tb3.ShipTypeCode = 'T69' then 1 else 0 end) as tau_khach from vma_schedule_cargolist as tb4 inner join vma_itinerary as tb3 on tb4.ItineraryNo = tb3.ItineraryNo where tb3.VRCode not in ('VR-SB','VR-SI','VR-SII','---') and ( (tb4.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4') and tb3.MarkedAsArrival not in (0,1,6) and tb3.LastPortCode is not null) or (tb4.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4') and tb3.MarkedAsDeparture not in (0,1,6) and tb3.NextPortCode is not null) ) and MaritimeCode = "
					+ maritimeCode
					+ " and (case tb4.CargoCategory when 'C2_DO' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C3_DO' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C4' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C5' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C1_XEP' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' when 'C3_XEP' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' when 'C4' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' else null end) ) as temp1 inner join ( select MaritimeCode as MaritimeCode_, sum(PassengerNumber) as khach_pttnd from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where vma_itinerary.VRCode not in ('VR-SB','VR-SI','VR-SII','---') and (vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.NextPortCode is not null) or (vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.LastPortCode is not null) and ( (vma_itinerary.MarkedAsArrival not in (0,1,6)) or (vma_itinerary.MarkedAsDeparture not in (0,1,6)) ) and MaritimeCode = "
					+ maritimeCode
					+ " and (case vma_itinerary_schedule.NoticeShipType when '1' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59' when '2' then vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ " 23:59:59' else null end) ) as temp2 on temp1.MaritimeCode = temp2.MaritimeCode_";

			log.info("=========select bao cao mau_19t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					double hang_container = Double.valueOf(String
							.valueOf(objects[1] != null ? objects[1] : -1));
					double hang_long = Double.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : -1));
					double hang_kho = Double.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					double qua_canh = Double.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					int tau_hang = Integer.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					int tau_khach = Integer.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					String maritimeCode_ = String
							.valueOf(objects[7] != null ? objects[7]
									: StringPool.BLANK);
					int khach_pttnd = Integer.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : -1));

					result.put("tuNgay", startDate);
					result.put("denNgay", endDate);
					result.put("C1", hang_container >= 0 ? hang_container
							: null);
					result.put("C2", hang_long >= 0 ? hang_long : null);
					result.put("C3", hang_kho >= 0 ? hang_kho : null);
					result.put("C4", qua_canh >= 0 ? qua_canh : null);
					result.put("C5", khach_pttnd >= 0 ? khach_pttnd : null);
					result.put("C6", tau_hang >= 0 ? tau_hang : null);
					result.put("C7", tau_khach >= 0 ? tau_khach : null);
				}
			}

			return result;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONObject getModelMau20T(String maritimeCode, String startDate,
			String endDate) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			

			String query = "select temp1.*, temp2.* from (select MaritimeCode, sum(case when tb4.CargoCategory in ('C1_XEP', 'C2_DO', 'C3_DO', 'C3_XEP') and tb4.CargoType =9 then Quantity else 0 end) as hang_container, sum(case when tb4.CargoCategory in ('C1_XEP', 'C2_DO', 'C3_DO', 'C3_XEP') and tb4.CargoType =3 then Quantity else 0 end) as hang_long, sum(case when tb4.CargoCategory in ('C1_XEP', 'C2_DO', 'C3_DO', 'C3_XEP') and tb4.CargoType =12 then Quantity else 0 end) as hang_kho, sum(case when tb4.CargoCategory in ('C5','C4','C4') then Quantity else 0 end) as qua_canh, sum(case when tb3.ShipTypeCode <> 'T69' then 1 else 0 end) as tau_hang, sum(case when tb3.ShipTypeCode = 'T69' then 1 else 0 end) as tau_khach from vma_schedule_cargolist as tb4 inner join vma_itinerary as tb3 on tb4.ItineraryNo = tb3.ItineraryNo where tb3.VRCode = 'VR-SB' and ( (tb4.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4') and tb3.MarkedAsArrival not in (0,1,6) and tb3.LastPortCode is not null) or (tb4.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4') and tb3.MarkedAsDeparture not in (0,1,6) and tb3.NextPortCode is not null) ) and MaritimeCode = "
					+ maritimeCode
					+ " and (case tb4.CargoCategory when 'C2_DO' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C3_DO' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C4' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C5' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C1_XEP' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' when 'C3_XEP' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' when 'C4' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' else null end) ) as temp1 inner join ( select MaritimeCode as MaritimeCode_, sum(PassengerNumber) as khach_pttnd from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where vma_itinerary.VRCode = 'VR-SB' and (vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.NextPortCode is not null) or (vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.LastPortCode is not null) and ( (vma_itinerary.MarkedAsArrival not in (0,1,6)) or (vma_itinerary.MarkedAsDeparture not in (0,1,6)) ) and MaritimeCode = "
					+ maritimeCode
					+ " and (case vma_itinerary_schedule.NoticeShipType when '1' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59' when '2' then vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ " 23:59:59' else null end) ) as temp2 on temp1.MaritimeCode = temp2.MaritimeCode_";

			log.info("=========select bao cao mau_20t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					double hang_container = Double.valueOf(String
							.valueOf(objects[1] != null ? objects[1] : -1));
					double hang_long = Double.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : -1));
					double hang_kho = Double.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					double qua_canh = Double.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					int tau_hang = Integer.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					int tau_khach = Integer.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					String maritimeCode_ = String
							.valueOf(objects[7] != null ? objects[7]
									: StringPool.BLANK);
					int khach_pttnd = Integer.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : -1));

					result.put("C1", hang_container >= 0 ? hang_container
							: null);
					result.put("C2", hang_long >= 0 ? hang_long : null);
					result.put("C3", hang_kho >= 0 ? hang_kho : null);
					result.put("C4", qua_canh >= 0 ? qua_canh : null);
					result.put("C5", khach_pttnd >= 0 ? khach_pttnd : null);
					result.put("C6", tau_hang >= 0 ? tau_hang : null);
					result.put("C7", tau_khach >= 0 ? tau_khach : null);
				}
			}

			return result;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONObject getModelMau21T(String maritimeCode, String startDate,
			String endDate) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			

			String query = "select temp1.*, temp2.* from (select MaritimeCode, sum(case when tb4.CargoCategory in ('C1_XEP', 'C2_DO', 'C3_DO', 'C3_XEP') and tb4.CargoType =9 then Quantity else 0 end) as hang_container, sum(case when tb4.CargoCategory in ('C1_XEP', 'C2_DO', 'C3_DO', 'C3_XEP') and tb4.CargoType =3 then Quantity else 0 end) as hang_long, sum(case when tb4.CargoCategory in ('C1_XEP', 'C2_DO', 'C3_DO', 'C3_XEP') and tb4.CargoType =12 then Quantity else 0 end) as hang_kho, sum(case when tb4.CargoCategory in ('C5','C4','C4') then Quantity else 0 end) as qua_canh, sum(case when tb3.ShipTypeCode <> 'T69' then 1 else 0 end) as tau_hang, sum(case when tb3.ShipTypeCode = 'T69' then 1 else 0 end) as tau_khach from vma_schedule_cargolist as tb4 inner join vma_itinerary as tb3 on tb4.ItineraryNo = tb3.ItineraryNo where tb3.VRCode not in ('VR', 'VRH') and ( (tb4.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4') and tb3.MarkedAsArrival not in (0,1,6) and tb3.LastPortCode is not null) or (tb4.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4') and tb3.MarkedAsDeparture not in (0,1,6) and tb3.NextPortCode is not null) ) and MaritimeCode = "
					+ maritimeCode
					+ " and (case tb4.CargoCategory when 'C2_DO' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C3_DO' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C4' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C5' then tb3.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when 'C1_XEP' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' when 'C3_XEP' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' when 'C4' then tb3.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and tb3.TimeOfDeparture <='"
					+ endDate
					+ " 23:59:59' else null end) ) as temp1 inner join ( select MaritimeCode as MaritimeCode_, sum(PassengerNumber) as khach_pttnd from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where vma_itinerary.VRCode in ('VR-SB','VR-SI','VR-SII','---') and (vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.NextPortCode is not null and vma_itinerary.LastPortCode in (select PortCode from dm_port where PortType=2)) or (vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.LastPortCode is not null and vma_itinerary.NextPortCode in (select PortCode from dm_port where PortType=2)) and ( (vma_itinerary.MarkedAsArrival not in (0,1,6)) or (vma_itinerary.MarkedAsDeparture not in (0,1,6)) ) and MaritimeCode = "
					+ maritimeCode
					+ " and (case vma_itinerary_schedule.NoticeShipType when '1' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ " 23:59:59' when '2' then vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ " 23:59:59' else null end) ) as temp2 on temp1.MaritimeCode = temp2.MaritimeCode_";

			log.info("=========select bao cao mau_21t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					double hang_container = Double.valueOf(String
							.valueOf(objects[1] != null ? objects[1] : -1));
					double hang_long = Double.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : -1));
					double hang_kho = Double.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					double qua_canh = Double.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					int tau_hang = Integer.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					int tau_khach = Integer.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					String maritimeCode_ = String
							.valueOf(objects[7] != null ? objects[7]
									: StringPool.BLANK);
					int khach_pttnd = Integer.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : -1));

					result.put("C1", hang_container >= 0 ? hang_container
							: null);
					result.put("C2", hang_long >= 0 ? hang_long : null);
					result.put("C3", hang_kho >= 0 ? hang_kho : null);
					result.put("C4", qua_canh >= 0 ? qua_canh : null);
					result.put("C5", khach_pttnd >= 0 ? khach_pttnd : null);
					result.put("C6", tau_hang >= 0 ? tau_hang : null);
					result.put("C7", tau_khach >= 0 ? tau_khach : null);
				}
			}

			return result;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray getModelMau65T(String maritimeCode, String startDate,
			String endDate) throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "select temp1.*, temp2.StateName FROM (select MaritimeCode, FlagStateOfShip, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then 1 else 0 end) as luot_tau_bien_vao, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_ra, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary_schedule.GT else 0 end) as GT_tau_bien_vao, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary_schedule.GT else 0 end) as GT_tau_bien_ra, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary_schedule.GT when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary_schedule.GT else 0 end) as GT_tau_bien, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary_schedule.DWT else 0 end) as DWT_tau_bien_vao, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary_schedule.DWT else 0 end) as DWT_tau_bien_ra, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary_schedule.DWT when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary_schedule.DWT else 0 end) as DWT_tau_bien from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6)) or (vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6)) ) and MaritimeCode = "
					+ maritimeCode
					+ " AND (case when vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' else null end) group by FlagStateOfShip ) as temp1 inner join dm_state as temp2 on temp1.FlagStateOfShip = temp2.StateCode order by temp2.StateName";

			log.info("=========select bao cao mau_65t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					JSONObject result = JSONFactoryUtil.createJSONObject();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String flagState = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					int luot_tau_bien_vao = Integer.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : -1));
					int luot_tau_bien_ra = Integer.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					int luot_tau_bien = Integer.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					double gt_tau_bien_vao = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					double gt_tau_bien_ra = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					double gt_tau_bien = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : -1));
					double dwt_tau_bien_vao = Double.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : -1));
					double dwt_tau_bien_ra = Double.valueOf(String
							.valueOf(objects[9] != null ? objects[9] : -1));
					double dwt_tau_bien = Double.valueOf(String
							.valueOf(objects[10] != null ? objects[10] : -1));
					String stateName = String
							.valueOf(objects[11] != null ? objects[11]
									: StringPool.BLANK);

					result.put("stateName", stateName);
					result.put("luot_tau_bien_vao",
							luot_tau_bien_vao >= 0 ? luot_tau_bien_vao : null);
					result.put("luot_tau_bien_ra",
							luot_tau_bien_ra >= 0 ? luot_tau_bien_ra : null);
					result.put("luot_tau_bien",
							luot_tau_bien >= 0 ? luot_tau_bien : null);
					result.put("gt_tau_bien_vao",
							gt_tau_bien_vao >= 0 ? gt_tau_bien_vao : null);
					result.put("gt_tau_bien_ra",
							gt_tau_bien_ra >= 0 ? gt_tau_bien_ra : null);
					result.put("gt_tau_bien", gt_tau_bien >= 0 ? gt_tau_bien
							: null);
					result.put("dwt_tau_bien_vao",
							dwt_tau_bien_vao >= 0 ? dwt_tau_bien_vao : null);
					result.put("dwt_tau_bien_ra",
							dwt_tau_bien_ra >= 0 ? dwt_tau_bien_ra : null);
					result.put("dwt_tau_bien", dwt_tau_bien >= 0 ? dwt_tau_bien
							: null);

					results.put(result);
				}
			}

			return results;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray getModelMau66T(String maritimeCode, String startDate,
			String endDate) throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "select MaritimeCode, vma_itinerary_schedule.ShipAgencyCode as ShipAgencyCode, vma_itinerary_schedule.ShipAgencyName as ShipAgencyName, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then 1 else 0 end) as luot_tau_bien_vao, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_ra, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary_schedule.GT else 0 end) as GT_tau_bien_vao, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary_schedule.GT else 0 end) as GT_tau_bien_ra, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary_schedule.GT when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary_schedule.GT else 0 end) as GT_tau_bien, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary_schedule.DWT else 0 end) as DWT_tau_bien_vao, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary_schedule.DWT else 0 end) as DWT_tau_bien_ra, sum(case when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary_schedule.DWT when VRCode in ('VR','VRH') and vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary_schedule.DWT else 0 end) as DWT_tau_bien from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6)) or (vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6)) ) and MaritimeCode = "
					+ maritimeCode
					+ " AND (case when vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' else null end) group by ShipAgencyCode order by ShipAgencyName";

			log.info("=========select bao cao mau_66t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					JSONObject result = JSONFactoryUtil.createJSONObject();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String shipAgencyCode = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String shipAgencyName = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					int luot_tau_bien_vao = Integer.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					int luot_tau_bien_ra = Integer.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					int luot_tau_bien = Integer.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					double gt_tau_bien_vao = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					double gt_tau_bien_ra = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : -1));
					double gt_tau_bien = Double.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : -1));
					double dwt_tau_bien_vao = Double.valueOf(String
							.valueOf(objects[9] != null ? objects[9] : -1));
					double dwt_tau_bien_ra = Double.valueOf(String
							.valueOf(objects[10] != null ? objects[10] : -1));
					double dwt_tau_bien = Double.valueOf(String
							.valueOf(objects[11] != null ? objects[11] : -1));

					result.put("shipAgencyName", shipAgencyName);
					result.put("luot_tau_bien_vao",
							luot_tau_bien_vao >= 0 ? luot_tau_bien_vao : null);
					result.put("luot_tau_bien_ra",
							luot_tau_bien_ra >= 0 ? luot_tau_bien_ra : null);
					result.put("luot_tau_bien",
							luot_tau_bien >= 0 ? luot_tau_bien : null);
					result.put("gt_tau_bien_vao",
							gt_tau_bien_vao >= 0 ? gt_tau_bien_vao : null);
					result.put("gt_tau_bien_ra",
							gt_tau_bien_ra >= 0 ? gt_tau_bien_ra : null);
					result.put("gt_tau_bien", gt_tau_bien >= 0 ? gt_tau_bien
							: null);
					result.put("dwt_tau_bien_vao",
							dwt_tau_bien_vao >= 0 ? dwt_tau_bien_vao : null);
					result.put("dwt_tau_bien_ra",
							dwt_tau_bien_ra >= 0 ? dwt_tau_bien_ra : null);
					result.put("dwt_tau_bien", dwt_tau_bien >= 0 ? dwt_tau_bien
							: null);

					results.put(result);
				}
			}

			return results;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONObject getModelMau67T(String maritimeCode, String startDate,
			String endDate) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			

			String query = "select MaritimeCode, sum(case when vma_itinerary_schedule.DWT < 5000 and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.DWT < 5000 and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as DWT_5000, sum(case when vma_itinerary_schedule.DWT >= 5000 and vma_itinerary_schedule.DWT <=10000 and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.DWT >= 5000 and vma_itinerary_schedule.DWT <=10000 and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as DWT_5000_10000, sum(case when vma_itinerary_schedule.DWT >= 10000 and vma_itinerary_schedule.DWT <=15000 and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.DWT >= 10000 and vma_itinerary_schedule.DWT <=15000 and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as DWT_10000_15000, sum(case when vma_itinerary_schedule.DWT >= 15000 and vma_itinerary_schedule.DWT <=20000 and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.DWT >= 15000 and vma_itinerary_schedule.DWT <=20000 and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as DWT_15000_20000, sum(case when vma_itinerary_schedule.DWT > 20000 and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.DWT > 20000 and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as DWT_20000, sum(case when vma_itinerary_schedule.LOA < 145 and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.LOA < 145 and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as LOA_145, sum(case when vma_itinerary_schedule.LOA >= 145 and vma_itinerary_schedule.LOA <= 155 and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.LOA >= 145 and vma_itinerary_schedule.LOA <= 155 and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as LOA_145_155, sum(case when vma_itinerary_schedule.LOA > 155 and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.LOA > 155 and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as LOA_155 from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6)) or (vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6)) ) and MaritimeCode = "
					+ maritimeCode
					+ " AND (case when vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate + "' else null end)";

			log.info("=========select bao cao mau_67t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				int tong = 0;
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					int dwt_5000 = Integer.valueOf(String
							.valueOf(objects[1] != null ? objects[1] : -1));
					int dwt_5000_10000 = Integer.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : -1));
					int dwt_10000_15000 = Integer.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					int dwt_15000_20000 = Integer.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					int dwt_20000 = Integer.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					int loa_145 = Integer.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					int loa_145_155 = Integer.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : -1));
					int loa_155 = Integer.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : -1));

					tong = dwt_5000 >= 0 ? dwt_5000
							: 0 + dwt_5000_10000 >= 0 ? dwt_5000_10000
									: 0 + dwt_10000_15000 >= 0 ? dwt_10000_15000
											: 0 + dwt_15000_20000 >= 0 ? dwt_15000_20000
													: 0 + dwt_20000 >= 0 ? dwt_20000
															: 0 + loa_145 >= 0 ? loa_145
																	: 0 + loa_145_155 >= 0 ? loa_145_155
																			: 0 + loa_155 >= 0 ? loa_155
																					: 0;
					try {
						result.put("C1", dwt_5000 >= 0 ? dwt_5000 : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("C2", dwt_5000_10000 >= 0 ? dwt_5000_10000
								: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("C3", dwt_10000_15000 >= 0 ? dwt_10000_15000
								: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("C4", dwt_15000_20000 >= 0 ? dwt_15000_20000
								: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("C5", dwt_20000 >= 0 ? dwt_20000 : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("C6", loa_145 >= 0 ? loa_145 : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("C7", loa_145_155 >= 0 ? loa_145_155 : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("C8", loa_155 >= 0 ? loa_155 : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put("A", tong);
					} catch (Exception e) {
						// nothing to do
					}
				}
			}

			return result;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONObject getModelMau13T(String maritimeCode, String startDate,
			String endDate, String objName) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			

			String query = "select MaritimeCode, sum(case when vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as tong_luot_tau, sum(case when VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_nn, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary_schedule.GT when vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary_schedule.GT else 0 end) as luot_tau_bien_nn_gt, sum(case when vma_itinerary.DocumentNameIN IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = '4')) AND vma_itinerary.DocumentYearIN IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = '4') ) AND vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.DocumentNameOUT IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = '5')) AND vma_itinerary.DocumentYearOUT IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = '5') ) AND vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 when vma_itinerary.DocumentNameIN =0 AND vma_itinerary.DocumentYearIN = 0 AND vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.DocumentNameOUT =0 AND vma_itinerary.DocumentYearOUT AND vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_nn_noi_dia, sum(case when vma_itinerary.DocumentNameIN IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC')) AND vma_itinerary.DocumentYearIN IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC') ) AND vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.DocumentNameOUT IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC')) AND vma_itinerary.DocumentYearOUT IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC') ) AND vma_itinerary.FlagStateOfShip <> 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_nn_xnc, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_vn, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary_schedule.GT when vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary_schedule.GT else 0 end) as luot_tau_bien_vn_gt, sum(case when vma_itinerary.DocumentNameIN IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = '4')) AND vma_itinerary.DocumentYearIN IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = '4') ) AND vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.DocumentNameOUT IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = '5')) AND vma_itinerary.DocumentYearOUT IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = '5') ) AND vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 when vma_itinerary.DocumentNameIN =0 AND vma_itinerary.DocumentYearIN = 0 AND vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.DocumentNameOUT =0 AND vma_itinerary.DocumentYearOUT AND vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_vn_noi_dia, sum(case when vma_itinerary.DocumentNameIN IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC')) AND vma_itinerary.DocumentYearIN IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC') ) AND vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.DocumentNameOUT IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC')) AND vma_itinerary.DocumentYearOUT IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC') ) AND vma_itinerary.FlagStateOfShip = 'VN' and VRCode in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_bien_vn_xnc, sum(case when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode not in ('VR','VRH','VRH1','VRH2','VRH3','VR-NA','VR200') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_pttnd, sum(case when VRCode in ('VR-SB') and vma_itinerary_schedule.NoticeShipType = '1' then 1 when VRCode in ('VR-SB') and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_pttnd_vrsb, sum(case when vma_itinerary.VRCode not in ('VR', 'VRH') and LastPortCode in (select PortCode from dm_port where PortType=0) then 1 when vma_itinerary.VRCode not in ('VR', 'VRH') and NextPortCode in (select PortCode from dm_port where PortType=0) then 1 else 0 end) as luot_pttnd_vr_sb_cb_cb, sum(case when vma_itinerary.VRCode not in ('VR', 'VRH') and LastPortCode in (select PortCode from dm_port where PortType=1) then 1 when vma_itinerary.VRCode not in ('VR', 'VRH') and NextPortCode in (select PortCode from dm_port where PortType=1) then 1 else 0 end) as hh_pttnd_vr_sb_cb_nd, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary.ShipTypeCode = 'T69' then 1 else 0 end) as luot_tau_khach_vn, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and vma_itinerary.ShipTypeCode = 'T69' then 1 else 0 end) as luot_tau_khach_nn, sum(case when vma_itinerary.LastPortCode in (select PortCode from dm_port where PortType=2) then 1 when vma_itinerary.NextPortCode in (select PortCode from dm_port where PortType=2) then 1 else 0 end) as luot_tau_tu_bo_ra_dao from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and MaritimeCode = "
					+ maritimeCode
					+ " AND (case when vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ " 23:59:59' when vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ " 00:00:00' and vma_itinerary.TimeOfDeparture <='"
					+ endDate + " 23:59:59' else null end)";

			log.info("=========select bao cao mau_69_13t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					int tong_luot_tau = Integer.valueOf(String
							.valueOf(objects[1] != null ? objects[1] : -1));
					int luot_tau_bien = Integer.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : -1));
					int luot_tau_bien_nn = Integer.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					double luot_tau_bien_nn_gt = Double.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					int luot_tau_bien_nn_noi_dia = Integer.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					int luot_tau_bien_nn_xnc = Integer.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					int luot_tau_bien_vn = Integer.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : -1));
					double luot_tau_bien_vn_gt = Double.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : -1));
					int luot_tau_bien_vn_noi_dia = Integer.valueOf(String
							.valueOf(objects[9] != null ? objects[9] : -1));
					int luot_tau_bien_vn_xnc = Integer.valueOf(String
							.valueOf(objects[10] != null ? objects[10] : -1));
					int luot_pttnd = Integer.valueOf(String
							.valueOf(objects[11] != null ? objects[11] : -1));
					int luot_pttnd_vrsb = Integer.valueOf(String
							.valueOf(objects[12] != null ? objects[12] : -1));
					int luot_pttnd_vr_sb_cb_cb = Integer.valueOf(String
							.valueOf(objects[13] != null ? objects[13] : -1));
					int hh_pttnd_vr_sb_cb_nd = Integer.valueOf(String
							.valueOf(objects[14] != null ? objects[14] : -1));
					int luot_tau_khach_vn = Integer.valueOf(String
							.valueOf(objects[15] != null ? objects[15] : -1));
					int luot_tau_khach_nn = Integer.valueOf(String
							.valueOf(objects[16] != null ? objects[16] : -1));
					int luot_tau_tu_bo_ra_dao = Integer.valueOf(String
							.valueOf(objects[17] != null ? objects[17] : -1));

					try {
						result.put(objName + "1",
								tong_luot_tau >= 0 ? tong_luot_tau : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(objName + "2",
								luot_tau_bien >= 0 ? luot_tau_bien : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(objName + "3",
								luot_tau_bien_nn >= 0 ? luot_tau_bien_nn : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(objName + "4",
								luot_tau_bien_nn_gt >= 0 ? luot_tau_bien_nn_gt
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								objName + "5",
								luot_tau_bien_nn_noi_dia >= 0 ? luot_tau_bien_nn_noi_dia
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								objName + "6",
								luot_tau_bien_nn_xnc >= 0 ? luot_tau_bien_nn_xnc
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(objName + "7",
								luot_tau_bien_vn >= 0 ? luot_tau_bien_vn : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(objName + "8",
								luot_tau_bien_vn_gt >= 0 ? luot_tau_bien_vn
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								objName + "9",
								luot_tau_bien_vn_noi_dia >= 0 ? luot_tau_bien_vn_noi_dia
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								objName + "10",
								luot_tau_bien_vn_xnc >= 0 ? luot_tau_bien_vn_xnc
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(objName + "11", luot_pttnd >= 0 ? luot_pttnd
								: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(objName + "12",
								luot_pttnd_vrsb >= 0 ? luot_pttnd_vrsb : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								objName + "13",
								luot_pttnd_vr_sb_cb_cb >= 0 ? luot_pttnd_vr_sb_cb_cb
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								objName + "14",
								hh_pttnd_vr_sb_cb_nd >= 0 ? hh_pttnd_vr_sb_cb_nd
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						int tong = BaoCaoBussinessUtils.sum2(luot_tau_khach_nn,
								luot_tau_khach_vn);
						result.put(objName + "15", tong >= 0 ? tong : null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(objName + "16",
								luot_tau_khach_vn >= 0 ? luot_tau_khach_vn
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(objName + "17",
								luot_tau_khach_nn >= 0 ? luot_tau_khach_nn
										: null);
					} catch (Exception e) {
						// nothing to do
					}
					try {
						result.put(
								objName + "18",
								luot_tau_tu_bo_ra_dao >= 0 ? luot_tau_tu_bo_ra_dao
										: null);
					} catch (Exception e) {
						// nothing to do
					}
				}
			}

			return result;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONObject getModelMau16T(String maritimeCode, String startDate,
			String endDate, String objName) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			

			String query = "select temp1.*, temp2.* from (select MaritimeCode, sum(case when vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as tong_luot_tau, sum(case when vma_itinerary_schedule.NoticeShipType = '1' then GT when vma_itinerary_schedule.NoticeShipType = '2' then GT else 0 end) as tong_GT, sum(case when vma_itinerary_schedule.NoticeShipType = '1' then DWT when vma_itinerary_schedule.NoticeShipType = '2' then DWT else 0 end) as tong_DWT, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_vn, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '1' then GT when vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '2' then GT else 0 end) as tong_GT_tau_vn, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '1' then DWT when vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '2' then DWT else 0 end) as tong_DWT_tau_vn, sum(case when vma_itinerary.DocumentNameIN IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC')) AND vma_itinerary.DocumentYearIN IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC') ) AND vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.DocumentNameOUT IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC')) AND vma_itinerary.DocumentYearOUT IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC') ) AND vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_vn_xnk, sum(case when vma_itinerary.DocumentNameIN IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC')) AND vma_itinerary.DocumentYearIN IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC') ) AND vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '1' then GT when vma_itinerary.DocumentNameOUT IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC')) AND vma_itinerary.DocumentYearOUT IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC') ) AND vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '2' then GT else 0 end) as tong_GT_tau_vn_xnk, sum(case when vma_itinerary.DocumentNameIN IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC')) AND vma_itinerary.DocumentYearIN IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'NC') ) AND vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '1' then DWT when vma_itinerary.DocumentNameOUT IN (SELECT temp_document.DocumentName FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC')) AND vma_itinerary.DocumentYearOUT IN (SELECT temp_document.DocumentYear FROM temp_document WHERE (temp_document.DocumentTypeCode = 'XC') ) AND vma_itinerary.FlagStateOfShip = 'VN' and vma_itinerary_schedule.NoticeShipType = '2' then DWT else 0 end) as tong_DWT_tau_vn_xnk, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and vma_itinerary_schedule.NoticeShipType = '1' then 1 when vma_itinerary.FlagStateOfShip <> 'VN' and vma_itinerary_schedule.NoticeShipType = '2' then 1 else 0 end) as luot_tau_nn, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and vma_itinerary_schedule.NoticeShipType = '1' then GT when vma_itinerary.FlagStateOfShip <> 'VN' and vma_itinerary_schedule.NoticeShipType = '2' then GT else 0 end) as tong_GT_tau_nn, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and vma_itinerary_schedule.NoticeShipType = '1' then DWT when vma_itinerary.FlagStateOfShip <> 'VN' and vma_itinerary_schedule.NoticeShipType = '2' then DWT else 0 end) as tong_DWT_tau_nn, sum(case when vma_itinerary_schedule.NoticeShipType = '1' then PassengerNumber when vma_itinerary_schedule.NoticeShipType = '2' then PassengerNumber else 0 end) as luot_khach from vma_itinerary_schedule inner join vma_itinerary on vma_itinerary_schedule.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and MaritimeCode = "
					+ maritimeCode
					+ " AND VRCode in ('VR','VRH') and (case when vma_itinerary_schedule.NoticeShipType = '1' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when vma_itinerary_schedule.NoticeShipType = '2' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' else null end)) as temp1 inner join (select MaritimeCode as MaritimeCode_, sum(case vma_schedule_cargolist.CargoCategory when 'C2_DO' then Quantity else 0 end) as tong_nhap_tan, sum(case vma_schedule_cargolist.CargoCategory when 'C2_DO' then TeusQuantity else 0 end) as tong_nhap_teus, sum(case vma_schedule_cargolist.CargoCategory when 'C1_XEP' then Quantity else 0 end) as tong_xuat_tan, sum(case vma_schedule_cargolist.CargoCategory when 'C1_XEP' then TeusQuantity else 0 end) as tong_xuat_teus, sum(case vma_schedule_cargolist.CargoCategory when 'C3_XEP' then Quantity when 'C3_DO' then Quantity else 0 end) as tong_noi_dia_tan, sum(case vma_schedule_cargolist.CargoCategory when 'C3_XEP' then TeusQuantity when 'C3_DO' then TeusQuantity else 0 end) as tong_noi_dia_teus, sum(case when vma_schedule_cargolist.CargoCategory = 'C2_DO' and vma_schedule_cargolist.CargoType =9 then Quantity else 0 end) as hang_container_nhap, sum(case when vma_schedule_cargolist.CargoCategory = 'C1_XEP' and vma_schedule_cargolist.CargoType =9 then Quantity else 0 end) as hang_container_xuat, sum(case when vma_schedule_cargolist.CargoCategory = 'C3_XEP' and vma_schedule_cargolist.CargoType =9 then Quantity when vma_schedule_cargolist.CargoCategory = 'C3_DO' and vma_schedule_cargolist.CargoType =9 then Quantity else 0 end) as hang_container_noi_dia, sum(case when vma_schedule_cargolist.CargoCategory = 'C2_DO' and vma_schedule_cargolist.CargoType =9 then TeusQuantity else 0 end) as hang_container_teus_nhap, sum(case when vma_schedule_cargolist.CargoCategory = 'C1_XEP' and vma_schedule_cargolist.CargoType =9 then TeusQuantity else 0 end) as hang_container_teus_xuat, sum(case when vma_schedule_cargolist.CargoCategory = 'C3_XEP' and vma_schedule_cargolist.CargoType =9 then TeusQuantity when vma_schedule_cargolist.CargoCategory = 'C3_DO' and vma_schedule_cargolist.CargoType =9 then TeusQuantity else 0 end) as hang_container_teus_noi_dia, sum(case when vma_schedule_cargolist.CargoCategory = 'C2_DO' and vma_schedule_cargolist.CargoType =3 then Quantity else 0 end) as hang_long_nhap, sum(case when vma_schedule_cargolist.CargoCategory = 'C1_XEP' and vma_schedule_cargolist.CargoType =3 then Quantity else 0 end) as hang_long_xuat, sum(case when vma_schedule_cargolist.CargoCategory = 'C3_XEP' and vma_schedule_cargolist.CargoType =3 then Quantity when vma_schedule_cargolist.CargoCategory = 'C3_DO' and vma_schedule_cargolist.CargoType =3 then Quantity else 0 end) as hang_long_noi_dia, sum(case when vma_schedule_cargolist.CargoCategory = 'C2_DO' and vma_schedule_cargolist.CargoType =12 then Quantity else 0 end) as hang_kho_nhap, sum(case when vma_schedule_cargolist.CargoCategory = 'C1_XEP' and vma_schedule_cargolist.CargoType =12 then Quantity else 0 end) as hang_kho_xuat, sum(case when vma_schedule_cargolist.CargoCategory = 'C3_XEP' and vma_schedule_cargolist.CargoType =12 then Quantity when vma_schedule_cargolist.CargoCategory = 'C3_DO' and vma_schedule_cargolist.CargoType =12 then Quantity else 0 end) as hang_kho_noi_dia, sum(case when vma_schedule_cargolist.CargoCategory in ('C5', 'C4_DO', 'C4_XEP') then Quantity else 0 end) as hang_qua_canh from vma_schedule_cargolist inner join vma_itinerary on vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and vma_itinerary.VRCode in ('VR', 'VRH') and MaritimeCode = "
					+ maritimeCode
					+ " and (case vma_schedule_cargolist.CargoCategory when 'C2_DO' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ "' when 'C3_DO' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ "' when 'C4_DO' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ "' when 'C5' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ "' when 'C1_XEP' then vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ "' when 'C3_XEP' then vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ "' when 'C4_XEP' then vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ "' else null end)) as temp2 on temp1.MaritimeCode = temp2.MaritimeCode_";

			log.info("=========select bao cao mau_16t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					double tong_luot_tau = Double.valueOf(String
							.valueOf(objects[1] != null ? objects[1] : -1));
					double tong_gt = Double.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : -1));
					double tong_dwt = Double.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					double luot_tau_vn = Double.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					double tong_gt_tau_vn = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					double tong_dwt_tau_vn = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					double luot_tau_vn_xnk = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : -1));
					double tong_gt_tau_vn_xnk = Double.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : -1));
					double tong_dwt_tau_vn_xnk = Double.valueOf(String
							.valueOf(objects[9] != null ? objects[9] : -1));
					double luot_tau_nn = Double.valueOf(String
							.valueOf(objects[10] != null ? objects[10] : -1));
					double tong_gt_tau_nn = Double.valueOf(String
							.valueOf(objects[11] != null ? objects[11] : -1));
					double tong_dwt_tau_nn = Double.valueOf(String
							.valueOf(objects[12] != null ? objects[12] : -1));
					double luot_khach = Double.valueOf(String
							.valueOf(objects[13] != null ? objects[13] : -1));
					double tong_nhap_tan = Double.valueOf(String
							.valueOf(objects[15] != null ? objects[15] : -1));
					double tong_nhap_teus = Double.valueOf(String
							.valueOf(objects[16] != null ? objects[16] : -1));
					double tong_xuat_tan = Double.valueOf(String
							.valueOf(objects[17] != null ? objects[17] : -1));
					double tong_xuat_teus = Double.valueOf(String
							.valueOf(objects[18] != null ? objects[18] : -1));
					double tong_noi_dia_tan = Double.valueOf(String
							.valueOf(objects[19] != null ? objects[19] : -1));
					double tong_noi_dia_teus = Double.valueOf(String
							.valueOf(objects[20] != null ? objects[20] : -1));
					double hang_container_nhap = Double.valueOf(String
							.valueOf(objects[21] != null ? objects[21] : -1));
					double hang_container_xuat = Double.valueOf(String
							.valueOf(objects[22] != null ? objects[22] : -1));
					double hang_container_noi_dia = Double.valueOf(String
							.valueOf(objects[23] != null ? objects[23] : -1));
					double hang_container_teus_nhap = Double.valueOf(String
							.valueOf(objects[24] != null ? objects[24] : -1));
					double hang_container_teus_xuat = Double.valueOf(String
							.valueOf(objects[25] != null ? objects[25] : -1));
					double hang_container_teus_noi_dia = Double.valueOf(String
							.valueOf(objects[26] != null ? objects[26] : -1));
					double hang_long_nhap = Double.valueOf(String
							.valueOf(objects[27] != null ? objects[27] : -1));
					double hang_long_xuat = Double.valueOf(String
							.valueOf(objects[28] != null ? objects[28] : -1));
					double hang_long_noi_dia = Double.valueOf(String
							.valueOf(objects[29] != null ? objects[29] : -1));
					double hang_kho_nhap = Double.valueOf(String
							.valueOf(objects[30] != null ? objects[30] : -1));
					double hang_kho_xuat = Double.valueOf(String
							.valueOf(objects[31] != null ? objects[31] : -1));
					double hang_kho_noi_dia = Double.valueOf(String
							.valueOf(objects[32] != null ? objects[32] : -1));
					double hang_qua_canh = Double.valueOf(String
							.valueOf(objects[33] != null ? objects[33] : -1));

					result.put(objName + "1",
							tong_luot_tau >= 0 ? tong_luot_tau : null);
					result.put(objName + "2", tong_gt >= 0 ? tong_gt : null);
					result.put(objName + "3", tong_dwt >= 0 ? tong_dwt : null);
					result.put(objName + "4",
							tong_gt_tau_vn >= 0 ? tong_gt_tau_vn : null);
					result.put(objName + "5",
							tong_dwt_tau_vn >= 0 ? tong_dwt_tau_vn : null);
					result.put(objName + "6",
							luot_tau_vn_xnk >= 0 ? luot_tau_vn_xnk : null);
					result.put(objName + "7",
							tong_gt_tau_vn_xnk >= 0 ? tong_gt_tau_vn_xnk : null);
					result.put(objName + "8",
							tong_dwt_tau_vn_xnk >= 0 ? tong_dwt_tau_vn_xnk
									: null);
					result.put(objName + "9", luot_tau_nn >= 0 ? luot_tau_nn
							: null);
					result.put(objName + "10",
							tong_gt_tau_nn >= 0 ? tong_gt_tau_nn : null);
					result.put(objName + "11",
							tong_dwt_tau_nn >= 0 ? tong_dwt_tau_nn : null);
					result.put(objName + "12",
							hang_container_xuat >= 0 ? hang_container_xuat
									: null);
					result.put(objName + "13",
							hang_container_nhap >= 0 ? hang_container_nhap
									: null);
					result.put(
							objName + "14",
							hang_container_noi_dia >= 0 ? hang_container_noi_dia
									: null);
					result.put(
							objName + "15",
							hang_container_teus_xuat >= 0 ? hang_container_teus_xuat
									: null);
					result.put(
							objName + "16",
							hang_container_teus_nhap >= 0 ? hang_container_teus_nhap
									: null);
					result.put(
							objName + "17",
							hang_container_teus_noi_dia >= 0 ? hang_container_teus_noi_dia
									: null);
					result.put(objName + "18",
							hang_long_xuat >= 0 ? hang_long_xuat : null);
					result.put(objName + "19",
							hang_long_nhap >= 0 ? hang_long_nhap : null);
					result.put(objName + "20",
							hang_long_noi_dia >= 0 ? hang_long_noi_dia : null);
					result.put(objName + "21",
							hang_kho_xuat >= 0 ? hang_kho_xuat : null);
					result.put(objName + "22",
							hang_kho_nhap >= 0 ? hang_kho_nhap : null);
					result.put(objName + "23",
							hang_kho_noi_dia >= 0 ? hang_kho_noi_dia : null);
					result.put(objName + "24",
							hang_qua_canh >= 0 ? hang_qua_canh : null);
					result.put(objName + "25", luot_khach >= 0 ? luot_khach
							: null);
				}
			}

			return result;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray getModelMau46_47_48_52_71_72_73_79T(String query)
			throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			log.info("=========select bao cao mau_46_47_48_52_71_72_73_79t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				int i = 1;
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					JSONObject result = JSONFactoryUtil.createJSONObject();

					String itineraryNo = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String nameOfShip = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String registryNumber = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String vrCode = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String shipTypeName = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					double loa = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : null));
					double dwt = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : null));
					double gt = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : null));
					int luot_khach_den = Integer.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : null));
					int luot_khach_roi = Integer.valueOf(String
							.valueOf(objects[9] != null ? objects[9] : null));
					String cang_roi = String
							.valueOf(objects[10] != null ? objects[10]
									: StringPool.BLANK);
					String cang_den = String
							.valueOf(objects[11] != null ? objects[11]
									: StringPool.BLANK);
					String cang_dich = String
							.valueOf(objects[12] != null ? objects[12]
									: StringPool.BLANK);
					String dai_ly_tau = String
							.valueOf(objects[13] != null ? objects[13]
									: StringPool.BLANK);
					String ngay_den = String
							.valueOf(objects[14] != null ? objects[14]
									: StringPool.BLANK);
					String ngay_roi = String
							.valueOf(objects[15] != null ? objects[15]
									: StringPool.BLANK);
					String ten_hang_den = String
							.valueOf(objects[16] != null ? objects[16]
									: StringPool.BLANK);
					String ten_hang_roi = String
							.valueOf(objects[17] != null ? objects[17]
									: StringPool.BLANK);

					result.put("A", i);
					result.put("B", nameOfShip);
					result.put("C", StringPool.BLANK);
					result.put("D", StringPool.BLANK);
					result.put("E", StringPool.BLANK);
					result.put("F", shipTypeName);
					result.put("G", loa >= 0 ? loa : null);
					result.put("H", dwt >= 0 ? dwt : null);
					result.put("I", gt >= 0 ? gt : null);
					result.put("K", ngay_den);
					result.put("L", ngay_roi);
					result.put("M", cang_roi);
					result.put("N", cang_dich);
					result.put("O", dai_ly_tau);
					result.put("P", StringPool.BLANK);
					result.put("Q", ten_hang_den);
					result.put("R", ten_hang_roi);

					results.put(result);
					i++;
				}
			}

			return results;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray getModelMau49_50_51T(String maritimeCode,
			String nameOfShip, String imoNumber, String registryNumber,
			String vrCode, String flagStateOfShip, String from_gt,
			String to_gt, String from_dwt, String to_dwt, String from_loa,
			String to_loa, String lastPortCode, String nextPortCode,
			String arrivalShipAgency, String departureShipAgency,
			String cargoType, String cargoCategory, String callSign,
			String startDate, String endDate) throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "select temp3.*, GROUP_CONCAT(DISTINCT temp4.ten_hang_den SEPARATOR ', ') as ten_hang_den, GROUP_CONCAT(DISTINCT temp4.ten_hang_roi SEPARATOR ', ') as ten_hang_roi from (select temp1.ItineraryNo, temp1.NameOfShip, temp1.RegistryNumber, temp1.VRCode, temp1.ShipTypeName, temp1.StateName, temp1.LOA, temp1.DWT, temp1.GT, (case when temp1.LastPortCode is not null and temp1.LastPortCode <> '' then (select PortName from dm_port where PortCode = temp1.LastPortCode) else '' end) as cang_roi, (case when temp1.ArrivalPortCode is not null and temp1.ArrivalPortCode <> '' then (select PortName from dm_port where PortCode = temp1.ArrivalPortCode) else '' end) as cang_den, (case when temp1.NextPortCode is not null and temp1.NextPortCode <> '' then (select PortName from dm_port where PortCode = temp1.NextPortCode) else '' end) as cang_dich, temp1.dai_ly_den, temp1.dai_ly_roi, temp1.ngay_den, temp2.ngay_roi from (select vma_itinerary.ItineraryNo as ItineraryNo, vma_itinerary.NameOfShip as NameOfShip, vma_itinerary.RegistryNumber as RegistryNumber, vma_itinerary.VRCode as VRCode, dm_ship_type.ShipTypeName as ShipTypeName, dm_state.StateName as StateName, vma_itinerary_schedule.LOA as LOA , vma_itinerary_schedule.DWT as DWT , vma_itinerary_schedule.GT as GT, vma_itinerary.LastPortCode as LastPortCode, vma_itinerary.ArrivalPortCode as ArrivalPortCode, vma_itinerary.NextPortCode as NextPortCode, vma_itinerary.TimeOfArrival as ngay_den, vma_itinerary.ArrivalShipAgency as dai_ly_den, vma_itinerary.DepartureShipAgency as dai_ly_roi from vma_itinerary inner join dm_ship_type on vma_itinerary.ShipTypeCode = dm_ship_type.ShipTypeCode inner join vma_itinerary_schedule on vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo inner join dm_state on dm_state.StateCode = vma_itinerary.FlagStateOfShip where vma_itinerary.VRCode not in ('VR', 'VRH') and vma_itinerary_schedule.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.MaritimeCode = "
					+ maritimeCode
					+ " AND vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' AND (case when '"
					+ nameOfShip
					+ "' <> '' then vma_itinerary.NameOfShip like concat('%','"
					+ nameOfShip
					+ "','%') else 1 end) AND (case when '"
					+ imoNumber
					+ "' <> '' then vma_itinerary.IMONumber like concat('%','"
					+ imoNumber
					+ "','%') else 1 end) AND (case when '"
					+ callSign
					+ "' <> '' then vma_itinerary.CallSign like concat('%','"
					+ callSign
					+ "','%') else 1 end) AND (case when '"
					+ registryNumber
					+ "' <> '' then vma_itinerary.RegistryNumber like concat('%','"
					+ registryNumber
					+ "','%') else 1 end) AND (case when '"
					+ vrCode
					+ "' <> '' then vma_itinerary.VRCode = '"
					+ vrCode
					+ "' else 1 end) AND (case when '"
					+ flagStateOfShip
					+ "' <> '' then vma_itinerary.FlagStateOfShip = '"
					+ flagStateOfShip
					+ "' else 1 end) AND (case when '"
					+ from_gt
					+ "' <> '' then vma_itinerary_schedule.GT >= '"
					+ from_gt
					+ "' else 1 end) AND (case when '"
					+ to_gt
					+ "' <> '' then vma_itinerary_schedule.GT <= '"
					+ to_gt
					+ "' else 1 end) AND (case when '"
					+ from_dwt
					+ "' <> '' then vma_itinerary_schedule.DWT >= '"
					+ from_dwt
					+ "' else 1 end) AND (case when '"
					+ to_dwt
					+ "' <> '' then vma_itinerary_schedule.DWT <= '"
					+ to_dwt
					+ "' else 1 end) AND (case when '"
					+ from_loa
					+ "' <> '' then vma_itinerary_schedule.LOA >= '"
					+ from_loa
					+ "' else 1 end) AND (case when '"
					+ to_loa
					+ "' <> '' then vma_itinerary_schedule.LOA <= '"
					+ to_loa
					+ "' else 1 end) AND (case when '"
					+ lastPortCode
					+ "' <> '' then vma_itinerary.LastPortCode = '"
					+ lastPortCode
					+ "' else 1 end) AND (case when '"
					+ nextPortCode
					+ "' <> '' then vma_itinerary.NextPortCode = '"
					+ nextPortCode
					+ "' else 1 end) AND (case when '"
					+ arrivalShipAgency
					+ "' <> '' then vma_itinerary.ArrivalShipAgency = '"
					+ arrivalShipAgency
					+ "' else 1 end) AND (case when '"
					+ departureShipAgency
					+ "' <> '' then vma_itinerary.DepartureShipAgency = '"
					+ departureShipAgency
					+ "' else 1 end) group by vma_itinerary.ItineraryNo ) as temp1 inner join (select vma_itinerary.ItineraryNo as ItineraryNo , vma_itinerary_schedule.TimeOfDeparture as ngay_roi from vma_itinerary inner join vma_itinerary_schedule on vma_itinerary.ItineraryNo = vma_itinerary_schedule.ItineraryNo where vma_itinerary.VRCode not in ('VR', 'VRH') and vma_itinerary_schedule.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.MaritimeCode = "
					+ maritimeCode
					+ " AND vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' group by vma_itinerary.ItineraryNo ) as temp2 on temp1.ItineraryNo = temp2.ItineraryNo ) as temp3 inner join ( select vma_itinerary.ItineraryNo as ItineraryNo, (case when vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) then dm_dataitem.name else null end) as ten_hang_den, (case when vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) then dm_dataitem.name else null end) as ten_hang_roi from vma_itinerary left join (vma_schedule_cargolist inner join dm_dataitem on dm_dataitem.code = vma_schedule_cargolist.CargoCode and dm_dataitem.datagroupid = '124') on vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO', 'C5', 'C4_DO') and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP', 'C4_XEP') and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and MaritimeCode = "
					+ maritimeCode
					+ " and (case vma_schedule_cargolist.CargoCategory when 'C2_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C3_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C4_DO' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C5' then vma_itinerary.TimeOfArrival >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <='"
					+ endDate
					+ "' when 'C1_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' when 'C3_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' when 'C4_XEP' then vma_itinerary.TimeOfDeparture >='"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <='"
					+ endDate
					+ "' else null end) AND (case when '"
					+ cargoType
					+ "' <> '' then vma_schedule_cargolist.CargoType = '"
					+ cargoType
					+ "' else 1 end) AND (case when '"
					+ cargoCategory
					+ "' <> '' then vma_schedule_cargolist.CargoCategory = '"
					+ cargoCategory
					+ "' else 1 end) ) as temp4 on temp3.ItineraryNo = temp4.ItineraryNo group by temp3.ItineraryNo";

			log.info("=========select bao cao mau49_50_51t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				int i = 1;
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					JSONObject result = JSONFactoryUtil.createJSONObject();

					String itineraryNo = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					nameOfShip = String.valueOf(objects[1] != null ? objects[1]
							: StringPool.BLANK);
					registryNumber = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					vrCode = String.valueOf(objects[3] != null ? objects[3]
							: StringPool.BLANK);
					String shipTypeName = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					String stateName = String
							.valueOf(objects[5] != null ? objects[5]
									: StringPool.BLANK);
					double loa = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : null));
					double dwt = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : null));
					double gt = Double.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : null));
					String cang_roi = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String cang_den = String
							.valueOf(objects[10] != null ? objects[10]
									: StringPool.BLANK);
					String cang_dich = String
							.valueOf(objects[11] != null ? objects[11]
									: StringPool.BLANK);
					String dai_ly_den = String
							.valueOf(objects[12] != null ? objects[12]
									: StringPool.BLANK);
					String dai_ly_roi = String
							.valueOf(objects[13] != null ? objects[13]
									: StringPool.BLANK);
					String ngay_den = String
							.valueOf(objects[14] != null ? objects[14]
									: StringPool.BLANK);
					String ngay_roi = String
							.valueOf(objects[15] != null ? objects[15]
									: StringPool.BLANK);
					String ten_hang_den = String
							.valueOf(objects[16] != null ? objects[16]
									: StringPool.BLANK);
					String ten_hang_roi = String
							.valueOf(objects[17] != null ? objects[17]
									: StringPool.BLANK);

					result.put("A", i);
					result.put("B", nameOfShip);
					result.put("C", StringPool.BLANK);
					result.put("D", loa >= 0 ? loa : null);
					result.put("E", StringPool.BLANK);
					result.put("F", stateName);
					result.put("G", shipTypeName);
					result.put("H", ngay_roi);
					result.put("I", dai_ly_roi);
					result.put("K", dai_ly_den);
					result.put("L", cang_dich);
					result.put("M", cang_roi);
					result.put("N", cang_dich);
					result.put("O", dwt >= 0 ? dwt : null);
					result.put("P", gt >= 0 ? gt : null);
					result.put("Q", ngay_den);
					result.put("R", ten_hang_roi);
					result.put("S", ten_hang_den);

					results.put(result);
					i++;
				}
			}

			return results;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray getModelMauBC15T(String maritimeCode,
			String cargoTypeContainer, String cargoTypeKho,
			String cargoTypeLong, String portHarbourCode, String startDate,
			String endDate) throws SystemException {

		JSONArray results = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "select temp2.PortHarbourName as PortHarbourName, temp1.* from ( select MaritimeCode, vma_schedule_cargolist.PortHarbourCode as PortHarbourCode,vma_schedule_cargolist.CargoType as CargoType, sum(Quantity) as tong_so_tan, sum(TeusQuantity) as tong_so_teus, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and vma_schedule_cargolist.CargoCategory = 'C1_XEP' then Quantity else 0 end) as tong_xuat_tau_noi, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and vma_schedule_cargolist.CargoCategory = 'C1_XEP' then TeusQuantity else 0 end) as tong_xuat_tau_noi_teus, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and vma_schedule_cargolist.CargoCategory = 'C1_XEP' then Quantity else 0 end) as tong_xuat_tau_ngoai, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and vma_schedule_cargolist.CargoCategory = 'C1_XEP' then TeusQuantity else 0 end) as tong_xuat_tau_ngoai_teus, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and vma_schedule_cargolist.CargoCategory = 'C2_DO' then Quantity else 0 end) as tong_nhap_tau_noi, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and vma_schedule_cargolist.CargoCategory = 'C2_DO' then TeusQuantity else 0 end) as tong_nhap_tau_noi_teus, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and vma_schedule_cargolist.CargoCategory = 'C2_DO' then Quantity else 0 end) as tong_nhap_tau_ngoai, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and vma_schedule_cargolist.CargoCategory = 'C2_DO' then TeusQuantity else 0 end) as tong_nhap_tau_ngoai_teus, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and vma_schedule_cargolist.CargoCategory = 'C3_XEP' then Quantity when vma_itinerary.FlagStateOfShip = 'VN' and vma_schedule_cargolist.CargoCategory = 'C3_DO' then Quantity else 0 end) as noi_dia_tau_noi, sum(case when vma_itinerary.FlagStateOfShip = 'VN' and vma_schedule_cargolist.CargoCategory = 'C3_XEP' then TeusQuantity when vma_itinerary.FlagStateOfShip = 'VN' and vma_schedule_cargolist.CargoCategory = 'C3_DO' then TeusQuantity else 0 end) as noi_dia_tau_noi_teus, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and vma_schedule_cargolist.CargoCategory = 'C3_XEP' then Quantity when vma_itinerary.FlagStateOfShip <> 'VN' and vma_schedule_cargolist.CargoCategory = 'C3_DO' then Quantity else 0 end) as noi_dia_tau_ngoai, sum(case when vma_itinerary.FlagStateOfShip <> 'VN' and vma_schedule_cargolist.CargoCategory = 'C3_XEP' then TeusQuantity when vma_itinerary.FlagStateOfShip <> 'VN' and vma_schedule_cargolist.CargoCategory = 'C3_DO' then TeusQuantity else 0 end) as noi_dia_tau_ngoai_teus from vma_schedule_cargolist inner join vma_itinerary on vma_schedule_cargolist.ItineraryNo = vma_itinerary.ItineraryNo where ( (vma_schedule_cargolist.CargoCategory in ('C2_DO', 'C3_DO') and vma_schedule_cargolist.NoticeShipType = '1' and vma_itinerary.MarkedAsArrival not in (0,1,6) and vma_itinerary.LastPortCode is not null) or (vma_schedule_cargolist.CargoCategory in ('C1_XEP', 'C3_XEP') and vma_schedule_cargolist.NoticeShipType = '2' and vma_itinerary.MarkedAsDeparture not in (0,1,6) and vma_itinerary.NextPortCode is not null) ) and MaritimeCode = '"
					+ maritimeCode
					+ "' and (case vma_schedule_cargolist.CargoCategory when 'C2_DO' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ "' when 'C3_DO' then vma_itinerary.TimeOfArrival >= '"
					+ startDate
					+ "' and vma_itinerary.TimeOfArrival <= '"
					+ endDate
					+ "' when 'C1_XEP' then vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ "' when 'C3_XEP' then vma_itinerary.TimeOfDeparture >= '"
					+ startDate
					+ "' and vma_itinerary.TimeOfDeparture <= '"
					+ endDate
					+ "' else null end) and vma_schedule_cargolist.CargoType in ('"
					+ cargoTypeContainer
					+ "','"
					+ cargoTypeKho
					+ "','"
					+ cargoTypeLong
					+ "') and (case when '"
					+ portHarbourCode
					+ "' <> '' then vma_schedule_cargolist.PortHarbourCode = '"
					+ portHarbourCode
					+ "' else 1 end) group by CargoType, PortHarbourCode ) as temp1 inner join dm_port_harbour as temp2 on temp1.PortHarbourCode = temp2.PortHarbourCode and temp2.PortRegionCode = "
					+ maritimeCode;

			log.info("=========select bao cao mauBC15t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				int i = 1;
				double tong_tong_so_tan = 0, tong_tong_so_teus = 0, tong_xuat_noi = 0, tong_xuat_noi_teus = 0, tong_xuat_ngoai = 0, tong_xuat_ngoai_teus = 0, tong_nhap_noi = 0, tong_nhap_noi_teus = 0, tong_nhap_ngoai = 0, tong_nhap_ngoai_teus = 0, tong_noi_dia_noi = 0, tong_noi_dia_noi_teus = 0, tong_noi_dia_ngoai = 0, tong_noi_dia_ngoai_teus = 0;
				JSONObject tong = JSONFactoryUtil.createJSONObject();
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					JSONObject result = JSONFactoryUtil.createJSONObject();

					String portHarbourName = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					maritimeCode = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					portHarbourCode = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					int cargoType = Integer.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					double tong_so_tan = Double.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : null));
					double tong_so_teus = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : null));
					double tong_xuat_tau_noi = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : null));
					double tong_xuat_tau_noi_teus = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : null));
					double tong_xuat_tau_ngoai = Double.valueOf(String
							.valueOf(objects[8] != null ? objects[8] : null));
					double tong_xuat_tau_ngoai_teus = Double.valueOf(String
							.valueOf(objects[9] != null ? objects[9] : null));
					double tong_nhap_tau_noi = Double.valueOf(String
							.valueOf(objects[10] != null ? objects[10] : null));
					double tong_nhap_tau_noi_teus = Double.valueOf(String
							.valueOf(objects[11] != null ? objects[11] : null));
					double tong_nhap_tau_ngoai = Double.valueOf(String
							.valueOf(objects[12] != null ? objects[12] : null));
					double tong_nhap_tau_ngoai_teus = Double.valueOf(String
							.valueOf(objects[13] != null ? objects[13] : null));
					double noi_dia_tau_noi = Double.valueOf(String
							.valueOf(objects[14] != null ? objects[14] : null));
					double noi_dia_tau_noi_teus = Double.valueOf(String
							.valueOf(objects[15] != null ? objects[15] : null));
					double noi_dia_tau_ngoai = Double.valueOf(String
							.valueOf(objects[16] != null ? objects[16] : null));
					double noi_dia_tau_ngoai_teus = Double.valueOf(String
							.valueOf(objects[17] != null ? objects[17] : null));

					result.put("A", i);
					result.put("B", portHarbourName);
					result.put("C", tong_so_tan >= 0 ? tong_so_tan : null);
					tong_tong_so_tan += tong_so_tan >= 0 ? tong_so_tan : 0;
					result.put("D", tong_so_teus >= 0 ? tong_so_teus : null);
					tong_tong_so_teus += tong_so_teus >= 0 ? tong_so_teus : 0;
					result.put("E", tong_xuat_tau_noi >= 0 ? tong_xuat_tau_noi
							: null);
					tong_xuat_noi += tong_xuat_tau_noi >= 0 ? tong_xuat_tau_noi
							: 0;
					result.put(
							"F",
							tong_xuat_tau_noi_teus >= 0 ? tong_xuat_tau_noi_teus
									: null);
					tong_xuat_noi_teus += tong_xuat_tau_noi_teus >= 0 ? tong_xuat_tau_noi_teus
							: 0;
					result.put("G",
							tong_xuat_tau_ngoai >= 0 ? tong_xuat_tau_ngoai
									: null);
					tong_xuat_ngoai += tong_xuat_tau_ngoai >= 0 ? tong_xuat_tau_ngoai
							: 0;
					result.put(
							"H",
							tong_xuat_tau_ngoai_teus >= 0 ? tong_xuat_tau_ngoai_teus
									: null);
					tong_xuat_ngoai_teus += tong_xuat_tau_ngoai_teus >= 0 ? tong_xuat_tau_ngoai_teus
							: 0;
					result.put("I", tong_nhap_tau_noi >= 0 ? tong_nhap_tau_noi
							: null);
					tong_nhap_noi += tong_nhap_tau_noi >= 0 ? tong_nhap_tau_noi
							: 0;
					result.put(
							"K",
							tong_nhap_tau_noi_teus >= 0 ? tong_nhap_tau_noi_teus
									: null);
					tong_nhap_noi_teus += tong_nhap_tau_noi_teus >= 0 ? tong_nhap_tau_noi_teus
							: 0;
					result.put("L",
							tong_nhap_tau_ngoai >= 0 ? tong_nhap_tau_ngoai
									: null);
					tong_nhap_ngoai += tong_nhap_tau_ngoai >= 0 ? tong_nhap_tau_ngoai
							: 0;
					result.put(
							"M",
							tong_nhap_tau_ngoai_teus >= 0 ? tong_nhap_tau_ngoai_teus
									: null);
					tong_nhap_ngoai_teus += tong_nhap_tau_ngoai_teus >= 0 ? tong_nhap_tau_ngoai_teus
							: 0;
					result.put("N", noi_dia_tau_noi >= 0 ? noi_dia_tau_noi
							: null);
					tong_noi_dia_noi += noi_dia_tau_noi >= 0 ? noi_dia_tau_noi
							: 0;
					result.put("O",
							noi_dia_tau_noi_teus >= 0 ? noi_dia_tau_noi_teus
									: null);
					tong_noi_dia_noi_teus += noi_dia_tau_noi_teus >= 0 ? noi_dia_tau_noi_teus
							: 0;
					result.put("P", noi_dia_tau_ngoai >= 0 ? noi_dia_tau_ngoai
							: null);
					tong_noi_dia_ngoai += noi_dia_tau_ngoai >= 0 ? noi_dia_tau_ngoai
							: 0;
					result.put(
							"Q",
							noi_dia_tau_ngoai_teus >= 0 ? noi_dia_tau_ngoai_teus
									: null);
					tong_noi_dia_ngoai_teus += noi_dia_tau_ngoai_teus >= 0 ? noi_dia_tau_ngoai_teus
							: 0;

					results.put(result);
					i++;
				}
				tong.put("B", DanhMucUtils.encodeUTF8("Tổng số"));
				tong.put("C", tong_tong_so_tan);
				tong.put("D", tong_tong_so_teus);
				tong.put("E", tong_xuat_noi);
				tong.put("F", tong_xuat_noi_teus);
				tong.put("G", tong_xuat_ngoai);
				tong.put("H", tong_xuat_ngoai_teus);
				tong.put("I", tong_nhap_noi);
				tong.put("K", tong_nhap_noi_teus);
				tong.put("L", tong_nhap_ngoai);
				tong.put("M", tong_nhap_ngoai_teus);
				tong.put("N", tong_noi_dia_noi);
				tong.put("O", tong_noi_dia_noi_teus);
				tong.put("P", tong_noi_dia_ngoai);
				tong.put("Q", tong_noi_dia_ngoai_teus);
				results.put(tong);
			}

			return results;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray dsNoiChuyen(String shipPosition, String imoNumber,
			String callSign, String registryNumber, String nameOfShip,
			String documentNameIN, String timeOfArrival, int start, int end)
			throws SystemException {
		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {

			

			StringBuilder query = new StringBuilder();

			query.append(" SELECT DISTINCT A1.NameOfShip, A1.FlagStateOfShip, A1.IMONumber, A1.CallSign, A1.VRCode, A1.RegistryNumber, A1.DocumentNameIN, A1.DocumentYearIN, A1.DocumentNameOUT, A1.DocumentYearOUT, A1.DocumentNameVOY, A1.DocumentYearVOY, A1.MarkedAsArrival, A1.TimeOfArrival, A1.TimeOfDeparture, A1.MarkedAsDeparture, A7.ShipBoat, A1.ItineraryNo FROM vma_itinerary AS A1 LEFT JOIN vma_ship AS A7 ON ((( A1.CallSign = A7.CallSign AND A1.CallSign IS NOT NULL ) AND ( A1.IMONumber = A7.IMONumber AND A1.IMONumber IS NOT NULL ) ) OR ( A1.RegistryNumber = A7.RegistryNumber AND A1.RegistryNumber IS NOT NULL )) WHERE 1 = 1 ");

			if (Validator.isNotNull(shipPosition) && !shipPosition.isEmpty()) {
				query.append(" AND A1.ShipPosition = " + shipPosition);
			}
			if (Validator.isNotNull(callSign) && !callSign.isEmpty()) {
				query.append(" AND A1.CallSign = '" + callSign + "'");
			}
			if (Validator.isNotNull(imoNumber) && !imoNumber.isEmpty()) {
				query.append(" AND A1.IMONumber = '" + imoNumber + "'");
			}
			if (Validator.isNotNull(registryNumber)
					&& !registryNumber.isEmpty()) {
				query.append(" AND A1.RegistryNumber = '" + registryNumber
						+ "'");
			}
			if (Validator.isNotNull(nameOfShip) && !nameOfShip.isEmpty()) {
				query.append(" AND A1.NameOfShip LIKE '%" + nameOfShip + "%'");
			}
			if (Validator.isNotNull(documentNameIN)
					&& !documentNameIN.isEmpty()) {
				query.append(" AND A1.DocumentNameIN = " + documentNameIN);
			}
			if (Validator.isNotNull(timeOfArrival) && !timeOfArrival.isEmpty()) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(timeOfArrival);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					query.append(" AND (A1.TimeOfArrival BETWEEN '" + strDate
							+ " 00:00:00'");

					query.append(" AND '" + strDate + " 23:59:59' ) )");
				}
			}

			log.info("=========select ds noi chuyen >>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					nameOfShip = String.valueOf(objects[0]);
					String flagStateOfShip = String.valueOf(objects[1]);
					imoNumber = String.valueOf(objects[2]);
					callSign = String.valueOf(objects[3]);
					String vrCode = String.valueOf(objects[4]);
					registryNumber = String.valueOf(objects[5]);
					documentNameIN = String.valueOf(objects[6]);
					String documentYearIN = String.valueOf(objects[7]);
					String documentNameOUT = String.valueOf(objects[8]);
					String documentYearOUT = String.valueOf(objects[9]);
					String documentNameVOY = String.valueOf(objects[10]);
					String documentYearVOY = String.valueOf(objects[11]);
					String markedAsArrival = String.valueOf(objects[12]);
					Date _timeOfArrival = (Date) objects[13];
					Date timeOfDeparture = (Date) objects[14];
					int markedAsDeparture = GetterUtil.getInteger(objects[15]);
					String shipBoat = String.valueOf(objects[16]);
					String itineraryNo = String.valueOf(objects[17]);

					JSONObject object = JSONFactoryUtil.createJSONObject();

					object.put("itineraryNo", itineraryNo);
					object.put("shipBoat", shipBoat);
					object.put("markedAsDeparture", markedAsDeparture);
					object.put("nameOfShip", nameOfShip);
					object.put("flagStateOfShip", flagStateOfShip);
					try {
						object.put(
								"flagStateOfShipName",
								DmStateLocalServiceUtil.getByStateCode(
										flagStateOfShip).getStateName());
					} catch (Exception e) {
						// nothing to do
					}
					object.put("imoNumber", imoNumber);
					object.put("callSign", callSign);
					object.put("vrCode", vrCode);
					object.put("registryNumber", registryNumber);
					object.put("documentNameIN", documentNameIN);
					object.put("documentYearIN", documentYearIN);
					object.put("documentNameOUT", documentNameOUT);
					object.put("documentYearOUT", documentYearOUT);
					object.put("documentNameVOY", documentNameVOY);
					object.put("documentYearVOY", documentYearVOY);
					object.put("markedAsArrival", markedAsArrival);
					if (_timeOfArrival != null) {
						String _tmp = "";
						try {
							_tmp = FormatData.formatDateShort3
									.format(_timeOfArrival);
						} catch (Exception e) {
							// TODO: handle exception
						}
						object.put("timeOfArrival", _tmp);
					} else {
						object.put("timeOfArrival", "");
					}

					if (timeOfDeparture != null) {
						String _tmp = "";
						try {
							_tmp = FormatData.formatDateShort3
									.format(timeOfDeparture);
						} catch (Exception e) {
							// TODO: handle exception
						}
						object.put("timeOfDeparture", _tmp);
					} else {
						object.put("timeOfDeparture", "");
					}
					data.put(object);

				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return data;
	}
}
