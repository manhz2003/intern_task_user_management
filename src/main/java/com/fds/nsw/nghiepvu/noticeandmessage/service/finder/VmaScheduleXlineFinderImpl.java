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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleXlineModelImpl;
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
import com.fds.nsw.nghiepvu.model.VmaScheduleXline;
@Service
@Slf4j
public class VmaScheduleXlineFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleXline> queryFactory;

	

	public JSONArray findVmaScheduleXline(String portofAuthority,
			String shipOperatorCode, String companyName,
			String routeDescription, Integer scheduleYear,
			Integer scheduleMonth, Integer voyageNumber, int start, int end)
			throws SystemException {

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * ");

			query.append(" FROM vma_schedule_xline ");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(portofAuthority, shipOperatorCode,
					companyName, routeDescription, scheduleYear, scheduleMonth,
					voyageNumber));

			query.append(" ORDER BY ScheduleYear, ScheduleMonth DESC");

			log.info("=========select vma_schedule_xline>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaScheduleXline.class).build();

			/*  */

			List<VmaScheduleXline> vmaScheduleXlines = (List<VmaScheduleXline>) queryFactory.getResultList(builder);

			if (vmaScheduleXlines != null) {
				for (VmaScheduleXline vmaScheduleXline : vmaScheduleXlines) {

					JSONObject object = VMAUtils.object2Json(vmaScheduleXline,
							VmaScheduleXline.class);

					data.put(object);
				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return data;
	}

	public long countVmaScheduleXline(String portofAuthority,
			String shipOperatorCode, String companyName,
			String routeDescription, Integer scheduleYear,
			Integer scheduleMonth, Integer voyageNumber) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_schedule_xline WHERE 1 = 1 ");

			query.append(generateCondition(portofAuthority, shipOperatorCode,
					companyName, routeDescription, scheduleYear, scheduleMonth,
					voyageNumber)

			);

			log.info("=========count vma_schedule_xline>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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

	private String generateCondition(String portofAuthority,
			String shipOperatorCode, String companyName,
			String routeDescription, Integer scheduleYear,
			Integer scheduleMonth, Integer voyageNumber) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(companyName)) {
			condition.append(" AND CompanyName LIKE \"%" + companyName + "%\"");
		}

		if (voyageNumber != null) {
			condition.append(" AND VoyageNumber = " + voyageNumber);
		}

		if (scheduleMonth != null) {
			condition.append(" AND ScheduleMonth = " + scheduleMonth);
		}

		if (scheduleYear != null) {
			condition.append(" AND ScheduleYear = " + scheduleYear);
		}

		if (Validator.isNotNull(shipOperatorCode)) {
			condition.append(" AND ShipOperatorCode = \"" + shipOperatorCode
					+ "\"");
		}

		if (Validator.isNotNull(routeDescription)) {
			condition.append(" AND RouteDescription LIKE \"%"
					+ routeDescription + "%\"");
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(" AND PortofAuthority = \"" + portofAuthority
					+ "\"");
		}

		return condition.toString();
	}

	public List<VmaScheduleXline> findVmaScheduleXline(Class<?> clazz,
			String className, String sql, int start, int end)
			throws SystemException {

		

		try {
			

			log.info("=========select vma_schedule_xline>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaScheduleXline.class).build();



			return (List<VmaScheduleXline>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaScheduleXline(String sql) throws SystemException { long count = 0; try {
			

			log.info("=========count vma_schedule_xline>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
