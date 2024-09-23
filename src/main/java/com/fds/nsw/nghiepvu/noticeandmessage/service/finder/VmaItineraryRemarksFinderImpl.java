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
import com.fds.nsw.nghiepvu.modelImpl.VmaItineraryRemarksModelImpl;


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
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import vn.gt.portlet.phuongtien.VMAUtils;
import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.nghiepvu.model.VmaItineraryRemark;
@Service
@Slf4j
public class VmaItineraryRemarksFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaItineraryRemark> queryFactory;

	public JSONArray findVmaItineraryRemarks(String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType, String requestDate,
			String requestPerson, String remarks, Integer markedAsPending,
			int start, int end) throws SystemException {

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * ");

			query.append(" FROM vma_itinerary_remarks ");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, documentName, documentYear, noticeShipType,
					requestDate, requestPerson, remarks, markedAsPending));

			query.append(" ORDER BY RequestDate ASC");

			log.info("=========select vma_itinerary_remarks>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaItineraryRemark.class).build();

			/*  */

			List<VmaItineraryRemark> vmaItineraryRemarkss = (List<VmaItineraryRemark>) queryFactory.getResultList(builder);

			if (vmaItineraryRemarkss != null) {
				for (VmaItineraryRemark vmaItineraryRemarks : vmaItineraryRemarkss) {

					JSONObject object = VMAUtils.object2Json(
							vmaItineraryRemarks,
							VmaItineraryRemark.class);

					data.put(object);
				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return data;
	}

	public long countVmaItineraryRemarks(String itineraryNo,
			String portofAuthority, String nameOfShip, Long documentName,
			Integer documentYear, Integer noticeShipType, String requestDate,
			String requestPerson, String remarks, Integer markedAsPending) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_itinerary_remarks WHERE 1 = 1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					nameOfShip, documentName, documentYear, noticeShipType,
					requestDate, requestPerson, remarks, markedAsPending));

			log.info("=========count vma_itinerary_remarks>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
			Integer documentYear, Integer noticeShipType, String requestDate,
			String requestPerson, String remarks, Integer markedAsPending) {
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

		if (markedAsPending != null) {
			condition.append(" AND MarkedAsPending = " + markedAsPending);
		}

		if (documentName != null) {
			condition.append(" AND DocumentName = " + documentName);
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(" AND PortofAuthority = \"" + portofAuthority
					+ "\"");
		}

		if (Validator.isNotNull(requestPerson)) {
			condition.append(" AND RequestPerson LIKE \"%" + requestPerson
					+ "%\"");
		}

		if (Validator.isNotNull(remarks)) {
			condition.append(" AND Remarks LIKE \"%" + remarks + "%\"");
		}

		if (Validator.isNotNull(requestDate)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(requestDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND RequestDate BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND ItineraryNo = \"" + itineraryNo + "\"");
		}

		return condition.toString();
	}
	
	public List<VmaItineraryRemark> findVmaItineraryRemarks(Class<?> clazz, String className,
			String sql, int start, int end) throws SystemException {

		

		try {
			
			
			log.info("=========select vma_itinerary_remarks>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaItineraryRemark.class).build();

			return (List<VmaItineraryRemark>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaItineraryRemarks(String sql) throws SystemException { long count = 0; try {
			
			
			log.info("=========count vma_itinerary_remarks>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
