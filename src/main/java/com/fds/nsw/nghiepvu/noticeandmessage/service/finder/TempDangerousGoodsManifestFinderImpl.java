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
import java.util.List;

import com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;

import lombok.extern.slf4j.Slf4j;
//import com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest;
@Service
@Slf4j
public class TempDangerousGoodsManifestFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempDangerousGoodsManifest> queryFactory;
	
	
	
	public List<TempDangerousGoodsManifest> findBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM TEMP_DANGEROUS_GOODS_MANIFEST WHERE DocumentName= :documentName AND DocumentYear= :documentYear ");
			
			String sql = query.toString();
			log.debug("=========findBydocumentNameAnddocumentYear========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDangerousGoodsManifest.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			return (List<TempDangerousGoodsManifest>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<TempDangerousGoodsManifest>();
	}
	
	public int countBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT count(*) as total FROM TEMP_DANGEROUS_GOODS_MANIFEST WHERE DocumentName= :documentName AND DocumentYear= :documentYear ");
			
			log.debug("=========countBydocumentNameAnddocumentYear========" + query.toString());
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Integer.class).build();
			
			
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			int count = 0;
			return (Integer) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return 0;
	}
	
	public TempDangerousGoodsManifest getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		
		try {
			
			
			//StringBuilder interfaceRequest = new StringBuilder();
			/*interfaceRequest.append("SELECT inter.RequestCode FROM interface_request inter INNER JOIN (");
			interfaceRequest.append("SELECT MAX(RequestedDate) AS MaxDateTime FROM interface_request WHERE RequestCode in (");
			interfaceRequest.append("SELECT RequestCode FROM temp_dangerous_goods_manifest ");
			interfaceRequest.append("WHERE DocumentName = '" + documentName + "' AND DocumentYear = '" + documentYear + "'");
			interfaceRequest.append(")");
			interfaceRequest.append(") interMax ON inter.RequestedDate = interMax.MaxDateTime");*/
			
			//interfaceRequest.append("SELECT RequestCode FROM interface_request ");
			//interfaceRequest.append("where RequestCode in (select RequestCode from temp_dangerous_goods_manifest where DocumentName = '" + documentName + "' and DocumentYear = '" + documentYear + "') ");
			//interfaceRequest.append("and RequestedDate = ( ");
			//interfaceRequest.append("select max(RequestedDate) from interface_request where RequestCode in (select RequestCode from temp_dangerous_goods_manifest where DocumentName = '" + documentName + "' and DocumentYear = '" + documentYear + "')");
			//interfaceRequest.append(")");
			
			StringBuilder query = new StringBuilder();
			//query.append("SELECT * FROM temp_dangerous_goods_manifest WHERE RequestCode = (" + interfaceRequest.toString() + ")");
			query.append("SELECT * FROM temp_dangerous_goods_manifest WHERE DocumentName= :documentName AND DocumentYear= :documentYear order by ID desc");
			
			log.debug("=========getLastByDocumentNameAndDocumentYear========" + query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(TempDangerousGoodsManifest.class).build();
			
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			List<TempDangerousGoodsManifest> lstTem = (List<TempDangerousGoodsManifest>) queryFactory.getResultList(builder);
			
			if (lstTem != null && lstTem.size() > 0) {
				return lstTem.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}
	
	public List<TempDangerousGoodsManifest> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName, int documentYear) {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM temp_dangerous_goods_manifest WHERE DocumentName= :documentName AND DocumentYear= :documentYear order by ID desc");
			
			String sql = query.toString();
			log.debug("===findByDocumentNameAndDocumentYearOrderByDescRequestDate==" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(TempDangerousGoodsManifest.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			return (List<TempDangerousGoodsManifest>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<TempDangerousGoodsManifest>();
	}
}
