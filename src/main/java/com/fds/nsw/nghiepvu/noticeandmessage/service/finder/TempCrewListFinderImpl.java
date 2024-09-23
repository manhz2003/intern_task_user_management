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
import com.fds.nsw.nghiepvu.model.TempCrewList;
@Service
@Slf4j
public class TempCrewListFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempCrewList> queryFactory;
	
	
	
	public TempCrewList findTempCrewListByRequestCode(String requestCode) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM TEMP_CREW_LIST WHERE RequestCode= :requestCode");
			
			String sql = query.toString();
			log.debug("=========findTempCrewListByRequestCode========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempCrewList.class).build();
			
			builder.appendNamedParameterMap("requestCode",requestCode);
			
			// execute the query and return a list from the db
			return (TempCrewList) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
	
	public List<TempCrewList> findBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM TEMP_CREW_LIST WHERE DocumentName= :documentName AND DocumentYear= :documentYear ");
			
			String sql = query.toString();
			log.debug("=========findBydocumentNameAnddocumentYear========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempCrewList.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			return (List<TempCrewList>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<TempCrewList>();
	}
	
	public TempCrewList getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		
		try {
			
			
			//StringBuilder interfaceRequest = new StringBuilder();
	/*		interfaceRequest.append("SELECT inter.RequestCode FROM interface_request inter INNER JOIN (");
			interfaceRequest.append("SELECT MAX(RequestedDate) AS MaxDateTime FROM interface_request WHERE RequestCode in (");
			interfaceRequest.append("SELECT RequestCode FROM temp_crew_list ");
			interfaceRequest.append("WHERE DocumentName = '" + documentName + "' AND DocumentYear = '" + documentYear + "'");
			interfaceRequest.append(")");
			interfaceRequest.append(") interMax ON inter.RequestedDate = interMax.MaxDateTime");*/
			
			//interfaceRequest.append("SELECT RequestCode FROM interface_request ");
			//interfaceRequest.append("where RequestCode in (select RequestCode from temp_crew_list where DocumentName = '" + documentName + "' and DocumentYear = '" + documentYear + "') ");
			//interfaceRequest.append("and RequestedDate = ( ");
			//interfaceRequest.append("select max(RequestedDate) from interface_request where RequestCode in (select RequestCode from temp_crew_list where DocumentName = '" + documentName + "' and DocumentYear = '" + documentYear + "')");
			//interfaceRequest.append(")");
			
			StringBuilder query = new StringBuilder();
			//query.append("SELECT * FROM temp_crew_list WHERE RequestCode = (" + interfaceRequest.toString() + ")");
			query.append("SELECT * FROM temp_crew_list WHERE DocumentName= :documentName AND DocumentYear= :documentYear order by ID desc");
			
			log.debug("=========getLastByDocumentNameAndDocumentYear========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempCrewList.class).build();
			
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			List<TempCrewList> lstTem = (List<TempCrewList>) queryFactory.getResultList(builder);
			
			if (lstTem != null && lstTem.size() > 0) {
				return lstTem.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}
	
	public List<TempCrewList> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName, int documentYear) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM temp_crew_list WHERE DocumentName= :documentName AND DocumentYear= :documentYear order by ID desc");
			
			String sql = query.toString();
			log.debug("===findByDocumentNameAndDocumentYearOrderByDescRequestDate==" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempCrewList.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			return (List<TempCrewList>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<TempCrewList>();
	}
}
