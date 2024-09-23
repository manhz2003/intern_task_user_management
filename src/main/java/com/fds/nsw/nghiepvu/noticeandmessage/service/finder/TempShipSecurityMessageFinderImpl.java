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
import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;
@Service
@Slf4j
public class TempShipSecurityMessageFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempShipSecurityMessage> queryFactory;
	
	
	
	public List<TempShipSecurityMessage> findBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM TEMP_SHIP_SECURITY_MESSAGE WHERE DocumentName= :documentName AND DocumentYear= :documentYear ");
			
			String sql = query.toString();
			log.debug("=========findBydocumentNameAnddocumentYear========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempShipSecurityMessage.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			return (List<TempShipSecurityMessage>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<TempShipSecurityMessage>();
	}
	
	public TempShipSecurityMessage getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		
		try {
			
			
			//StringBuilder interfaceRequest = new StringBuilder();
/*			interfaceRequest.append("SELECT inter.RequestCode FROM interface_request inter INNER JOIN (");
			interfaceRequest.append("SELECT MAX(RequestedDate) AS MaxDateTime FROM interface_request WHERE RequestCode in (");
			interfaceRequest.append("SELECT RequestCode FROM temp_ship_security_message ");
			interfaceRequest.append("WHERE DocumentName = '" + documentName + "' AND DocumentYear = '" + documentYear + "'");
			interfaceRequest.append(")");
			interfaceRequest.append(") interMax ON inter.RequestedDate = interMax.MaxDateTime");
*/
			
			/*interfaceRequest.append("SELECT RequestCode FROM interface_request ");
			interfaceRequest.append("where RequestCode in (select RequestCode from temp_ship_security_message where DocumentName = '" + documentName + "' and DocumentYear = '" + documentYear + "') ");
			interfaceRequest.append("and RequestedDate = ( ");
			interfaceRequest.append("select max(RequestedDate) from interface_request where RequestCode in (select RequestCode from temp_ship_security_message where DocumentName = '" + documentName + "' and DocumentYear = '" + documentYear + "')");
			interfaceRequest.append(")");*/
			
			StringBuilder query = new StringBuilder();
			//query.append("SELECT * FROM temp_ship_security_message WHERE RequestCode = (" + interfaceRequest.toString() + ")");
			query.append("SELECT * FROM temp_ship_security_message WHERE DocumentName= :documentName AND DocumentYear= :documentYear order by ID desc");
			
			log.debug("=========getLastByDocumentNameAndDocumentYear========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempShipSecurityMessage.class).build();
			
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			List<TempShipSecurityMessage> lstTem = (List<TempShipSecurityMessage>) queryFactory.getResultList(builder);
			
			if (lstTem != null && lstTem.size() > 0) {
				return lstTem.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}
	
	public TempShipSecurityMessage getByRequestCode(String requestCode) {
		
		try {
			
			
			StringBuilder query = new StringBuilder();
			// query.append("SELECT * FROM temp_ship_security_message WHERE RequestCode = (" + interfaceRequest.toString() + ")");
			query.append("SELECT * FROM temp_ship_security_message WHERE RequestCode= :requestCode");
			
			log.debug("=========getLastByDocumentNameAndDocumentYear========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempShipSecurityMessage.class).build();
			
			
			builder.appendNamedParameterMap("requestCode",requestCode);
			
			// execute the query and return a list from the db
			List<TempShipSecurityMessage> lstTem = (List<TempShipSecurityMessage>) queryFactory.getResultList(builder);
			
			if (lstTem != null && lstTem.size() > 0) {
				return lstTem.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}
	
	public List<TempShipSecurityMessage> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName, int documentYear) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM TEMP_SHIP_SECURITY_MESSAGE WHERE DocumentName= :documentName AND DocumentYear= :documentYear order by ID desc");
			
			String sql = query.toString();
			log.debug("===findByDocumentNameAndDocumentYearOrderByDescRequestDate==" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempShipSecurityMessage.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			return (List<TempShipSecurityMessage>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<TempShipSecurityMessage>();
	}
}
