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

package com.fds.nsw.nghiepvu.result.service.finder;

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
import com.fds.nsw.nghiepvu.model.ResultNotification;
@Service
@Slf4j
public class ResultNotificationFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ResultNotification> queryFactory;

	

	public List<ResultNotification> findByBusinessTypeCodeOrderbyLastestDate(String businessTypeCode, long documentName, int documentYear) {
		

		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM result_notification ");
			query.append(" WHERE DocumentName= :documentName ");
			query.append(" AND DocumentYear= :documentYear ");
			query.append(" AND BusinessTypeCode IN (" + businessTypeCode + ")");
			query.append(" order by LatestDate desc ");

			System.out.println("ResultNotificationFinderImpl.findByBusinessTypeCodeOrderbyLastestDate()" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(ResultNotification.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);

			// execute the query and return a list from the db

			return (List<ResultNotification>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			
		}
		return new ArrayList<ResultNotification>();
	}

	public List<ResultNotification> findByMaritimeCodeOrderbyLastestDate(String maritimeCode) {
		

		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM result_notification WHERE 1 = 1 ");
			if (maritimeCode != null && maritimeCode.length() > 0) {
				query.append(" and  MaritimeCode = '" + maritimeCode + "'");
			}
			query.append(" and LatestDate >= '" + FormatData.parseDateToTring(FormatData.subTractDate(new Date(), 7)) + "'");
			query.append(" order by LatestDate desc LIMIT 10 ");
			
			log.debug(query.toString());
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(ResultNotification.class).build();
			

			// execute the query and return a list from the db

			return (List<ResultNotification>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} finally {
			
		}
		return new ArrayList<ResultNotification>();
	}
}
