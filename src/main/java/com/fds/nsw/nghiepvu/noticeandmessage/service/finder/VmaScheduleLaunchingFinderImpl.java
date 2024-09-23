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

import java.util.*;

import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleLaunchingModelImpl;
import vn.gt.portlet.phuongtien.VMAUtils;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.utility.string.StringPool;
import org.json.JSONArray;
import org.json.JSONObject;

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
import com.fds.nsw.nghiepvu.model.VmaScheduleLaunching;
@Service
@Slf4j
public class VmaScheduleLaunchingFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleLaunching> queryFactory;

	public JSONArray findVmaScheduleLaunching(String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType, String launchingFrom,
			String launchingTo, String launchingPlace, String launchingReason,
			int start, int end) throws SystemException {

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * ");

			query.append(" FROM vma_schedule_launching ");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, documentName, documentYear, noticeShipType,
					launchingFrom, launchingTo, launchingPlace, launchingReason));

			query.append(" ORDER BY LaunchingFrom ASC");

			log.info("=========select vma_schedule_launching>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaScheduleLaunching.class).build();

			/*  */

			List<VmaScheduleLaunching> vmaScheduleLaunchings = (List<VmaScheduleLaunching>) queryFactory.getResultList(builder);

			if (vmaScheduleLaunchings != null) {
				for (VmaScheduleLaunching vmaScheduleLaunching : vmaScheduleLaunchings) {

					JSONObject object = VMAUtils.object2Json(
							vmaScheduleLaunching,
							VmaScheduleLaunching.class, new String[] {
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

	public long countVmaScheduleLaunching(String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType, String launchingFrom,
			String launchingTo, String launchingPlace, String launchingReason) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_schedule_launching WHERE 1 = 1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, documentName, documentYear, noticeShipType,
					launchingFrom, launchingTo, launchingPlace, launchingReason));

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
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType, String launchingFrom,
			String launchingTo, String launchingPlace, String launchingReason) {
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

		if (Validator.isNotNull(launchingPlace)) {
			condition.append(" AND LaunchingPlace LIKE \"%" + launchingPlace
					+ "%\"");
		}

		if (Validator.isNotNull(launchingReason)) {
			condition.append(" AND LaunchingReason LIKE \"%" + launchingReason
					+ "%\"");
		}

		if (Validator.isNotNull(launchingFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(launchingFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND LaunchingFrom BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(launchingTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(launchingTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND LaunchingTo BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND ItineraryNo = \"" + itineraryNo + "\"");
		}

		return condition.toString();
	}

	public List<VmaScheduleLaunching> findVmaScheduleLaunching(Class<?> clazz,
			String className, String sql, int start, int end)
			throws SystemException {

		

		try {
			

			log.info("=========select vma_schedule_launching>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true)
					.queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaScheduleLaunching.class).build();


			return (List<VmaScheduleLaunching>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaScheduleLaunching(String sql) throws SystemException { long count = 0; try {
			

			log.info("=========count vma_schedule_launching>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
