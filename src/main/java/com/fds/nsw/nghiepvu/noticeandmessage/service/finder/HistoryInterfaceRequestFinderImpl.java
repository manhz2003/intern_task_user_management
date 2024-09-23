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
import com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest;
@Service
@Slf4j
public class HistoryInterfaceRequestFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<HistoryInterfaceRequest> queryFactory;



	public HistoryInterfaceRequest findHistoryInterfaceRequestByRequestCode(String requestCode) throws SystemException {

		try {

			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM HISTORY_INTERFACE_REQUEST ");
			query.append(" WHERE RequestCode= :requestCode");

			log.info("=========findHistoryInterfaceRequestByRequestCode========" + query.toString() + "requestCode:" + requestCode);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(HistoryInterfaceRequest.class).build();
			log.info("===new builder: " + builder.getQueryString());
			builder.appendNamedParameterMap("requestCode",requestCode);

			// execute the query and return a list from the db
			return (HistoryInterfaceRequest) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {

		}
	}

	public int updateHistoryInterfaceRequest(String sql) throws SystemException {

		try {


			log.debug("=========updateByRequestCode========" + sql);

			QueryBuilder builder = QueryBuilder.builder().queryString(sql).entityClass(HistoryInterfaceRequest.class).build();
			builder.setSqlQuery(true);

			
			
			int executeUpdate = queryFactory.executeUpdate(builder);
			// session.flush();
			return executeUpdate;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(e);
		} finally {
			
		}
	}
}
