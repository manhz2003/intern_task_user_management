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
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleTugboatListModelImpl;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleTugboatModelImpl;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatListLocalServiceUtil;
import vn.gt.portlet.phuongtien.VMAUtils;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.utility.string.StringPool;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboat;
@Service
@Slf4j
public class VmaScheduleTugboatFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleTugboat> queryFactory;

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;

	public JSONArray findVmaScheduleTugboat(String itineraryNo,
			String nameOfShip, Integer noticeShipType,
			String anchoringPortHarbourCode, String anchoringPortWharfCode,
			String shiftingPortRegionCode, String shiftingPortHarbourCode,
			String shiftingPortWharfCode, String tugDateFrom, String tugDateTo,
			int start, int end) throws SystemException {

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * ");

			query.append(" FROM vma_schedule_tugboat ");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, nameOfShip,
					noticeShipType, anchoringPortHarbourCode,
					anchoringPortWharfCode, shiftingPortRegionCode,
					shiftingPortHarbourCode, shiftingPortWharfCode,
					tugDateFrom, tugDateTo));

			query.append(" ORDER BY TugDateFrom ASC");

			log.info("=========select vma_schedule_tugboat>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaScheduleTugboat.class).build();

			/*  */

			List<VmaScheduleTugboat> vmaScheduleTugboats = (List<VmaScheduleTugboat>) queryFactory.getResultList(builder);

			if (vmaScheduleTugboats != null) {
				for (VmaScheduleTugboat vmaScheduleTugboat : vmaScheduleTugboats) {
					List<VmaScheduleTugboatList> vmaScheduleTugboatLists = VmaScheduleTugboatListLocalServiceUtil
							.findByItineraryNo_SequenceNo(
									vmaScheduleTugboat.getItineraryNo(),
									vmaScheduleTugboat.getSequenceNo());

					JSONArray tugboatList = JSONFactoryUtil.createJSONArray();

					if (vmaScheduleTugboatLists != null) {
						for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
							tugboatList.put(VMAUtils.object2Json(
									vmaScheduleTugboatList,
									VmaScheduleTugboatList.class));

						}
					}

					JSONObject tugboat = VMAUtils.object2Json(
							vmaScheduleTugboat,
							VmaScheduleTugboat.class);

					tugboat.put("tugboatList", tugboatList);

					data.put(tugboat);
				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return data;
	}

	public long countVmaScheduleTugboat(String itineraryNo, String nameOfShip,
			Integer noticeShipType, String anchoringPortHarbourCode,
			String anchoringPortWharfCode, String shiftingPortRegionCode,
			String shiftingPortHarbourCode, String shiftingPortWharfCode,
			String tugDateFrom, String tugDateTo) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_schedule_tugboat WHERE 1 = 1 ");

			query.append(generateCondition(itineraryNo, nameOfShip,
					noticeShipType, anchoringPortHarbourCode,
					anchoringPortWharfCode, shiftingPortRegionCode,
					shiftingPortHarbourCode, shiftingPortWharfCode,
					tugDateFrom, tugDateTo));

			log.info("=========count vma_schedule_tugboat>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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

	private String generateCondition(String itineraryNo, String nameOfShip,
			Integer noticeShipType, String anchoringPortHarbourCode,
			String anchoringPortWharfCode, String shiftingPortRegionCode,
			String shiftingPortHarbourCode, String shiftingPortWharfCode,
			String tugDateFrom, String tugDateTo) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(" AND NameOfShip LIKE \"%" + nameOfShip + "%\"");
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

		if (Validator.isNotNull(tugDateFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(tugDateFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND TugDateFrom BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(tugDateTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(tugDateTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND TugDateTo BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND ItineraryNo = \"" + itineraryNo + "\"");
		}

		return condition.toString();
	}

	public List<VmaScheduleTugboat> findVmaScheduleTugboat(Class<?> clazz,
			String className, String sql, int start, int end)
			throws SystemException {

		

		try {
			
			log.info("=========select vma_schedule_tugboat>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaScheduleTugboat.class).build();


			/*  */

			return (List<VmaScheduleTugboat>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaScheduleTugboat(String sql) throws SystemException { long count = 0; try {
			
			log.info("=========count vma_schedule_tugboat>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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

	public JSONArray getModelMau24_2T(String maritimeCode, String shipCode,
			String startDate, String endDate) throws SystemException {

		JSONArray array = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "Select DISTINCT vma_schedule_tugboat.ItineraryNo, vma_schedule_tugboat.NameOfShip, vma_schedule_tugboat.TugDateFrom, vma_schedule_tugboat.TugDateTo, vma_schedule_tugboat.NoticeShipType, dm_port.PortName as ArrivalPortName, dm_port_region.PortRegionName, dm_port_harbour.PortHarbourName, dm_port_wharf.PortWharfName from vma_schedule_tugboat INNER JOIN vma_schedule_tugboat_list ON vma_schedule_tugboat_list.ItineraryNo = vma_schedule_tugboat.ItineraryNo and vma_schedule_tugboat_list.SequenceNo = vma_schedule_tugboat.SequenceNo and vma_schedule_tugboat_list.PortofAuthority = '"
					+ maritimeCode
					+ "' and vma_schedule_tugboat_list.ShipCode = '"
					+ shipCode
					+ "' LEFT JOIN vma_itinerary ON vma_itinerary.ItineraryNo = vma_schedule_tugboat.ItineraryNo LEFT JOIN dm_port ON dm_port.PortCode = vma_itinerary.ArrivalPortCode LEFT JOIN dm_port_region ON dm_port_region.PortRegionCode = vma_schedule_tugboat.ShiftingPortRegionCode LEFT JOIN dm_port_harbour ON dm_port_harbour.PortHarbourCode = vma_schedule_tugboat.ShiftingPortHarbourCode LEFT JOIN dm_port_wharf ON dm_port_wharf.PortWharfCode = vma_schedule_tugboat.ShiftingPortWharfCode WHERE ((TugDateFrom >= '"
					+ startDate
					+ "' and TugDateFrom <= '"
					+ endDate
					+ "') AND (TugDateTo >= '"
					+ startDate
					+ "' OR TugDateTo is null)) or (TugDateFrom is null and (TugDateTo >= '"
					+ startDate
					+ "' and TugDateTo <= '"
					+ endDate
					+ "')) ORDER BY vma_schedule_tugboat.TugDateFrom";

			log.info("=========select bao cao mau_24_2t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();

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
					String tugDateFrom = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String tugDateTo = String
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
					obj.put("tugDateFrom", tugDateFrom);
					obj.put("tugDateTo", tugDateTo);
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

	public JSONArray getModelMau25T(String maritimeCode,
			String tugboatCompanyCode, String startDate, String endDate)
			throws SystemException {

		JSONArray array = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "Select DISTINCT vma_schedule_tugboat_list.ItineraryNo, vma_schedule_tugboat.NameOfShip, vma_schedule_tugboat_list.ShipName, vma_schedule_tugboat_list.Power, vma_schedule_tugboat.TugDateFrom, vma_schedule_tugboat.TugDateTo, vma_schedule_tugboat.NoticeShipType, dm_port.PortName as ArrivalPortName, dm_port_region.PortRegionName, dm_port_harbour.PortHarbourName, dm_port_wharf.PortWharfName from vma_schedule_tugboat INNER JOIN vma_schedule_tugboat_list ON vma_schedule_tugboat_list.ItineraryNo = vma_schedule_tugboat.ItineraryNo and vma_schedule_tugboat_list.SequenceNo = vma_schedule_tugboat.SequenceNo and vma_schedule_tugboat_list.PortofAuthority = '"
					+ maritimeCode
					+ "' and vma_schedule_tugboat_list.TugboatCompanyCode = '"
					+ tugboatCompanyCode
					+ "' LEFT JOIN vma_itinerary ON vma_itinerary.ItineraryNo = vma_schedule_tugboat.ItineraryNo LEFT JOIN dm_port ON dm_port.PortCode = vma_itinerary.ArrivalPortCode LEFT JOIN dm_port_region ON dm_port_region.PortRegionCode = vma_schedule_tugboat.ShiftingPortRegionCode LEFT JOIN dm_port_harbour ON dm_port_harbour.PortHarbourCode = vma_schedule_tugboat.ShiftingPortHarbourCode LEFT JOIN dm_port_wharf ON dm_port_wharf.PortWharfCode = vma_schedule_tugboat.ShiftingPortWharfCode WHERE ((TugDateFrom >= '"
					+ startDate
					+ "' and TugDateFrom <= '"
					+ endDate
					+ "') AND (TugDateTo >=  '"
					+ startDate
					+ "' OR TugDateTo is null)) or  (TugDateFrom is null and (TugDateTo >= '"
					+ startDate
					+ "' and TugDateTo <= '"
					+ endDate
					+ "')) ORDER BY vma_schedule_tugboat.TugDateFrom";

			log.info("=========select bao cao mau_25t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();

			

			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();

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
					String shipName = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					double power = Double.valueOf(String
							.valueOf(objects[3] != null ? objects[3] : -1));
					String tugDateFrom = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					String tugDateTo = String
							.valueOf(objects[5] != null ? objects[5]
									: StringPool.BLANK);
					String noticeShipType = String
							.valueOf(objects[6] != null ? objects[6]
									: StringPool.BLANK);
					String arrivalPortName = String
							.valueOf(objects[7] != null ? objects[7]
									: StringPool.BLANK);
					String portRegionName = String
							.valueOf(objects[8] != null ? objects[8]
									: StringPool.BLANK);
					String portHarbourName = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);
					String portWharfName = String
							.valueOf(objects[10] != null ? objects[10]
									: StringPool.BLANK);

					obj.put("itineraryNo", itineraryNo);
					obj.put("nameOfShip", nameOfShip);
					obj.put("shipName", shipName);
					obj.put("power", power >= 0 ? power : null);
					obj.put("tugDateFrom", tugDateFrom);
					obj.put("tugDateTo", tugDateTo);
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

}
