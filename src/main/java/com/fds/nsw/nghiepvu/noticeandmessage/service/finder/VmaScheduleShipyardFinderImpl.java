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
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleShipyardModelImpl;
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
import com.fds.nsw.nghiepvu.model.VmaScheduleShipyard;
@Service
@Slf4j
public class VmaScheduleShipyardFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleShipyard> queryFactory;

	public JSONArray findVmaScheduleShipyard(String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType, String shipYardCode,
			String shipYardCompanyName, String shipYardOfficialNo,
			String repairingFrom, String repairingTo, String repairingPlace,
			String repairingReason, Integer repaired, int start, int end)
			throws SystemException {

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * ");

			query.append(" FROM vma_schedule_shipyard ");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, documentName, documentYear, noticeShipType,
					shipYardCode, shipYardCompanyName, shipYardOfficialNo,
					repairingFrom, repairingTo, repairingPlace,
					repairingReason, repaired));

			query.append(" ORDER BY RepairingFrom ASC");

			log.info("=========select vma_schedule_shipyard>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaScheduleShipyard.class).build();

			/*  */

			List<VmaScheduleShipyard> vmaScheduleShipyards = (List<VmaScheduleShipyard>) queryFactory.getResultList(builder);

			if (vmaScheduleShipyards != null) {
				for (VmaScheduleShipyard vmaScheduleShipyard : vmaScheduleShipyards) {

					JSONObject object = VMAUtils.object2Json(
							vmaScheduleShipyard,
							VmaScheduleShipyard.class);

					data.put(object);
				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return data;
	}

	public long countVmaScheduleShipyard(String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType, String shipYardCode,
			String shipYardCompanyName, String shipYardOfficialNo,
			String repairingFrom, String repairingTo, String repairingPlace,
			String repairingReason, Integer repaired) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_schedule_shipyard WHERE 1 = 1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, documentName, documentYear, noticeShipType,
					shipYardCode, shipYardCompanyName, shipYardOfficialNo,
					repairingFrom, repairingTo, repairingPlace,
					repairingReason, repaired));

			log.info("=========count vma_schedule_shipyard>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType, String shipYardCode,
			String shipYardCompanyName, String shipYardOfficialNo,
			String repairingFrom, String repairingTo, String repairingPlace,
			String repairingReason, Integer repaired) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(nameOfShip)) {
			condition.append(" AND NameOfShip LIKE \"%" + nameOfShip + "%\"");
		}

		if (noticeShipType != null) {
			condition.append(" AND NoticeShipType = " + noticeShipType);
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

		if (Validator.isNotNull(shipYardCode)) {
			condition.append(" AND ShipYardCode = \"" + shipYardCode + "\"");
		}

		if (Validator.isNotNull(shipYardCompanyName)) {
			condition.append(" AND ShipYardCompanyName = \""
					+ shipYardCompanyName + "\"");
		}

		if (Validator.isNotNull(shipYardOfficialNo)) {
			condition.append(" AND ShipYardOfficialNo = \""
					+ shipYardOfficialNo + "\"");
		}

		if (Validator.isNotNull(repairingPlace)) {
			condition.append(" AND RepairingPlace LIKE \"%" + repairingPlace
					+ "%\"");
		}

		if (Validator.isNotNull(repairingReason)) {
			condition.append(" AND RepairingReason LIKE \"%" + repairingReason
					+ "%\"");
		}

		if (Validator.isNotNull(repairingFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(repairingFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND RepairingFrom BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(repairingTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(repairingTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND RepairingTo BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND ItineraryNo = \"" + itineraryNo + "\"");
		}

		return condition.toString();
	}

	public List<VmaScheduleShipyard> findVmaScheduleShipyard(Class<?> clazz,
			String className, String sql, int start, int end)
			throws SystemException {

		

		try {
			

			log.info("=========select vma_schedule_shipyard>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaScheduleShipyard.class).build();



			/*  */

			return (List<VmaScheduleShipyard>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaScheduleShipyard(String sql) throws SystemException { long count = 0; try {
			

			log.info("=========count vma_schedule_shipyard>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
