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
import com.fds.nsw.nghiepvu.model.VmaSchedulePilotList;
import com.fds.nsw.nghiepvu.modelImpl.VmaSchedulePilotListModelImpl;
import com.fds.nsw.nghiepvu.modelImpl.VmaSchedulePilotModelImpl;
import vn.gt.dao.noticeandmessage.service.VmaSchedulePilotListLocalServiceUtil;
import vn.gt.portlet.phuongtien.VMAUtils;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.utility.string.StringPool;
import org.json.JSONArray;
import org.json.JSONObject;

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
import com.fds.nsw.nghiepvu.model.VmaSchedulePilot;
@Service
@Slf4j
public class VmaSchedulePilotFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaSchedulePilot> queryFactory;

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;

	public JSONArray findVmaSchedulePilot(String itineraryNo,
			String portofAuthority, String nameOfShip, String certificateNo,
			Integer noticeShipType, String anchoringPortHarbourCode,
			String anchoringPortWharfCode, String shiftingPortRegionCode,
			String shiftingPortHarbourCode, String shiftingPortWharfCode,
			String pilotDateFrom, String pilotDateTo, int start, int end)
			throws SystemException {

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * ");

			query.append(" FROM vma_schedule_pilot ");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, certificateNo, noticeShipType,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, pilotDateFrom, pilotDateTo));

			query.append(" ORDER BY PilotDateFrom ASC");

			log.info("=========select vma_schedule_pilot>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaSchedulePilot.class).build();

			/*  */

			List<VmaSchedulePilot> vmaScheduleTugboats = (List<VmaSchedulePilot>) queryFactory.getResultList(builder);

			if (vmaScheduleTugboats != null) {
				for (VmaSchedulePilot vmaSchedulePilot : vmaScheduleTugboats) {
					List<VmaSchedulePilotList> vmaSchedulePilotLists = VmaSchedulePilotListLocalServiceUtil
							.findByItineraryNo_SequenceNo(
									vmaSchedulePilot.getItineraryNo(),
									vmaSchedulePilot.getSequenceNo());

					JSONArray pilotList = JSONFactoryUtil.createJSONArray();

					if (vmaSchedulePilotLists != null) {
						for (VmaSchedulePilotList vmaSchedulePilotList : vmaSchedulePilotLists) {
							pilotList.put(VMAUtils.object2Json(
									vmaSchedulePilotList,
									VmaSchedulePilotList.class));

						}
					}

					JSONObject object = VMAUtils.object2Json(vmaSchedulePilot,
							VmaSchedulePilot.class);

					object.put("pilotList", pilotList);

					data.put(object);
				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return data;
	}

	public long countVmaSchedulePilot(String itineraryNo,
			String portofAuthority, String nameOfShip, String certificateNo,
			Integer noticeShipType, String anchoringPortHarbourCode,
			String anchoringPortWharfCode, String shiftingPortRegionCode,
			String shiftingPortHarbourCode, String shiftingPortWharfCode,
			String pilotDateFrom, String pilotDateTo) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_schedule_pilot WHERE 1 = 1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, certificateNo, noticeShipType,
					anchoringPortHarbourCode, anchoringPortWharfCode,
					shiftingPortRegionCode, shiftingPortHarbourCode,
					shiftingPortWharfCode, pilotDateFrom, pilotDateTo));

			log.info("=========count vma_schedule_pilot>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();

			/*  */

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	private String generateCondition(String itineraryNo,
			String portofAuthority, String nameOfShip, String certificateNo,
			Integer noticeShipType, String anchoringPortHarbourCode,
			String anchoringPortWharfCode, String shiftingPortRegionCode,
			String shiftingPortHarbourCode, String shiftingPortWharfCode,
			String pilotDateFrom, String pilotDateTo) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(" AND NameOfShip LIKE \"%" + nameOfShip + "%\"");
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(" AND PortofAuthority = \"" + portofAuthority
					+ "\"");
		}

		if (Validator.isNotNull(certificateNo)) {
			condition.append(" AND CertificateNo = \"" + certificateNo + "\"");
		}

		if (noticeShipType != null) {
			condition.append(" AND NoticeShipType = " + noticeShipType);
		}

		if (Validator.isNotNull(anchoringPortHarbourCode)) {
			condition.append(" AND AnchoringPortHarbourCode = \""
					+ anchoringPortHarbourCode + "\"");
		}

		if (Validator.isNotNull(anchoringPortHarbourCode)) {
			condition.append(" AND AnchoringPortWharfCode = \""
					+ anchoringPortWharfCode + "\"");
		}

		if (Validator.isNotNull(shiftingPortRegionCode)) {
			condition.append(" AND ShiftingPortRegionCode = \""
					+ shiftingPortRegionCode + "\"");
		}

		if (Validator.isNotNull(shiftingPortHarbourCode)) {
			condition.append(" AND ShiftingPortHarbourCode = \""
					+ shiftingPortHarbourCode + "\"");
		}

		if (Validator.isNotNull(shiftingPortWharfCode)) {
			condition.append(" AND ShiftingPortWharfCode = \""
					+ shiftingPortWharfCode + "\"");
		}

		if (Validator.isNotNull(pilotDateFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(pilotDateFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND PilotDateFrom BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(pilotDateTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(pilotDateTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND PilotDateTo BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND ItineraryNo = \"" + itineraryNo + "\"");
		}

		return condition.toString();
	}

	public List<VmaSchedulePilot> findVmaSchedulePilot(Class<?> clazz,
			String className, String sql, int start, int end)
			throws SystemException {

		

		try {
			
			log.info("=========select vma_schedule_pilot>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaSchedulePilot.class).build();

			/*  */

			return (List<VmaSchedulePilot>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaSchedulePilot(String sql) throws SystemException { long count = 0; try {
			
			log.info("=========count vma_schedule_pilot>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			/*  */

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public JSONArray getModelMau26_2T(String maritimeCode, String pilotCode,
			String startDate, String endDate) throws SystemException {

		JSONArray array = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "Select DISTINCT vma_schedule_pilot.ItineraryNo, vma_schedule_pilot.NameOfShip, vma_schedule_pilot.PilotDateFrom, vma_schedule_pilot.PilotDateTo, vma_schedule_pilot.NoticeShipType, dm_port.PortName as ArrivalPortName, dm_port_region.PortRegionName, dm_port_harbour.PortHarbourName, dm_port_wharf.PortWharfName from vma_schedule_pilot INNER JOIN vma_schedule_pilot_list ON vma_schedule_pilot_list.ItineraryNo = vma_schedule_pilot.ItineraryNo and vma_schedule_pilot_list.SequenceNo = vma_schedule_pilot.SequenceNo and vma_schedule_pilot_list.PortofAuthority = '"
					+ maritimeCode
					+ "'and vma_schedule_pilot_list.PilotCode = '"
					+ pilotCode
					+ "'LEFT JOIN vma_itinerary ON vma_itinerary.ItineraryNo = vma_schedule_pilot.ItineraryNo LEFT JOIN dm_port ON dm_port.PortCode = vma_itinerary.ArrivalPortCode LEFT JOIN dm_port_region ON dm_port_region.PortRegionCode = vma_schedule_pilot.ShiftingPortRegionCode LEFT JOIN dm_port_harbour ON dm_port_harbour.PortHarbourCode = vma_schedule_pilot.ShiftingPortHarbourCode LEFT JOIN dm_port_wharf ON dm_port_wharf.PortWharfCode = vma_schedule_pilot.ShiftingPortWharfCode WHERE ((PilotDateFrom >= '"
					+ startDate
					+ "'and PilotDateFrom <= '"
					+ endDate
					+ "') AND (PilotDateTo >=  '"
					+ startDate
					+ "'OR PilotDateTo is null)) or  (PilotDateFrom is null and (PilotDateTo >= '"
					+ startDate
					+ "'and PilotDateTo <= '"
					+ endDate
					+ "')) ORDER BY vma_schedule_pilot.PilotDateFrom;";

			log.info("=========select bao cao mau_26_2t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					JSONObject obj = JSONFactoryUtil.createJSONObject();

					String itineraryNo = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String nameOfShip = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String pilotDateFrom = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String pilotDateTo = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String noticeShipType = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					String arrivalPortName = String
							.valueOf(objects[5] != null ? objects[5]
									: StringPool.BLANK);
					String portRegionName = String
							.valueOf(objects[6] != null ? objects[6]
									: StringPool.BLANK);
					String portHarbourName = String
							.valueOf(objects[7] != null ? objects[7]
									: StringPool.BLANK);
					String portWharfName = String
							.valueOf(objects[8] != null ? objects[8]
									: StringPool.BLANK);

					obj.put("itineraryNo", itineraryNo);
					obj.put("nameOfShip", nameOfShip);
					obj.put("pilotDateFrom", pilotDateFrom);
					obj.put("pilotDateTo", pilotDateTo);
					obj.put("noticeShipType", noticeShipType);
					obj.put("arrivalPortName", arrivalPortName);
					obj.put("portRegionName", portRegionName);
					obj.put("portHarbourName", portHarbourName);
					obj.put("portWharfName", portWharfName);

					array.put(obj);
				}
			}

			return array;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray getModelMau27T(String maritimeCode,
			String pilotCompanyCode, String startDate, String endDate)
			throws SystemException {

		JSONArray array = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "Select DISTINCT vma_schedule_pilot.ItineraryNo, vma_schedule_pilot.NameOfShip, vma_itinerary_schedule.LOA, vma_itinerary_schedule.Breadth, vma_itinerary_schedule.GT, vma_itinerary_schedule.ShownDraftxA, pilot_list.PilotName, vma_schedule_pilot.PilotDateFrom, vma_schedule_pilot.PilotDateTo, vma_schedule_pilot.NoticeShipType, dm_port.PortName as ArrivalPortName, dm_port_region.PortRegionName, dm_port_harbour.PortHarbourName, dm_port_wharf.PortWharfName from vma_schedule_pilot INNER JOIN (select v1.ItineraryNo, v1.PortofAuthority, v2.PilotCompanyCode, v1.SequenceNo , GROUP_CONCAT(v2.PilotName) as PilotName from vma_schedule_pilot as v1 INNER JOIN vma_schedule_pilot_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.SequenceNo = v2.SequenceNo GROUP BY v1.ItineraryNo, v1.SequenceNo) as pilot_list ON pilot_list.ItineraryNo = vma_schedule_pilot.ItineraryNo and pilot_list.SequenceNo = vma_schedule_pilot.SequenceNo and pilot_list.PilotName is not null and pilot_list.PortofAuthority = '"
					+ maritimeCode
					+ "'and pilot_list.PilotCompanyCode = '"
					+ pilotCompanyCode
					+ "'LEFT JOIN vma_itinerary_schedule ON vma_itinerary_schedule.ItineraryNo = vma_schedule_pilot.ItineraryNo and vma_itinerary_schedule.NoticeShipType = vma_schedule_pilot.NoticeShipType and (vma_itinerary_schedule.CertificateNo = vma_schedule_pilot.CertificateNo or vma_itinerary_schedule.CertificateNo is NULL) LEFT JOIN vma_itinerary ON vma_itinerary.ItineraryNo = vma_schedule_pilot.ItineraryNo LEFT JOIN dm_port ON dm_port.PortCode = vma_itinerary.ArrivalPortCode LEFT JOIN dm_port_region ON dm_port_region.PortRegionCode = vma_schedule_pilot.ShiftingPortRegionCode LEFT JOIN dm_port_harbour ON dm_port_harbour.PortHarbourCode = vma_schedule_pilot.ShiftingPortHarbourCode LEFT JOIN dm_port_wharf ON dm_port_wharf.PortWharfCode = vma_schedule_pilot.ShiftingPortWharfCode WHERE ((PilotDateFrom >= '"
					+ startDate
					+ "'and PilotDateFrom <= v_toDate) AND (PilotDateTo >=  '"
					+ startDate
					+ "'OR PilotDateTo is null)) or  (PilotDateFrom is null and (PilotDateTo >= '"
					+ startDate
					+ "'and PilotDateTo <= v_toDate)) ORDER BY vma_schedule_pilot.PilotDateFrom;";

			log.info("=========select bao cao mau_25t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					JSONObject obj = JSONFactoryUtil.createJSONObject();

					String itineraryNo = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String nameOfShip = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					double loa = Double.valueOf(String
							.valueOf(objects[2] != null ? objects[2] : -1));
					double breadth = Double.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					double gt = Double.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					double shownDraftxA = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					String pilotName = String
							.valueOf(objects[6] != null ? objects[6]
									: StringPool.BLANK);
					String pilotDateFrom = String
							.valueOf(objects[7] != null ? objects[7]
									: StringPool.BLANK);
					String pilotDateTo = String
							.valueOf(objects[8] != null ? objects[8]
									: StringPool.BLANK);
					String noticeShipType = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String arrivalPortName = String
							.valueOf(objects[10] != null ? objects[10]
									: StringPool.BLANK);
					String portRegionName = String
							.valueOf(objects[11] != null ? objects[11]
									: StringPool.BLANK);
					String portHarbourName = String
							.valueOf(objects[12] != null ? objects[12]
									: StringPool.BLANK);
					String portWharfName = String
							.valueOf(objects[13] != null ? objects[13]
									: StringPool.BLANK);

					obj.put("itineraryNo", itineraryNo);
					obj.put("nameOfShip", nameOfShip);
					obj.put("pilotDateFrom", pilotDateFrom);
					obj.put("pilotDateTo", pilotDateTo);
					obj.put("noticeShipType", noticeShipType);
					obj.put("arrivalPortName", arrivalPortName);
					obj.put("portRegionName", portRegionName);
					obj.put("portHarbourName", portHarbourName);
					obj.put("portWharfName", portWharfName);
					obj.put("loa", loa >= 0 ? loa : null);
					obj.put("breadth", breadth >= 0 ? breadth : null);
					obj.put("gt", gt >= 0 ? gt : null);
					obj.put("shownDraftxA", shownDraftxA >= 0 ? shownDraftxA
							: null);
					obj.put("pilotName", pilotName);

					array.put(obj);
				}
			}

			return array;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
}
