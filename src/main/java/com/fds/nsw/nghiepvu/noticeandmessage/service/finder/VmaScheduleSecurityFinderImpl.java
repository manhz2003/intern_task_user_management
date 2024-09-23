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

import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleSecurityModelImpl;
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
import com.fds.nsw.nghiepvu.model.VmaScheduleSecurity;
@Service
@Slf4j
public class VmaScheduleSecurityFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleSecurity> queryFactory;

	public JSONArray findVmaScheduleSecurity(String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType,
			String securityOfficeCode, String securityCompanyName,
			String securityOfficialNo, String securityDate,
			String securityPlace, String securityReason, Integer evacuated,
			String evacuateOfficialCode, String evacuateCompanyName,
			String evacuateOfficialNo, String evacuateDate,
			String evacuateReason, int start, int end) throws SystemException {

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * ");

			query.append(" FROM vma_schedule_security ");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, documentName, documentYear, noticeShipType,
					securityOfficeCode, securityCompanyName,
					securityOfficialNo, securityDate, securityPlace,
					securityReason, evacuated, evacuateOfficialCode,
					evacuateCompanyName, evacuateOfficialNo, evacuateDate,
					evacuateReason));

			query.append(" ORDER BY SecurityDate ASC");

			log.info("=========select vma_schedule_security>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaScheduleSecurity.class).build();

			/*  */

			List<VmaScheduleSecurity> vmaScheduleSecuritys = (List<VmaScheduleSecurity>) queryFactory.getResultList(builder);

			if (vmaScheduleSecuritys != null) {
				for (VmaScheduleSecurity vmaScheduleSecurity : vmaScheduleSecuritys) {

					JSONObject object = VMAUtils.object2Json(
							vmaScheduleSecurity,
							VmaScheduleSecurity.class, new String[] {
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

	public long countVmaScheduleSecurity(String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType,
			String securityOfficeCode, String securityCompanyName,
			String securityOfficialNo, String securityDate,
			String securityPlace, String securityReason, Integer evacuated,
			String evacuateOfficialCode, String evacuateCompanyName,
			String evacuateOfficialNo, String evacuateDate,
			String evacuateReason) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_schedule_security WHERE 1 = 1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, documentName, documentYear, noticeShipType,
					securityOfficeCode, securityCompanyName,
					securityOfficialNo, securityDate, securityPlace,
					securityReason, evacuated, evacuateOfficialCode,
					evacuateCompanyName, evacuateOfficialNo, evacuateDate,
					evacuateReason));

			log.info("=========count vma_schedule_security>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
			Integer documentYear, Integer noticeShipType,
			String securityOfficeCode, String securityCompanyName,
			String securityOfficialNo, String securityDate,
			String securityPlace, String securityReason, Integer evacuated,
			String evacuateOfficialCode, String evacuateCompanyName,
			String evacuateOfficialNo, String evacuateDate,
			String evacuateReason) {
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

		if (documentYear != null) {
			condition.append(" AND DocumentYear = " + documentYear);
		}

		if (evacuated != null) {
			condition.append(" AND Evacuated = " + evacuated);
		}

		if (Validator.isNotNull(evacuateOfficialCode)) {
			condition.append(" AND EvacuateOfficialCode = \""
					+ evacuateOfficialCode + "\"");
		}

		if (Validator.isNotNull(evacuateOfficialNo)) {
			condition.append(" AND EvacuateOfficialNo = \""
					+ evacuateOfficialNo + "\"");
		}

		if (Validator.isNotNull(evacuateReason)) {
			condition.append(" AND EvacuateReason LIKE \"%" + evacuateReason
					+ "%\"");
		}

		if (Validator.isNotNull(evacuateCompanyName)) {
			condition.append(" AND EvacuateCompanyName LIKE \"%"
					+ evacuateCompanyName + "%\"");
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(" AND PortofAuthority = \"" + portofAuthority
					+ "\"");
		}

		if (Validator.isNotNull(securityOfficeCode)) {
			condition.append(" AND SecurityOfficeCode = \""
					+ securityOfficeCode + "\"");
		}

		if (Validator.isNotNull(securityCompanyName)) {
			condition.append(" AND SecurityCompanyName LIKE \"%"
					+ securityCompanyName + "%\"");
		}

		if (Validator.isNotNull(securityOfficialNo)) {
			condition.append(" AND SecurityOfficialNo = \""
					+ securityOfficialNo + "\"");
		}

		if (Validator.isNotNull(securityPlace)) {
			condition.append(" AND SecurityPlace LIKE \"%" + securityPlace
					+ "%\"");
		}

		if (Validator.isNotNull(securityReason)) {
			condition.append(" AND SecurityReason LIKE \"%" + securityReason
					+ "%\"");
		}

		if (Validator.isNotNull(securityDate)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(securityDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND SecurityDate BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(evacuateDate)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(evacuateDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND EvacuateDate BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND ItineraryNo = \"" + itineraryNo + "\"");
		}

		return condition.toString();
	}

	public List<VmaScheduleSecurity> findVmaScheduleSecurity(Class<?> clazz, String className,
			String sql, int start, int end) throws SystemException {

		

		try {
			
		
			log.info("=========select vma_schedule_security>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaScheduleSecurity.class).build();

			/*  */

			return (List<VmaScheduleSecurity>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaScheduleSecurity(String sql) throws SystemException { long count = 0; try {
			
			
			log.info("=========count vma_schedule_security>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
