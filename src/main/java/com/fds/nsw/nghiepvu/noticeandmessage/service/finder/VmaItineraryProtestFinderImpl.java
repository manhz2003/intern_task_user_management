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

import vn.gt.portlet.phuongtien.VMAUtils;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.nghiepvu.modelImpl.VmaItineraryProtestModelImpl;
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
import com.fds.nsw.nghiepvu.model.VmaItineraryProtest;
@Service
@Slf4j
public class VmaItineraryProtestFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaItineraryProtest> queryFactory;

	public JSONArray findVmaItineraryProtest(String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType,
			String shipOperatorCode, String shipAgencyCode, String protestDate,
			String protestRemarks, Integer makePayment, int start, int end)
			throws SystemException {

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * ");

			query.append(" FROM vma_itinerary_protest ");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, documentName, documentYear, noticeShipType,
					shipOperatorCode, shipAgencyCode, protestDate,
					protestRemarks, makePayment));

			query.append(" ORDER BY ProtestDate ASC");

			log.info("=========select vma_itinerary_protest>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaItineraryProtest.class).build();

			/*  */

			List<VmaItineraryProtest> vmaItineraryProtests = (List<VmaItineraryProtest>) queryFactory.getResultList(builder);

			if (vmaItineraryProtests != null) {
				for (VmaItineraryProtest vmaItineraryProtest : vmaItineraryProtests) {

					JSONObject object = VMAUtils.object2Json(
							vmaItineraryProtest,
							VmaItineraryProtest.class, new String[]{"shipAgencyCode", "shipOperatorCode", "shipOwnerCode"});

					data.put(object);
				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return data;
	}

	public long countVmaItineraryProtest(String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType,
			String shipOperatorCode, String shipAgencyCode, String protestDate,
			String protestRemarks, Integer makePayment) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_itinerary_protest WHERE 1 = 1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, documentName, documentYear, noticeShipType,
					shipOperatorCode, shipAgencyCode, protestDate,
					protestRemarks, makePayment));

			log.info("=========count vma_itinerary_protest>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
			String shipOperatorCode, String shipAgencyCode, String protestDate,
			String protestRemarks, Integer makePayment) {
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

		if (makePayment != null) {
			condition.append(" AND MakePayment = " + makePayment);
		}

		if (Validator.isNotNull(shipOperatorCode)) {
			condition.append(" AND ShipOperatorCode = \"" + shipOperatorCode
					+ "\"");
		}

		if (Validator.isNotNull(shipAgencyCode)) {
			condition
					.append(" AND ShipAgencyCode = \"" + shipAgencyCode + "\"");
		}

		if (Validator.isNotNull(protestRemarks)) {
			condition.append(" AND ProtestRemarks LIKE \"%" + protestRemarks
					+ "%\"");
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(" AND PortofAuthority = \"" + portofAuthority
					+ "\"");
		}

		if (Validator.isNotNull(protestDate)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(protestDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND ProtestDate BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND ItineraryNo = \"" + itineraryNo + "\"");
		}

		return condition.toString();
	}
	
	public List<VmaItineraryProtest> findVmaItineraryProtest(Class<?> clazz, String className,
			String sql, int start, int end) throws SystemException {

		

		try {
			
			
			log.info("=========select vma_itinerary_protest>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaItineraryProtest.class).build();


			return (List<VmaItineraryProtest>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaItineraryProtest(String sql) throws SystemException { long count = 0; try {
			
		
			log.info("=========count vma_itinerary_protest>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
