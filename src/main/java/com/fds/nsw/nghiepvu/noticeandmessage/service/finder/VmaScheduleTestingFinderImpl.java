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
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleTestingModelImpl;
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
import com.fds.nsw.nghiepvu.model.VmaScheduleTesting;
@Service
@Slf4j
public class VmaScheduleTestingFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleTesting> queryFactory;

	public JSONArray findVmaScheduleTesting(String itineraryNo,
			String portofAuthority, Long documentName, Integer documentYear,
			Integer noticeShipType, String testingFrom, String testingTo,
			String nameOfShip, String flagStateOfShip, String imoNumber,
			String callSign, String vrCode, String registryNumber,
			Double shownDraftxF, Double shownDraftxA, Double loa, Double dwt,
			int start, int end) throws SystemException {

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * ");

			query.append(" FROM vma_schedule_testing ");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					documentName, documentYear, noticeShipType, testingFrom,
					testingTo, nameOfShip, flagStateOfShip, imoNumber,
					callSign, vrCode, registryNumber, shownDraftxF,
					shownDraftxA, loa, dwt));

			query.append(" ORDER BY TestingFrom ASC");

			log.info("=========select vma_schedule_testing>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaScheduleTesting.class).build();

			/*  */

			List<VmaScheduleTesting> vmaScheduleTestings = (List<VmaScheduleTesting>) queryFactory.getResultList(builder);

			if (vmaScheduleTestings != null) {
				for (VmaScheduleTesting vmaScheduleTesting : vmaScheduleTestings) {

					JSONObject object = VMAUtils.object2Json(
							vmaScheduleTesting,
							VmaScheduleTesting.class, new String[] {
									"anchoringPortHarbourCode",
									"anchoringPortWharfCode",
									"shiftingPortHarbourCode",
									"shiftingPortWharfCode", "shipCaptain",
									"timeOfArrival", "timeOfDeparture" });

					data.put(object);
				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return data;
	}

	public long countVmaScheduleTesting(String itineraryNo,
			String portofAuthority, Long documentName, Integer documentYear,
			Integer noticeShipType, String testingFrom, String testingTo,
			String nameOfShip, String flagStateOfShip, String imoNumber,
			String callSign, String vrCode, String registryNumber,
			Double shownDraftxF, Double shownDraftxA, Double loa, Double dwt) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_schedule_launching WHERE 1 = 1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					documentName, documentYear, noticeShipType, testingFrom,
					testingTo, nameOfShip, flagStateOfShip, imoNumber,
					callSign, vrCode, registryNumber, shownDraftxF,
					shownDraftxA, loa, dwt));

			log.info("=========count vma_schedule_launching>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
			String portofAuthority, Long documentName, Integer documentYear,
			Integer noticeShipType, String testingFrom, String testingTo,
			String nameOfShip, String flagStateOfShip, String imoNumber,
			String callSign, String vrCode, String registryNumber,
			Double shownDraftxF, Double shownDraftxA, Double loa, Double dwt) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(" AND NameOfShip LIKE \"%" + nameOfShip + "%\"");
		}

		if (Validator.isNotNull(flagStateOfShip)) {
			condition.append(" AND FlagStateOfShip LIKE \"%" + flagStateOfShip
					+ "%\"");
		}

		if (noticeShipType != null) {
			condition.append(" AND NoticeShipType = " + noticeShipType);
		}

		if (shownDraftxF != null) {
			condition.append(" AND ShownDraftxF = " + shownDraftxF);
		}

		if (shownDraftxA != null) {
			condition.append(" AND ShownDraftxA = " + shownDraftxA);
		}

		if (loa != null) {
			condition.append(" AND LOA = " + loa);
		}

		if (dwt != null) {
			condition.append(" AND DWT = " + dwt);
		}

		if (documentYear != null) {
			condition.append(" AND DocumentYear = " + documentYear);
		}

		if (documentName != null) {
			condition.append(" AND DocumentName = " + documentName);
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(" AND PortofAuthority = \"" + portofAuthority
					+ "\"");
		}

		if (Validator.isNotNull(callSign)) {
			condition.append(" AND CallSign LIKE \"%" + callSign + "%\"");
		}

		if (Validator.isNotNull(vrCode)) {
			condition.append(" AND VRCode LIKE \"%" + vrCode + "%\"");
		}

		if (Validator.isNotNull(registryNumber)) {
			condition.append(" AND RegistryNumber LIKE \"%" + registryNumber
					+ "%\"");
		}

		if (Validator.isNotNull(imoNumber)) {
			condition.append(" AND IMONumber LIKE \"%" + imoNumber + "%\"");
		}

		if (Validator.isNotNull(testingFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(testingFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND TestingFrom BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(testingTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(testingTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND TestingTo BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND ItineraryNo = \"" + itineraryNo + "\"");
		}

		return condition.toString();
	}
	
	public List<VmaScheduleTesting> findVmaScheduleTesting(Class<?> clazz, String className,
			String sql, int start, int end) throws SystemException {

		

		try {
			
			
			log.info("=========select vma_schedule_testing>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaScheduleTesting.class).build();

			/*  */

			return (List<VmaScheduleTesting>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaScheduleTesting(String sql) throws SystemException { long count = 0; try {
			
			
			log.info("=========count vma_schedule_testing>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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

}
