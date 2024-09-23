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
import com.fds.nsw.nghiepvu.model.ResultHistoryMinistry;
@Service
@Slf4j
public class ResultHistoryMinistryFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ResultHistoryMinistry> queryFactory;
	
	

	public ResultHistoryMinistry findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode(long documentName, int documentYear,
			String ministryCode, String businessTypeCode) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM result_history_ministry ");
			query.append(" WHERE DocumentName= :documentName ");
			query.append(" AND DocumentYear= :documentYear ");
			query.append(" and ministryCode = '" + ministryCode + "'");
			query.append(" and businessTypeCode in (" + businessTypeCode + ")");

			String sql = query.toString();
			log.debug("=========findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(ResultHistoryMinistry.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);

			return (ResultHistoryMinistry) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public List<ResultHistoryMinistry> findDistinctMinistryCode(long documentName, int documentYear) throws SystemException {
		

		List<ResultHistoryMinistry> allResultHistoryMinistry = null;
		try {
			
			StringBuilder query = new StringBuilder();
			if (documentName > 0 && documentYear > 0) {
				query.append("SELECT DISTINCT MinistryCode FROM result_history_ministry");
				query.append(" WHERE DocumentName= :documentName ");
				query.append(" AND DocumentYear= :documentYear ");
				query.append(" order by id asc");
			}
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(ResultHistoryMinistry.class).build();
			
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);

			// execute the query and return a list from the db

			allResultHistoryMinistry = (List<ResultHistoryMinistry>) queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return allResultHistoryMinistry;
	}

	// public List<ResultHistoryMinistry> findLeftJoinMinistryCode(long
	// documentName, int documentYear) throws SystemException {
	// 
	//
	// List<ResultHistoryMinistry> allResultHistoryMinistry = null;
	// try {
	// 
	// StringBuilder query = new StringBuilder();
	// if(documentName >0 && documentYear >0){
	// query.append(" SELECT DISTINCT rMy.MinistryCode, rMy.DocumentName, rMy.DocumentYear,Thongbao.BusinessTypeCode, Thongbao.Thongbao_Response ,Xacbao.BusinessTypeCode, Xacbao.Xacbao_Response,Hoso.BusinessTypeCode, Hoso.Hoso_Response ");
	// query.append(" FROM result_history_ministry as rMy LEFT JOIN (SELECT DISTINCT MinistryCode, DocumentName, DocumentYear FROM result_history_ministry ) as res ON res.DocumentName = rMy.DocumentName AND res.DocumentYear = rMy.DocumentYear ");
	// query.append(" LEFT JOIN (SELECT DISTINCT MinistryCode, DocumentName, DocumentYear, Response as Thongbao_Response, BusinessTypeCode FROM result_history_ministry ) as Thongbao ON Thongbao.DocumentName = rMy.DocumentName AND Thongbao.DocumentYear = rMy.DocumentYear AND Thongbao.MinistryCode = rMy.MinistryCode and Thongbao.BusinessTypeCode IN ('30', '31', '32') ");
	// query.append(" LEFT JOIN (SELECT DISTINCT MinistryCode, DocumentName, DocumentYear, Response as Xacbao_Response, BusinessTypeCode FROM result_history_ministry ) as Xacbao ON Xacbao.DocumentName = rMy.DocumentName AND Xacbao.DocumentYear = rMy.DocumentYear  AND Xacbao.MinistryCode = rMy.MinistryCode AND Xacbao.BusinessTypeCode IN ('40', '42')");
	// query.append(" LEFT JOIN (SELECT DISTINCT MinistryCode, DocumentName, DocumentYear, Response as Hoso_Response, BusinessTypeCode FROM result_history_ministry ) as Hoso ON Hoso.DocumentName = rMy.DocumentName AND Hoso.DocumentYear = rMy.DocumentYear AND Hoso.MinistryCode = rMy.MinistryCode AND Hoso.BusinessTypeCode IN ('99')");
	// query.append(" WHERE rMy.DocumentName = ?");
	// query.append(" and rMy.DocumentYear = ?");
	// query.append(" order by id asc");
	// }
	//
	// // SELECT DISTINCT rMy.MinistryCode, rMy.DocumentName, rMy.DocumentYear,
	// // Thongbao.BusinessTypeCode, Thongbao.Thongbao_Response ,
	// // Xacbao.BusinessTypeCode, Xacbao.Xacbao_Response,
	// // Hoso.BusinessTypeCode, Hoso.Hoso_Response
	// //
	// // FROM result_history_ministry as rMy
	// // LEFT JOIN (SELECT DISTINCT MinistryCode, DocumentName, DocumentYear
	// FROM result_history_ministry ) as res
	// // ON res.DocumentName = rMy.DocumentName AND res.DocumentYear =
	// rMy.DocumentYear
	// //
	// // LEFT JOIN (SELECT DISTINCT MinistryCode, DocumentName, DocumentYear,
	// Response as Thongbao_Response, BusinessTypeCode FROM
	// result_history_ministry ) as Thongbao
	// // ON Thongbao.DocumentName = rMy.DocumentName AND Thongbao.DocumentYear
	// = rMy.DocumentYear
	// // AND Thongbao.MinistryCode = rMy.MinistryCode and
	// Thongbao.BusinessTypeCode IN ('30', '31', '32')
	// //
	// // LEFT JOIN (SELECT DISTINCT MinistryCode, DocumentName, DocumentYear,
	// Response as Xacbao_Response, BusinessTypeCode FROM
	// result_history_ministry ) as Xacbao
	// // ON Xacbao.DocumentName = rMy.DocumentName AND Xacbao.DocumentYear =
	// rMy.DocumentYear
	// // AND Xacbao.MinistryCode = rMy.MinistryCode AND Xacbao.BusinessTypeCode
	// IN ('40', '42')
	// //
	// // LEFT JOIN (SELECT DISTINCT MinistryCode, DocumentName, DocumentYear,
	// Response as Hoso_Response, BusinessTypeCode FROM result_history_ministry
	// ) as Hoso
	// // ON Hoso.DocumentName = rMy.DocumentName AND Hoso.DocumentYear =
	// rMy.DocumentYear
	// // AND Hoso.MinistryCode = rMy.MinistryCode AND Hoso.BusinessTypeCode IN
	// ('99')
	// //
	// // WHERE rMy.DocumentName = '15010' and rMy.DocumentYear = 2014 order by
	// id desc;
	//
	//
	// QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).
	//
	// log.debug("=========findTemDocumentByDocumentNameAndDocumentYear========"
	// + q+query.toString());
	// 
	// 
	// builder.appendNamedParameterMap("documentName",documentName);
	// builder.appendNamedParameterMap("documentYear",documentYear);
	//
	// // execute the query and return a list from the db
	//
	// allResultHistoryMinistry = (List<ResultHistoryMinistry>)QueryUtil.list(q,
	// getDialect(),
	// QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	//
	//
	// } catch (Exception e) {
	// throw new SystemException(e);
	// } finally {
	// 
	// }
	// return allResultHistoryMinistry;
	// }

}
